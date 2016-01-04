package com.example.chahakgupta.testproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DBAdapter1
{
	DBhelper1 helper;
	public DBAdapter1(Context c)
	{
		helper=new DBhelper1(c);
	}
	
	// for inserting username and password in database table DB_table
	public long insertdata(String book,String image)
	{
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DBhelper1.book, book);
		cv.put(DBhelper1.image, image);
		long id= sql_data.insert(DBhelper1.table,null ,cv);
		return id;
	}
	public String getbook(String code){
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper1.book , DBhelper1.image};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper1.table, cols,DBhelper1.book + " = '" + code + "'" , null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		int i1 = cursor.getColumnIndex(DBhelper1.book);
		String code1 = cursor.getString(i1);
		int i2 = cursor.getColumnIndex(DBhelper1.image);
		String name = cursor.getString(i2);
		cursor.close();
			//buff.append(cid + " " + cname + " " + cpass + "\n");
		return name;
	}
	
}
