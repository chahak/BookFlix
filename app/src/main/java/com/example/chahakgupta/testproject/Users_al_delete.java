package com.example.chahakgupta.testproject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Users_al_delete extends Activity {
EditText et;
TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users_al_delete);
		et = (EditText) findViewById(R.id.deleteroll);
		tv = (TextView) findViewById(R.id.deleteinfo);
	}

	public void deleteb(View v){
		DBAdapter db = new DBAdapter(this);
		String roll = et.getText().toString();
		User u = db.getuser(roll);
		Message.message(this,""+ u.name);
		db.deleteuser(u.rollno);
			tv.setText("deleted successfully");
		
		
	}
}
