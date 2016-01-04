package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Activity2 extends Activity {
	private ImageView img1;
	TextView tv;
	String s;
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		Intent i1 = getIntent();
		String str = i1.getStringExtra("imagecode1");
		img1 = (ImageView)findViewById(R.id.ImageView03);
		tv = (TextView)findViewById(R.id.tvimagecode);
		tv.setText(str);
		
	}
	public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
   
public void show1 (View v){
	String code = tv.getText().toString();
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
    query.whereEqualTo("Image",code);
    query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> parseObjects, ParseException e) {
            ParseFile file = (ParseFile) parseObjects.get(0).get("ImageFile");
            file.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {
                    Bitmap bmp = BitmapFactory
                            .decodeByteArray(bytes, 0,bytes.length);
                    img1.setImageBitmap(bmp);
                }
            });
        }
    });


	/*DBAdapter1 db = new DBAdapter1(this);
	String name = db.getbook(code);

	Uri muri = Uri.parse(name);
	 s = getPath(muri);
     System.out.println("Image Path : " + s);
     img1.setImageURI(muri);*/
}
	
}
