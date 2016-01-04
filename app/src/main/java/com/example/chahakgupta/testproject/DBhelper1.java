package com.example.chahakgupta.testproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBhelper1 extends SQLiteOpenHelper {

	private static final String DB_name="MyDb1";
	static final String table="images";
	//User  user = new User();
	
	private static final int version = 2;
	//details for User_table
	static final String row_id="id";
	static final String book="bookid";
	static final String image="path";

	Context ctx;
	
	static final String data_create ="CREATE TABLE " + table + " ("+row_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ book + " VARCHAR(255), "+ image +" VARCHAR(255))";
	
	//static final String data_create ="create table contacts id integer primary key autoincrement," +
		//	" name text not null, email text not null)";
	static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ table;

	public DBhelper1(Context context) {
		super(context, DB_name, null, version);
		this.ctx=context;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(data_create);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Message.message(ctx, ""+e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(DROP_TABLE);
			onCreate(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Message.message(ctx, ""+e);
		}

	}
}
