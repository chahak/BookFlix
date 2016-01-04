package com.example.chahakgupta.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Buyer extends Fragment {
	//DBAdapter db;
	//Book b;
	private View rootview;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootview = inflater.inflate(R.layout.buyer, container, false);
		Bundle data2 = this.getArguments();
		String buyer = data2.getString("book_buyer");
		//prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		//prefs=getActivity().getSharedPreferences("user detail",Context.MODE_PRIVATE);
		 //logged=prefs.getString("roll", "");

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username",buyer );
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> itemList, ParseException e) {
                if (e == null) {

                    TextView tv1 = (TextView) rootview.findViewById(R.id.roll_buy);
                    tv1.setText(itemList.get(0).getNumber("Rollnum").toString());
                    TextView tv2 = (TextView) rootview.findViewById(R.id.name_buy);
                    tv2.setText(itemList.get(0).getUsername());
                    TextView tv3 = (TextView) rootview.findViewById(R.id.branch_buy);
                    tv3.setText(itemList.get(0).getString("branch"));
                    TextView tv4 = (TextView) rootview.findViewById(R.id.year_buy);
                    tv4.setText(itemList.get(0).getString("year"));
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });

	/*	DBAdapter db = new DBAdapter(getActivity().getApplicationContext());
		Book b= db.getbook(buyer);
		User u = db.getuserfromname(b.buyer);*/
		//Message.message(getActivity(),code);

		Button back = (Button)rootview.findViewById(R.id.back_button_buy);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Seller_status f2 = new Seller_status();
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
 				//Intent i5 = new Intent(getActivity(),Booklist_ndfrag.class);
 				//startActivity(i5);
				ft.replace(R.id.fl1, f2);
				ft.commit();
			}
		});
		return rootview;
	}

}
