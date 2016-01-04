package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Seller extends Fragment {
	//DBAdapter db;
	//Book b;
	//private View rootview;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreate(savedInstanceState);
	//	setContentView(R.layout.seller);
        final View rootview = inflater.inflate(R.layout.seller, container, false);
		//Intent i2 = getIntent();

		//String userroll = i2.getStringExtra("username");
        Bundle data2 = this.getArguments();
        String userroll = data2.getString("username");
       // Message.message(this,userroll);
	//	DBAdapter db = new DBAdapter(this);
	//	User u= db.getuserfromname(userroll);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
       // ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("objectId", userroll);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseObjects, ParseException e) {
                //Message.message(getActivity(),code);
                if (e == null) {
                    TextView tv1 = (TextView) rootview.findViewById(R.id.roll_u);
                    tv1.setText(parseObjects.get(0).getNumber("Rollnum").toString());
                    TextView tv2 = (TextView) rootview.findViewById(R.id.name_u);
                    tv2.setText(parseObjects.get(0).getUsername());
                    TextView tv3 = (TextView) rootview.findViewById(R.id.branch_u);
                    tv3.setText(parseObjects.get(0).getString("branch"));
                    TextView tv4 = (TextView) rootview.findViewById(R.id.year_u);
                    tv4.setText(parseObjects.get(0).getString("year"));
                }

            }
        });
	/*	Button back = (Button)rootview.findViewById(R.id.back_button_u);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*NavDrawFrag2 f2 = new NavDrawFrag2();
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				ft.replace(R.id.fl, f2);
				ft.commit();

 				Intent i5 = new Intent(getActivity(),NavDrawFrag2.class);
 				startActivity(i5);
			}
		});*/
        return  rootview;
            }

        }
