package com.example.chahakgupta.testproject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Signin extends Activity {
	EditText e1,e2;
	TextView tv;
	DBAdapter adapter;
	SharedPreferences.Editor editor;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
      //  Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "cb0ypHVrFQtiOYoIDP2sVhwqp7ALB1cHrAUjBWUJ", "rINUwRI3gwgTbQ61oZNnUqraahcxJE5gv9BRyJAT");

        //adapter = new DBAdapter(this);
		sp=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		editor=sp.edit();
		e1 = (EditText)findViewById(R.id.userroll);
		e2 = (EditText)findViewById(R.id.userpass);
		tv = (TextView)findViewById(R.id.info3);
	}

	public void usersign(View v) {
        final String userroll = e1.getText().toString().trim();
        final String pass = e2.getText().toString().trim();

      /*  String[] str = new String[1];
        adapter = new DBAdapter(this);
		str[0] = adapter.checkdata(userroll, pass);
		if(str[0].equals("false")){
			tv.setText("Incorrect roll number or password!");
		}
		else{
		//	savedata(userroll,pass);
		//	DBAdapter adapter = new DBAdapter(this);
		//	adapter.deletecart();
			Intent i = new Intent(this, Start.class);
			//i.putExtra("seller_roll", userroll);
			startActivity(i);
		}
	}
***/
      /**  ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("Rollnum", userroll);
        query.whereEqualTo("password",pass);
      //  final String[] b = new String[1];
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> u, ParseException e) {
                if (e == null) {
                    // Log.d("score", "Retrieved " + u.size() + " scores");
                    if (u.size()>0) {
                        Intent i = new Intent(Signin.this, Start.class);
                        //i.putExtra("seller_roll", userroll);
                        startActivity(i);
                    } else {
                        tv.setText("Incorrect roll number or password!");
                    }
                } else {
                    //Log.d("score", "Error: " + e.getMessage());
                }
            }
        });**/
        if(TextUtils.isEmpty(userroll)||TextUtils.isEmpty(pass)) {
            if(TextUtils.isEmpty(userroll))
                e1.setError("username cannot be empty");
            if(TextUtils.isEmpty(pass))
                e2.setError("password cannot be empty");
            return;
        }
        else {
            ParseUser.logInInBackground(userroll, pass,
                    new LogInCallback() {
                        public void done(ParseUser user,
                                         ParseException e) {
                            //  mProgressBar.setVisibility(View.INVISIBLE);
                            if (user != null) {
                                // Hooray! The user is logged in.
                                savedata(userroll, pass);
                                startActivity(new Intent(
                                        Signin.this, Start.class));
                            } else {
                                // Login failed. Look at the
                                // ParseException to see what happened.
                                e1.setText("");
                                e2.setText("");
                                tv.setText("Incorrect credentials!");
                         /*   Toast.makeText(Signin.this,
                                    "Login failed! Try again.",
                                    Toast.LENGTH_LONG).show();*/
                            }
                        }
                    });
            //end of login process
        }
    }
    private void savedata(String userroll, String pass) {
		// TODO Auto-generated method stub
		editor.clear();
		editor.putString("roll", userroll);
		editor.putString("password", pass);
		editor.putString("remember", "yes");
		editor.commit();
	}

}
