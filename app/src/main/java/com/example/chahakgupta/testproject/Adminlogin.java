package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Adminlogin extends Activity {
	EditText e1,e2,e3,e4,e5,e6;
	TextView tv;
	SharedPreferences.Editor editor;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminlogin);
		sp=getSharedPreferences("admin detail",Context.MODE_PRIVATE);
		editor=sp.edit();
		e1 = (EditText)findViewById(R.id.adminname);
		e2 = (EditText)findViewById(R.id.adminpass);
		tv = (TextView)findViewById(R.id.admininfo);
		
	}
	public void adminsign(View v){
		String admin = e1.getText().toString();
		String pass = e2.getText().toString();
		if(pass.equals("password")){
			savedata(admin,pass);
			tv.setText("");
			Intent i = new Intent(this,Admin_main.class);
			startActivity(i);
		}
		else
		{
			tv.setText("Enter correct password!");
			tv.setTextColor(Color.RED);

		}
	}
	private void savedata(String admin, String pass) {
		// TODO Auto-generated method stub
		editor.clear();
		editor.putString("roll", admin);
		editor.putString("password", pass);
		editor.putString("remember", "yes");
		editor.commit();
	}
		
}
