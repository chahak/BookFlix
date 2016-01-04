package com.example.chahakgupta.testproject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Book_al_delete extends Activity {
EditText et;
TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_al_delete);
		et = (EditText) findViewById(R.id.deletecode);
		tv = (TextView) findViewById(R.id.deleteinfo1);
	}

	public void deleteb1(View v){
		DBAdapter db = new DBAdapter(this);
		String code = et.getText().toString();
		Book b = db.getbook(code);
		Message.message(this,""+ b.code);
		db.deletebookbycode(code);
			tv.setText("deleted successfully");
		
		
	}
}
