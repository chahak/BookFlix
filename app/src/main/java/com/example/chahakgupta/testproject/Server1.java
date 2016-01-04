package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Server1 extends Activity {

    private TextView resultview;
    private EditText rollno,name;
    JSONParser jsonParser = new JSONParser();
    private int success;
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server1);
        StrictMode.enableDefaults();
        resultview = (TextView) findViewById(R.id.result);
        rollno = (EditText) findViewById(R.id.server_roll);
        name = (EditText) findViewById(R.id.server_name);
        getdata();
    }

    public void getdata() {
        String result = "";
        InputStream isr = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            //String link = "https://127.0.0.1/server.php";
            //URL url = new URL(link);
            //HttpPost httppost = new HttpPost();
            //httppost.setURI(new URI(link));

            //HttpPost httppost = new HttpPost("http://192.168.1.5/server.php");
            HttpPost httppost = new HttpPost("http://developerarush.esy.es/hackdengue/server.php");
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            isr = entity.getContent();
        } catch (Exception e) {
            Log.e("log.tag", "Error in http connection" + e.toString());
            resultview.setText("Couldn't connect to database");
        }
        //convert response to string
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
            BufferedReader reader = new BufferedReader(new InputStreamReader(isr));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log.tag", "Error converting result " + e.toString());

        }
        try{
            String s = "";
            JSONArray jarray = new JSONArray(result);

            for(int i=0;i<jarray.length();i++){
                JSONObject json = jarray.getJSONObject(i);
                s = s +
                        "Roll :" + json.getString("roll")+"\n" +
                        "Name :" + json.getString("name") + "\n";
            }
            resultview.setText(s);
        }
        catch (Exception e){
            Log.e("log.tag","Error Parsing Data "+e.toString());
        }
    }

    public void server_submit(View v){

        String r = rollno.getText().toString();
        String n = name.getText().toString();
        String line = null,
        result = null;
        InputStream is = null;
        int code;
       // String url = "http://192.168.1.5/server_insert.php";
        String url = "http://developerarush.esy.es/hackdengue/server_insert.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("roll",r));
        params.add(new BasicNameValuePair("name", n));

        ///////
        JSONObject json = jsonParser.makeHttpRequest(url, "GET", params);

       /* try {
            success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // successfully save new idiom
            } else {
                // failed to add new idiom
            }
        }
     catch (JSONException e) { e.printStackTrace();
    }*/

       /* try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is=entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",Toast.LENGTH_LONG).show();
        }


        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            code=(json_data.getInt("code"));

            if(code==1)
            {
                Toast.makeText(this, "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }*/
    }


}
