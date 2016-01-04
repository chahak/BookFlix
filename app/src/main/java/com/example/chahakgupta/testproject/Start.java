package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.parse.ParseUser;

public class Start extends Activity {

	 SharedPreferences.Editor editor;
		SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
	}

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.start);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void buyer(View v){
		//Intent i = getIntent();
		//String userroll = i.getStringExtra("seller_roll");
		Intent i1 = new Intent(this, NavDraw.class);
		//i1.putExtra("seller_roll", userroll);
		startActivity(i1);
	}

	public void seller(View v){
		//Intent i = getIntent();
		//String userroll = i.getStringExtra("seller_roll");
		Intent i2 = new Intent(this, NavDraw1.class);
		//i2.putExtra("seller_roll", userroll);
		startActivity(i2);
	}

    public void test(View v){
        //Intent i = getIntent();
        //String userroll = i.getStringExtra("seller_roll");
        Intent i2 = new Intent(this, Test.class);
        //i2.putExtra("seller_roll", userroll);
        startActivity(i2);
    }

	public void logout(View v){
        ////
		sp=getSharedPreferences("user detail",Context.MODE_PRIVATE);
			editor=sp.edit();
			editor.clear();
			editor.commit();
			Intent i3 = new Intent(this, Main.class);
			startActivity(i3);
        ////
        ParseUser.logOut();
        Intent i6 = new Intent(this,Signin.class);
        i6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
     //   startActivity(i6);
	}

    @Override
    public void onBackPressed() {
        backbuttonhandler();
    }

    private void backbuttonhandler() {
        // TODO Auto-generated method stub

            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Exit");
            ad.setMessage("Are you sure you want to leave application?");
            ad.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
            ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.cancel();
                }
            });
            ad.show();

    }
}
