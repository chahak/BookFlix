package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Show_book extends Fragment {
	//DBAdapter db;
	//Book b;
	//private View rootview;
    Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//rootview = inflater.inflate(R.layout.show_book, container, false);
		//super.onCreate(savedInstanceState);
       final View rootview = inflater.inflate(R.layout.show_book, container, false);
		//setContentView(R.layout.show_book);
		//Intent i = getIntent();
        ctx=getActivity();

        ImageView iv = (ImageView) rootview.findViewById(R.id.gotocart);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Cartlist_ndfr fr1=new Cartlist_ndfr();
                ft.replace(R.id.fl,fr1);
                ft.commit();
            }
        });

        Bundle data = this.getArguments();
		String code = data.getString("pos");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        query.whereEqualTo("objectId", code);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> parseObjects, ParseException e) {
                if(e==null){

                    final String code =parseObjects.get(0).getObjectId();
                    final String name = parseObjects.get(0).getString("Book_name");
                    final String publish =parseObjects.get(0).getString("Book_publisher");
                    final String price = parseObjects.get(0).getString("Book_price");
                    final String author = parseObjects.get(0).getString("Book_author");
                    TextView tv1 = (TextView) rootview.findViewById(R.id.code_t);
                    tv1.setText(code);
                    TextView tv2 = (TextView) rootview.findViewById(R.id.name_t);
                    tv2.setText(name);
                    TextView tv3 = (TextView) rootview.findViewById(R.id.publisher_t);
                    tv3.setText(publish);
                    TextView tv4 = (TextView) rootview.findViewById(R.id.price_t);
                    tv4.setText("Rs." + price);
                    TextView tv5 = (TextView) rootview.findViewById(R.id.author_t);
                    tv5.setText(author);
                    TextView tv6 = (TextView) rootview.findViewById(R.id.condition_t);
                    tv6.setText(parseObjects.get(0).getString("Book_condition"));
                    final ImageView imageview = (ImageView) rootview.findViewById(R.id.iv1);
                    final Button b1 = (Button) rootview.findViewById(R.id.addcart1);

                    //set image from image database if available


                    if(parseObjects.get(0).getString("image_status").equals("false")) {
                        int resID = getResources().getIdentifier(parseObjects.get(0).getString("Book_image"), "drawable", getActivity().getPackageName());
                        //  Drawable dr = getDrawable(resID);
                        imageview.setImageResource(resID);
                    }
                    else{
                        ParseFile file =parseObjects.get(0).getParseFile("file");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                Bitmap bmp = BitmapFactory
                                        .decodeByteArray(bytes, 0,bytes.length);
                                imageview.setImageBitmap(bmp);
                            }
                        });
                    }



                    ParseUser user = ParseUser.getCurrentUser();
                    String u = user.getString("username");
                    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Cart_details");
                    query1.whereEqualTo("Current_user",u);
                    query1.whereEqualTo("objectId",code);
                    query1.countInBackground(new CountCallback() {
                        @Override
                        public void done(int i, ParseException e) {
                            if (e == null) {
                                String result;
                                if (i > 0) {
                                    result = "true";
                                } else {

                                    result = "false";
                                }
                                // b.setText(""+i);
                                if (result.equals("true")) {
                                    b1.setBackgroundColor(Color.LTGRAY);
                                    b1.setEnabled(false);
                                    b1.setText("in cart");

                                }
                            } else {
                                Log.d("item", "Error: " + e.getMessage());

                            }
                        }

                    });

                    final DBAdapter db = new DBAdapter(ctx);
                    b1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          //  String code = barray[position].getString("Book_code");
                            // Message.message(context,""+code);
                           // String name =barray[position].getString("Book_name");
                         //   String price = "Rs." + barray[position].getString("Book_price");
                           // String publish =barray[position].getString("Book_publisher");
                           // String author = barray[position].getString("Book_author");
                            b1.setEnabled(false);
                            b1.setText("in cart");
                            b1.setBackgroundColor(Color.LTGRAY);
                            //	DBAdapter db = new DBAdapter(context);
                            String image = parseObjects.get(0).getString("Book_image");
                            ParseUser user = ParseUser.getCurrentUser();
                            String u = user.getString("username");


                            //	String condition = "try";

                            db.insertcartdata(code,name,publish,author,price,image,u);

                        }
                    });
                }
            }
        });
	/*	DBAdapter db = new DBAdapter(this);
	
		Book b= db.getbook(code);
		//Message.message(getActivity(),code);
		TextView tv1 = (TextView) findViewById(R.id.code_t);
		tv1.setText(b.code);
		TextView tv2 = (TextView) findViewById(R.id.name_t);
		tv2.setText(b.name);
		TextView tv3 = (TextView) findViewById(R.id.publisher_t);
		tv3.setText(b.publisher);
		TextView tv4 = (TextView) findViewById(R.id.price_t);
		tv4.setText("Rs." + b.price);
		TextView tv5 = (TextView) findViewById(R.id.author_t);
		tv5.setText(b.author);
		TextView tv6 = (TextView) findViewById(R.id.condition_t);
		tv6.setText(b.condition);
		final ImageView imageview = (ImageView) findViewById(R.id.iv1);
		int resID = getResources().getIdentifier(b.name, "drawable","com.thapar.bookbazar" );
		imageview.setImageResource(resID);*/
		/*Button back = (Button)rootview.findViewById(R.id.back_button);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*NavDrawFrag2 f2 = new NavDrawFrag2();
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				ft.replace(R.id.fl, f2);
				ft.commit();

 				Intent i5 = new Intent(this, NavDrawFrag2.class);
 				startActivity(i5);
			}
		});*/
        return rootview;
	}

}
