package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GetImageActivity extends Activity {
	TextView tv;
 
    private static final int SELECT_PICTURE = 1;
 
    private String s;
    private ImageView img;
    String code;
    String storeuri;
    String str;
    byte[] image;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        img = (ImageView)findViewById(R.id.ImageView10);
        tv = (TextView) findViewById(R.id.tvimagecode0);
 
        Intent i1 = getIntent();
		 str = i1.getStringExtra("imagecode");
        tv.setText(str);
        code = tv.getText().toString();
        ((Button) findViewById(R.id.Button01))
                .setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {

                        Intent intent = new Intent(Intent.ACTION_PICK,
                        		MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                       //** startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
                        startActivityForResult(intent,SELECT_PICTURE);
                    }
                });




        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
        query.whereEqualTo("Image",code);
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int i, ParseException e) {
                if(i>0){
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> parseObjects, ParseException e) {
                            ParseFile file = (ParseFile) parseObjects.get(0).get("ImageFile");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] bytes, ParseException e) {
                                    Bitmap bmp = BitmapFactory
                                            .decodeByteArray(bytes, 0,bytes.length);
                                    img.setImageBitmap(bmp);
                                }
                            });
                        }
                    });

                }
                else {
                    int resID = getResources().getIdentifier("b3", "drawable", getApplicationContext().getPackageName());
                    //  Drawable dr = getDrawable(resID);
                    img.setImageResource(resID);
                }
            }
        });

    }
 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
       /** if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {**/
                if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && null != data) {//
               /* Uri selectedImageUri = data.getData();
                s = getPath(selectedImageUri);
                System.out.println("Image Path : " + s);
                img.setImageURI(selectedImageUri);****/
               /* String name = s.substring(s.lastIndexOf("/") + 1);
                	     // instead of "/" you can also use File.sepearator
                	     System.out.println("......"+ name);
                	    // image_name=new TextView(ActivityName.this);
                	     tv.setText(name);  */

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

                        final ParseFile file = new ParseFile("storeuri.png",  image);
                        // Upload the image into Parse Cloud
                        file.saveInBackground();

                        // Create a New Class called "ImageUpload" in Parse
                        final ParseObject imgupload = new ParseObject("Image");
                       final ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
                        query.whereEqualTo("Image",code);
                        query.countInBackground(new CountCallback() {
                            @Override
                            public void done(int i, ParseException e) {
                                if(i>0){
                                   query.findInBackground(new FindCallback<ParseObject>() {
                                       @Override
                                       public void done(List<ParseObject> parseObjects, ParseException e) {
                                           parseObjects.get(0).put("ImageFile", file);
                                           parseObjects.get(0).saveInBackground();
                                       }
                                   });
                                }
                                else{
                                    // Create a column named "ImageName" and set the string
                                    imgupload.put("Image", str);


                                    // Create a column named "ImageFile" and insert the image
                                    imgupload.put("ImageFile", file);

                                    // Create the class and the columns
                                    imgupload.saveInBackground();

                                }

                            }
                        });



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
            	//Bitmap bm = BitmapFactory.decodeFile(selectedImagePath, options);
            	//img.setImageBitmap(bm);

            	//  Message.message(this,storeuri);//



                    //setting image of imageview
                    final ParseQuery<ParseObject> query = ParseQuery.getQuery("Image");
                    query.whereEqualTo("Image",code);
                    query.countInBackground(new CountCallback() {
                        @Override
                        public void done(int i, ParseException e) {
                            if(i>0){
                                query.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> parseObjects, ParseException e) {
                                        ParseFile file = (ParseFile) parseObjects.get(0).get("ImageFile");
                                        file.getDataInBackground(new GetDataCallback() {
                                            @Override
                                            public void done(byte[] bytes, ParseException e) {
                                                Bitmap bmp = BitmapFactory
                                                        .decodeByteArray(bytes, 0,bytes.length);
                                                img.setImageBitmap(bmp);
                                            }
                                        });
                                    }
                                });

                            }
                            else {
                                int resID = getResources().getIdentifier("b3", "drawable", getApplicationContext().getPackageName());
                                //  Drawable dr = getDrawable(resID);
                                img.setImageResource(resID);
                            }
                        }
                    });


            //}
        }
        else{

                }

    }
 
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
   
    public void next(View v){

    	 DBAdapter1 db = new DBAdapter1(this);
         Long id= db.insertdata(code, storeuri);
          if(id>1){
          	Message.message(this, "successful" + id);
          }
    	Intent i = new Intent(this,Activity2.class);
    	i.putExtra("imagecode1", code);
    	startActivity(i);
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
    
    public void back(View v){
   	 //DBAdapter1 db = new DBAdapter1(this);
      //  Long id= db.insertdata(code, storeuri);
        // if(id>1){
         //	Message.message(this, "successful" + id);
         //}
   	Intent data = new Intent();
   //	data.setData(Uri.parse(code));
	setResult(RESULT_OK, data);
    data.putExtra("img",image);
	finish();
   	//i.putExtra("imagecode1", code);
   //	startActivity(i);
   }
   }