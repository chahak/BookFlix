package com.example.chahakgupta.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.parse.SaveCallback;

import java.util.List;

public class Buyer_status extends Fragment{
	private ListView lv;
	private List<Book> l;
	private ParseObject[] bname;
	DBAdapter db;
    Context ctx;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootview = inflater.inflate(R.layout.buyer_status, container, false);
		DBAdapter db = new DBAdapter(getActivity().getApplicationContext());
        ctx = getActivity();
		//name =  db.getalldatainList().toArray();
		//name = show.getalldatainList().toArray(new String[l.size()]);
		lv = (ListView) rootview.findViewById(R.id.listView7);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        ParseUser user = ParseUser.getCurrentUser();
        query.whereEqualTo("Book_buyer",user.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> itemList, ParseException e) {
                if (e == null) {

                    bname = new ParseObject[itemList.size()];
                    bname = itemList.toArray(bname);
                    adapter ad = new adapter(ctx, bname);
                    lv.setAdapter(ad);
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });

	/*	prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		prefs=getActivity().getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        logged=prefs.getString("roll", "");
		        User u = db.getuser(logged);
		        Message.message(getActivity(), u.rollno);
		l = db.getallbooksinsellerList(u.rollno);
		
		bname = new Book[l.size()];
		bname = l.toArray(bname);
		
		//adapter ad =   new adapter(this.getActivity(), bname,u);
		lv.setAdapter(ad);*/
		return rootview;
	}
	
class adapter extends ArrayAdapter<ParseObject>{
		
		Context context;
		ParseObject barray[];
		User u;
		public adapter(Context c, ParseObject btitles[]) {
			// TODO Auto-generated constructor stub
			super(c, R.layout.rowview_buyerb,btitles);
			this.context = c;
			this.barray=btitles; 
			//this.u = u;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.rowview_buyerb, parent,false);
			//View row = convertView;
			//LayoutInflater li;
			//li = LayoutInflater.from(getContext());
			//row = li.inflate(R.layout.rowview,null);
			//final TextView textView1 = (TextView) row.findViewById(R.id.tvcode);
			//textView1.setText(barray[position].code);
			
		//	if(barray[position].seller.equals(u.rollno)){
				
			final TextView textView2 = (TextView) row.findViewById(R.id.tvname3);
			textView2.setText(barray[position].getString("Book_name"));
			final TextView textView3 = (TextView) row.findViewById(R.id.tvprice3);
			textView3.setText("Rs." + barray[position].getString("Book_price"));
			textView3.setTextColor(Color.RED);
			final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor3);
			textView4.setText(barray[position].getString("Book_author"));
            final TextView textView5 = (TextView) row.findViewById(R.id.tvstatus3);
            textView5.setText(barray[position].getString("Book_status"));
			final ImageView imageview = (ImageView) row.findViewById(R.id.image3);
		//	DBAdapter db = new DBAdapter(context);
		//	Book b = db.getbook(barray[position].getString("Book_name"));
		//	int resID = getResources().getIdentifier(barray[position].getString("Book_image"), "drawable",context.getPackageName() );
		//	imageview.setImageResource(resID);



            final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Book_details");
            query2.whereEqualTo("objectId",barray[position].getObjectId());
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
                                                    .decodeByteArray(bytes, 0, bytes.length);
                                            imageview.setImageBitmap(bmp);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });



            //	}
			Button b2 = (Button)row.findViewById(R.id.remove);
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

                   // Intent i = new Intent(getActivity(),Seller.class);
                    //i.putExtra("username",barray[position].getString("Book_seller"));
                    //startActivity(i);

                    final ParseQuery q1 = ParseQuery.getQuery("Book_details");
                    q1.whereEqualTo("objectId", barray[position].getObjectId());
                    q1.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> parseObjects, ParseException e) {
                            if (e == null) {
                                parseObjects.get(0).put("Book_status", "not sold");
                                parseObjects.get(0).put("Book_buyer", "null");
                                parseObjects.get(0).saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        FragmentManager fm=getFragmentManager();
                                        FragmentTransaction ft=fm.beginTransaction();
                                        Fragment fr = new Buyer_status();
                                        ft.replace(R.id.fl,fr);
                                        ft.commit();
                                    }
                                });
                            }
                        }
                    });
                    //Message.message(context, barray[position].seller);


				}
			});

						return row;
		}
}

}
