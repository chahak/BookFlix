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

public class Userdetail extends Activity {
	//DBAdapter db;
	//Book b;
	//private View rootview;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userdetail);
		Intent i2 = getIntent();
		String userroll = i2.getStringExtra("pos");
		DBAdapter db = new DBAdapter(this);
		User u= db.getuserfromname(userroll);
		//Message.message(getActivity(),code);
		TextView tv1 = (TextView) findViewById(R.id.roll_user);
		tv1.setText(u.name);
		TextView tv2 = (TextView) findViewById(R.id.name_user);
		tv2.setText(u.rollno);
		TextView tv3 = (TextView) findViewById(R.id.branch_user);
		tv3.setText(u.branch);
		TextView tv4 = (TextView) findViewById(R.id.year_user);
		tv4.setText(u.year);
	
	}

}
