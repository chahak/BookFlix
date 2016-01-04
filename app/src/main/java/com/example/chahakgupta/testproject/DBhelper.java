package com.example.chahakgupta.testproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBhelper extends SQLiteOpenHelper {

	private static final String DB_name="MyDb";
	static final String User_table="contacts";
	static final String Cart_table="cart";
	static final String Book_table="details";
	//User  user = new User();
	
	private static final int version = 6;
	//details for User_table
	static final String row_id="id";
	static final String u_id= "u_id";
	static final String rollno="rollno";
	static final String name="name";
	static final String password="pass";
	static final String branch="branch";
	static final String year="year";

	static final String row_id1="id";
	static final String b_code="code";
	static final String b_name ="name";
	static final String b_publisher="publisher";
	static final String b_price="price";
	static final String b_author="author";
	static final String b_condition="condition";
	static final String b_status="status";
	static final String b_image="image";
	static final String b_seller="seller";
	static final String b_buyer="buyer";

	Context ctx;
	
	static final String data_create ="CREATE TABLE " + User_table + " ("+u_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ rollno + " VARCHAR(255), "+ name +" VARCHAR(255), "+ password +" VARCHAR(255), "+ 
			branch +" VARCHAR(255), "+year +" VARCHAR(255))";
	static final String book_form = "CREATE TABLE " + Book_table + " ("+ row_id1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ b_code + " VARCHAR(255), "+ b_name +" VARCHAR(255), "+ b_publisher +
			" VARCHAR(255), "+ b_price +" VARCHAR(255), "+ b_author +" VARCHAR(255), "+ b_condition +" VARCHAR(255), "+
			b_status +" VARCHAR(255), "+ b_image +" VARCHAR(255), "+ b_seller +" VARCHAR(255), "+ b_buyer +" VARCHAR(255))";
	static final String order_create = "CREATE TABLE " + Cart_table + " ("+ row_id1 +" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ b_code + " VARCHAR(255), "+ b_name +" VARCHAR(255), "+ b_publisher +
			" VARCHAR(255), "+ b_price +" VARCHAR(255), "+ b_author +" VARCHAR(255))";
	
	//static final String data_create ="create table contacts id integer primary key autoincrement," +
		//	" name text not null, email text not null)";
	static final String DROP_TABLE = "DROP TABLE IF EXISTS "+User_table;
	static final String DROP_TABLE1 = "DROP TABLE IF EXISTS "+Cart_table;
			static final String DROP_TABLE2 = "DROP TABLE IF EXISTS "+Book_table;

	public DBhelper(Context context) {
		super(context, DB_name, null, version);
		this.ctx=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			db.execSQL(data_create);
			db.execSQL(book_form);
			db.execSQL(order_create);
	
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
			db.execSQL(DROP_TABLE1);
			db.execSQL(DROP_TABLE2);
			onCreate(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Message.message(ctx, ""+e);
		}

	}
}
