package com.example.chahakgupta.testproject;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Sell_Books extends Activity {
	
	private static final int SELECT_PICTURE = 1;
	int request_Code = 1;
	DBAdapter adapter;
	EditText e1,e2,e3,e4,e5,e6,e7,e8;
	Button b;
	String s;
byte[] image;
    String storeuri;
	ImageView iv;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
    String code ="img12";
    long id;
    ParseFile file;

    String selected="false";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sell_books);
		
		adapter = new DBAdapter(this);
		//img = (ImageView)findViewById(R.id.ImageView01);
		//e1 = (EditText)findViewById(R.id.code);
		e2 = (EditText)findViewById(R.id.bookname);
		e3 = (EditText)findViewById(R.id.publisher);
		e4 = (EditText)findViewById(R.id.author);
		e5 = (EditText)findViewById(R.id.price);
		e6 = (EditText)findViewById(R.id.condition);
        e7 = (EditText)findViewById(R.id.s_add);
        e8 = (EditText)findViewById(R.id.s_mobile);
		iv = (ImageView)findViewById(R.id.ImageView01);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        logged=prefs.getString("roll", "");

		      
		        
		//**Intent i = getIntent();
		//**user = i.getStringExtra("userroll");
	}
	 
	    
	   
	public void book_submit(View v){
		// final String code = e1.getText().toString();
		final String bookname= e2.getText().toString();
		final String publisher= e3.getText().toString();
		final String author= e4.getText().toString();
		final String price= e5.getText().toString();
		final String condition= e6.getText().toString();
		final String status = "not sold";
		final String buyer = "null";
       // String seller = "me";
		final String image = "b3";
        final String address = e7.getText().toString();
        final String phone = e8.getText().toString();
		DBAdapter db = new DBAdapter(this);
		//User user1 = db.getuser(logged) ;
        ParseUser user = ParseUser.getCurrentUser();
        final String seller = user.getObjectId();
		//if(storeuri.equals(image))
			//image = "b3";

		//else

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Confirm");
        ad.setMessage("Do you want to edit the details?");
        ad.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                id = adapter.insertbookdata( bookname, publisher, author,price,condition,status,image,seller,buyer,0,file,selected);
                if(id==0)
                {
                    Message.message(getApplicationContext(),"insertion unsuccessful");
                }
                else
                {
                    Message.message(getApplicationContext(), "insertion successful" + id);

                }

                final ParseQuery q1 = ParseQuery.getQuery("User_details");
                q1.whereEqualTo("user",ParseUser.getCurrentUser().getObjectId());
                q1.countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, ParseException e) {
                        if(i>0){
                            q1.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> parseObjects, ParseException e) {
                                    if(e==null) {
                                        parseObjects.get(0).put("address",address);
                                        parseObjects.get(0).put("mobile",phone);
                                        parseObjects.get(0).saveInBackground();
                                    }
                                }
                            });
                        }
                        else{
                            ParseObject user_detail = new ParseObject("User_details");
                            user_detail.put("user", ParseUser.getCurrentUser().getObjectId());
                            user_detail.put("address", address);
                            user_detail.put("mobile", phone);
                            user_detail.saveInBackground();
                        }
                    }
                });

                finish();
            }
        });
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        ad.show();


		}

	public void setimage(View v){
        ////temp
        /*
		Intent i1 = new Intent(this,GetImageActivity.class);
		String code = e1.getText().toString();
		i1.putExtra("imagecode", code);
		startActivityForResult(i1, request_Code);
        */
        ///temp
       // final String code = e1.getText().toString();
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //** startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
        startActivityForResult(intent,SELECT_PICTURE);
	}


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {//

            selected="true";
            Uri selectedImageUri = data.getData();
            //**String[] projection = { MediaColumns.DATA };
            String filePath = "";
            String wholeID = DocumentsContract.getDocumentId(selectedImageUri);
            String id = wholeID.split(":")[1];
            String[] projection = {MediaStore.Images.Media.DATA}; //
            String sel = MediaStore.Images.Media._ID + "=?";
            //** Cursor cursor = managedQuery(selectedImageUri, projection, null, null,null);
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection, sel, new String[]{id}, null); //
            //**int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
            cursor.moveToFirst(); //
            int column_index = cursor.getColumnIndex(projection[0]); //
            //**cursor.moveToFirst();
            String selectedImagePath = cursor.getString(column_index);
            Message.message(this,selectedImagePath);
            //cursor.close(); //

            /*****/
            storeuri = selectedImageUri.toString();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                //  Message.message(this,bm.toString());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = stream.toByteArray();
                // Object image = null;
                try {
                    String path = null;
                    image = readInFile(selectedImagePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                file = new ParseFile("storeuri.png",  image);
                // Upload the image into Parse Cloud
                file.saveInBackground();

                Bitmap bmp = BitmapFactory
                        .decodeByteArray(image, 0,image.length);
                iv.setImageBitmap(bmp);
                // Create a New Class called "ImageUpload" in Parse




            } catch (IOException e) {
                e.printStackTrace();
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            ///BitmapFactory.decodeFile(selectedImagePath, options);
            final int REQUIRED_SIZE = 200;
            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;





            //}
        }
        else{

        }

    }



    private byte[] readInFile(String path) throws IOException {
        // TODO Auto-generated method stub
        byte[] data = null;
        File file = new File(path);
        InputStream input_stream = new BufferedInputStream(new FileInputStream(
                file));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        data = new byte[16384]; // 16K
        int bytes_read;
        while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytes_read);
        }
        input_stream.close();
        return buffer.toByteArray();

    }

}

