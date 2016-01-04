package com.example.chahakgupta.testproject;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Seller_status extends Fragment{
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
		View rootview = inflater.inflate(R.layout.seller_status, container, false);
		DBAdapter db = new DBAdapter(getActivity().getApplicationContext());
        ctx = getActivity();
		//name =  db.getalldatainList().toArray();
		//name = show.getalldatainList().toArray(new String[l.size()]);
		lv = (ListView) rootview.findViewById(R.id.listView4);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        ParseUser user = ParseUser.getCurrentUser();
        query.whereEqualTo("Book_seller",user.getObjectId() );
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
			super(c, R.layout.sellerrowlayout,btitles);
			this.context = c;
			this.barray=btitles; 
			//this.u = u;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.sellerrowlayout, parent,false);
			//View row = convertView;
			//LayoutInflater li;
			//li = LayoutInflater.from(getContext());
			//row = li.inflate(R.layout.rowview,null);
			//final TextView textView1 = (TextView) row.findViewById(R.id.tvcode);
			//textView1.setText(barray[position].code);
			
		//	if(barray[position].seller.equals(u.rollno)){
				
			final TextView textView2 = (TextView) row.findViewById(R.id.tvname2);
			textView2.setText(barray[position].getString("Book_name"));
			final TextView textView3 = (TextView) row.findViewById(R.id.tvprice2);
			textView3.setText("Rs." + barray[position].getString("Book_price"));
			//textView3.setTextColor(Color.RED);
			final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor2);
			textView4.setText(barray[position].getString("Book_author"));
			final TextView textView5 = (TextView) row.findViewById(R.id.tvstatus2);
			textView5.setText(barray[position].getString("Book_status"));
            final Button button = (Button) row.findViewById(R.id.removeb_s);

            if(barray[position].getString("Book_status").equals("sold")) {
   //             textView5.setTextColor(Color.GREEN);
                button.setVisibility(View.GONE);
            }
            else {
 //               textView5.setTextColor(Color.RED);
                button.setVisibility(View.VISIBLE);
            }
			final ImageView imageview = (ImageView) row.findViewById(R.id.image2);
		//	DBAdapter db = new DBAdapter(context);
		//	Book b = db.getbook(barray[position].getString("Book_name"));
			int resID = getResources().getIdentifier(barray[position].getString("Book_image"), "drawable",context.getPackageName() );
			imageview.setImageResource(resID);
			//final TextView textView6 = (TextView) row.findViewById(R.id.tvbuyer2);
			//textView6.setText(barray[position].getString("Book_buyer"));
		//	}
			Button b2 = (Button)row.findViewById(R.id.removeb_s);
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

                    final ParseQuery q1 = ParseQuery.getQuery("Book_details");
                    q1.whereEqualTo("objectId", barray[position].getObjectId());
                    q1.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> parseObjects, ParseException e) {
                            if (e == null) {
                                parseObjects.get(0).deleteInBackground(new DeleteCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        FragmentManager fm=getFragmentManager();
                                        FragmentTransaction ft=fm.beginTransaction();
                                        Fragment fr = new Seller_status();
                                        ft.replace(R.id.fl1,fr);
                                        ft.commit();
                                    }
                                });


                            }
                        }
                    });

				}
			});
						return row;
		}
}

}
