package com.example.chahakgupta.testproject;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Cartlist_ndfr extends Fragment{
	private ListView lv;
	private List<Cartbook> l;
    ParseObject[] bname;
	DBAdapter db;
    Context context;
//	SharedPreferences.Editor editor;
//	SharedPreferences prefs;
	String logged;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootview = inflater.inflate(R.layout.cartlistndfr1, container, false);
		//final DBAdapter db = new DBAdapter(getActivity());
		//name =  db.getalldatainList().toArray();
		//name = show.getalldatainList().toArray(new String[l.size()]);
		lv = (ListView) rootview.findViewById(R.id.listView3);
        context= getActivity();

        ImageView iv = (ImageView) rootview.findViewById(R.id.refresh);
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

       final ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart_details");
       query.whereEqualTo("Current_user", ParseUser.getCurrentUser().getObjectId());

        query.countInBackground(new CountCallback() {
                                    public void done(int count, ParseException e) {
                                        if (e == null) {
                                            if(count>0){
                                              /*  ParseUser user = ParseUser.getCurrentUser();
                                                String u = user.getObjectId();
                                                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Cart_details");
                                                query1.whereEqualTo("Current_user",u);*/
                                                query.findInBackground(new FindCallback<ParseObject>() {
                                                    @Override
                                                    public void done(List<ParseObject> itemList, ParseException e) {
                                                        if (e == null) {
                                                                bname = new ParseObject[itemList.size()];
                                                            bname = itemList.toArray(bname);
                                                            adapter ad = new adapter(context, bname);
                                                            lv.setAdapter(ad);
                                                        } else {
                                                            Log.d("item", "Error: " + e.getMessage());
                                                        }
                                                    }
                                                });
                                            }
                                            else{
                                                Message.message(context, "cart is empty");
                                            }
                                        }
                                        else {
                                            Log.d("item", "Error: " + e.getMessage());
                                        }
                                    }
                                });
              /*  query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> itemList, ParseException e) {
                        if (e == null) {

                            if (itemList.)
                                bname = new ParseObject[itemList.size()];
                            bname = itemList.toArray(bname);
                            adapter ad = new adapter(context, bname);
                            lv.setAdapter(ad);
                        } else {
                            Log.d("item", "Error: " + e.getMessage());
                        }
                    }
                });*/



		//l = db.getallbooksincartList();
		//bname = new Cartbook[l.size()];
		//bname = l.toArray(bname);
		Button b = (Button) rootview.findViewById(R.id.order_now);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	db.change_status(bname, l.size());
			//	prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
			//	prefs=getActivity().getSharedPreferences("user detail",Context.MODE_PRIVATE);
			//	        logged=prefs.getString("roll", "");
			//	        User u = db.getuser(logged);
                DBAdapter db = new DBAdapter(context);
				//db.addbuyer();

				FragmentManager fm=getActivity().getSupportFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				Order_success fr1=new Order_success();
                ft.replace(R.id.fl,fr1);
			//	ft.replace(android.R.id.content,fr1,"frag1");
				//ft.addToBackStack("TAG");
                ft.addToBackStack(null);
				ft.commit();
			}
		});



		return rootview;
	}
class adapter extends ArrayAdapter<ParseObject>{
		
		Context context;
		ParseObject barray[];
		public adapter(Context c, ParseObject btitles[]) {
			// TODO Auto-generated constructor stub
			super(c, R.layout.newcartrow,btitles);
			this.context = c;
			this.barray=btitles; 
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.newcartrow, parent,false);
			//View row = convertView;
			//LayoutInflater li;
			//li = LayoutInflater.from(getContext());
			//row = li.inflate(R.layout.rowview,null);
//			final TextView textView1 = (TextView) row.findViewById(R.id.tvcode1);
//			textView1.setText(barray[position].getString("Book_code"));
			final TextView textView2 = (TextView) row.findViewById(R.id.tvname1);
			textView2.setText(barray[position].getString("Book_name"));
			final TextView textView3 = (TextView) row.findViewById(R.id.tvprice1);
			textView3.setText(barray[position].getString("Book_price"));
//			textView3.setTextColor(Color.RED);
			final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor1);
			textView4.setText(barray[position].getString("Book_author"));
			final ImageView imageview = (ImageView) row.findViewById(R.id.cart_image);
			DBAdapter db = new DBAdapter(context);
			//Book b = db.getbook(barray[position].getString("Book_code"));
			int resID = getResources().getIdentifier(barray[position].getString("Book_image"), "drawable",context.getPackageName() );
			imageview.setImageResource(resID);


//setting image of books from image class if available


            final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Book_details");
            query2.whereEqualTo("objectId",barray[position].getString("Book_code"));
            query2.countInBackground(new CountCallback() {
                @Override
                public void done(int i, ParseException e) {
                    if (i > 0) {
                        query2.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects, ParseException e) {
                                if(parseObjects.get(0).getString("image_status").equals("false")) {
                                    int resID = getResources().getIdentifier(parseObjects.get(0).getString("Book_image"), "drawable", context.getPackageName());
                                    //  Drawable dr = getDrawable(resID);
                                    imageview.setImageResource(resID);
                                }
                                else{
                                    ParseFile file = parseObjects.get(0).getParseFile("file");
                                    file.getDataInBackground(new GetDataCallback() {
                                        @Override
                                        public void done(byte[] bytes, ParseException e) {
                                            Bitmap bmp = BitmapFactory
                                                    .decodeByteArray(bytes, 0,bytes.length);
                                            imageview.setImageBitmap(bmp);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });


            ImageView iv2 = (ImageView) row.findViewById(R.id.iv_cross);
            iv2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Cart_details");
                    query1.whereEqualTo("Book_code",barray[position].getString("Book_code"));
                    ParseUser user = ParseUser.getCurrentUser();
                    String u = user.getObjectId();
                    query1.whereEqualTo("Current_user",u);
                    query1.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> parseObjects, ParseException e) {
                            if(e==null){
                                for(int i=0;i<parseObjects.size();i++){
                                    parseObjects.get(i).deleteInBackground();
                                }
                            }
                        }
                    });
                }
            });
			
						return row;
		}
}
	
}
