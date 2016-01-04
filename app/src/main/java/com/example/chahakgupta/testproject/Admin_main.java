package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Admin_main extends Activity {

	SharedPreferences.Editor editor;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_main);
	}

	public void users(View v){
		//Intent i = getIntent();
		//String userroll = i.getStringExtra("seller_roll");
		Intent i1 = new Intent(this, Users_al_nav.class);
		//i1.putExtra("seller_roll", userroll);
		startActivity(i1);
	}

	public void books(View v){
		//Intent i = getIntent();
		//String userroll = i.getStringExtra("seller_roll");
		Intent i2 = new Intent(this, Books_al_nav.class);
		//i2.putExtra("seller_roll", userroll);
		startActivity(i2);
	}

	public void logout_admin(View v){
		sp=getSharedPreferences("admin detail",Context.MODE_PRIVATE);
			editor=sp.edit();
			editor.clear();
			editor.commit();
			Intent i3 = new Intent(this, Main.class);
			startActivity(i3);
	}
	
}
