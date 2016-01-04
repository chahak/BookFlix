package com.example.chahakgupta.testproject;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Signup extends FragmentActivity {

	DBAdapter adapter;
	EditText e0,e1,e2,e3,e4,e5,e6,e7;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
       // Parse.enableLocalDatastore(this);

        //Parse.initialize(this, "cb0ypHVrFQtiOYoIDP2sVhwqp7ALB1cHrAUjBWUJ", "rINUwRI3gwgTbQ61oZNnUqraahcxJE5gv9BRyJAT");

        adapter = new DBAdapter(this);
      //  e0 =(EditText)findViewById(R.id.firstname);
		e1 = (EditText)findViewById(R.id.roll);
		e2 = (EditText)findViewById(R.id.user);
		e3 = (EditText)findViewById(R.id.pass);
		e4 = (EditText)findViewById(R.id.c_pass);
		e5 = (EditText)findViewById(R.id.branch);
		e6 = (EditText)findViewById(R.id.year);
        e7 = (EditText)findViewById(R.id.email);
		tv = (TextView) findViewById(R.id.info);
	}


	public void submit(View v) {
        // final String roll = Integer.parseInt( e1.getText().toString() );
        final String roll = e1.getText().toString().trim();
        //  String name = e0.getText().toString();
        final String username = e2.getText().toString().trim();
        final String pass = e3.getText().toString().trim();
        final String c_pass = e4.getText().toString().trim();
        final String branch = e5.getText().toString();
        final String year = e6.getText().toString();
        final String email = e7.getText().toString().trim();

        //check if required fields are filled
        if (TextUtils.isEmpty(roll) || TextUtils.isEmpty(username) || TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass)) {
            if (TextUtils.isEmpty(roll))
                e1.setError("Roll number required");
            if (TextUtils.isEmpty(username))
                e2.setError("Username required");
            if (TextUtils.isEmpty(email))
                e7.setError("Email id required");
            if (TextUtils.isEmpty(pass))
                e3.setError("Password required");
            return;
        }
        else {
//check if the user with same roll number registered
        ParseQuery query = ParseUser.getQuery();
        query.whereEqualTo("username", roll);
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int i, ParseException e) {
                if (i > 0) {
                    e2.setText("");
                    tv.setText("Roll Number is already registered");
                    tv.setTextColor(Color.RED);
                } else {
                    if (pass.equals(c_pass)) {

                        adapter.insertdata(roll, username, pass, branch, year, email);
                        //if(id<0)
                        //{
                        //	Message.message(this,"insertion unsuccessful");
                        //}
                        //else
                        //{
                        //	Message.message(this, "insertion successful" );
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Signupfrag fr1 = new Signupfrag();
                        ft.replace(android.R.id.content, fr1);
                        ft.commit();
                        //}
                    } else {
                        e4.setText("");
                        tv.setText("Wrong confirmation password");
                        tv.setTextColor(Color.RED);
                    }
                }
            }
        });

    }


		
	}
}
