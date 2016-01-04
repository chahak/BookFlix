package com.example.chahakgupta.testproject;



import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class DBAdapter
{
	DBhelper helper;
    Context context;
	public DBAdapter(Context c)
	{
		helper=new DBhelper(c);
        context = c;
	}
	
	// for inserting username and password in database table DB_table
	public void insertdata(String roll,String name,String pass,String branch,String year,String email)
	{
        ParseUser user = new ParseUser();
        user.setUsername(roll);
        user.setPassword(pass);
       user.setEmail(email);

// other fields can be set just like with ParseObject
        user.put("name", name);
        user.put("branch",branch);
        user.put("password1",pass);
        user.put("year",year);
      // user.put("Email",email);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });

      /*  ParseObject testObject = new ParseObject("User");
        testObject.put("username", name);
        testObject.put("password", pass);
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });*/

	}
	public long insertbookdata(String name,String publisher,String author,String price,String condition,String status,String image,String seller,String buyer,int flag,ParseFile file,String image_change)
	{
        final ParseObject bookObject = new ParseObject("Book_details");
        final String[] book_code = new String[1];
        book_code[0]="";
        bookObject.put("Book_name", name);
       // bookObject.put("Book_code", code);
        bookObject.put("Book_publisher", publisher);
        bookObject.put("Book_author",author);
        bookObject.put("Book_price", price);
        bookObject.put("Book_condition", condition);
        bookObject.put("Book_status", status);
        bookObject.put("Book_image", image);
        bookObject.put("Book_seller", seller);
        bookObject.put("Book_buyer", buyer);
        bookObject.put("image_status",image_change);
        if(image_change.equals("true")){
            bookObject.put("file",file);
        }
        bookObject.put("flag",flag);
        bookObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                    //    book_code[0] =bookObject.getObjectId();
                    //Message.message(context,book_code[0]);
                     //bookObject.put("Book_code", book_code[0]);
                //bookObject.saveInBackground();
            }
        });

		/*SQLiteDatabase sql_data = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DBhelper.b_code, code);
		cv.put(DBhelper.b_name, name);
		cv.put(DBhelper.b_publisher, publisher);
		cv.put(DBhelper.b_author,author);
		cv.put(DBhelper.b_price, price);
		cv.put(DBhelper.b_condition, condition);
		cv.put(DBhelper.b_status, status);
		cv.put(DBhelper.b_image, image);
		cv.put(DBhelper.b_seller, seller);
		cv.put(DBhelper.b_buyer, buyer);
		long id= sql_data.insert(DBhelper.Book_table,null ,cv);*/
        long id =1;
		return id;
	}
	

	public void insertcartdata(String code,String name,String publisher,String author,String price,String image,String user)
	{

        ParseObject bookObject = new ParseObject("Cart_details");

        bookObject.put("Book_name", name);
        bookObject.put("Book_code", code);
        bookObject.put("Book_publisher", publisher);
        bookObject.put("Book_author",author);
        bookObject.put("Book_price", price);
        bookObject.put("Book_image", image);
        bookObject.put("Current_user", user);
        bookObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });


	/*	SQLiteDatabase sql_data = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DBhelper.b_code, code);
		cv.put(DBhelper.b_name, name);
		cv.put(DBhelper.b_publisher, publisher);
		cv.put(DBhelper.b_author,author);
		cv.put(DBhelper.b_price, price);
	//	cv.put(DBhelper.b_condition, condition);
		sql_data.insert(DBhelper.Cart_table,null ,cv);*/
	}
	public String checkdata(String x, String y){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("Rollnum", x);
        final String[] b = new String[1];
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> u, ParseException e) {
                if (e == null) {
                    // Log.d("score", "Retrieved " + u.size() + " scores");
                    if (u.size() > 0) {
                        b[0] = "true";
                        Log.d("hello", b[0] + " is result");
                    } else {
                        b[0] = "false";
                        Log.d("hello", b[0] + " is result");
                    }
                } else {
                    //Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


		/*SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.u_id};
		Cursor cursor = sql_data.query(DBhelper.User_table, cols, DBhelper.rollno + " = '" + x + "' AND " + DBhelper.password + " = '" + y + "'", null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount()>0)
			b = "true";
		else 
			b="false";
			//buff.append("true");
		cursor.close();*/
		return b[0];
	}
	public User getuser(String userroll) {
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.u_id , DBhelper.rollno,DBhelper.name,DBhelper.password,DBhelper.branch,DBhelper.year};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.User_table, cols,DBhelper.rollno + " = '" + userroll + "'" , null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		int i1 = cursor.getColumnIndex(DBhelper.u_id);
		String id = cursor.getString(i1);
		int i2 = cursor.getColumnIndex(DBhelper.rollno);
		String name = cursor.getString(i2);
		int i3 = cursor.getColumnIndex(DBhelper.name);
		String rollno = cursor.getString(i3);
		int i4 = cursor.getColumnIndex(DBhelper.password);
		String password = cursor.getString(i4);
		int i5 = cursor.getColumnIndex(DBhelper.branch);
		String branch = cursor.getString(i5);
		int i6 = cursor.getColumnIndex(DBhelper.year);
		String year = cursor.getString(i6);
		cursor.close();
		User u = new User(rollno,name,password,branch,year);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
		return u;
	}
	public User getuserfromname(String userroll) {
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.u_id , DBhelper.rollno,DBhelper.name,DBhelper.password,DBhelper.branch,DBhelper.year};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.User_table, cols,DBhelper.name + " = '" + userroll + "'" , null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		int i1 = cursor.getColumnIndex(DBhelper.u_id);
		String id = cursor.getString(i1);
		int i2 = cursor.getColumnIndex(DBhelper.rollno);
		String name = cursor.getString(i2);
		int i3 = cursor.getColumnIndex(DBhelper.name);
		String rollno = cursor.getString(i3);
		int i4 = cursor.getColumnIndex(DBhelper.password);
		String password = cursor.getString(i4);
		int i5 = cursor.getColumnIndex(DBhelper.branch);
		String branch = cursor.getString(i5);
		int i6 = cursor.getColumnIndex(DBhelper.year);
		String year = cursor.getString(i6);
		cursor.close();
		User u = new User(rollno,name,password,branch,year);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
		return u;
	}


	
	public List<Book> getallbooksinList(){
		List<Book> l = new ArrayList<Book>();
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.row_id1, DBhelper.b_code , DBhelper.b_name,DBhelper.b_publisher,DBhelper.b_price,DBhelper.b_author,DBhelper.b_condition,DBhelper.b_status,DBhelper.b_image,DBhelper.b_seller,DBhelper.b_buyer};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.Book_table, cols,null , null, null, null, DBhelper.b_code);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		do{
			int i1 = cursor.getColumnIndex(DBhelper.b_code);
			String code = cursor.getString(i1);
			int i2 = cursor.getColumnIndex(DBhelper.b_name);
			String name = cursor.getString(i2);
			int i3 = cursor.getColumnIndex(DBhelper.b_publisher);
			String publisher = cursor.getString(i3);
			int i4 = cursor.getColumnIndex(DBhelper.b_price);
			String price = cursor.getString(i4);
			int i5 = cursor.getColumnIndex(DBhelper.b_author);
			String author = cursor.getString(i5);
			int i6 = cursor.getColumnIndex(DBhelper.b_condition);
			String condition = cursor.getString(i6);
			int i7 = cursor.getColumnIndex(DBhelper.b_status);
			String status = cursor.getString(i7);
			int i8 = cursor.getColumnIndex(DBhelper.b_image);
			String image = cursor.getString(i8);
			int i9 = cursor.getColumnIndex(DBhelper.b_seller);
			String seller = cursor.getString(i9);
			int i10 = cursor.getColumnIndex(DBhelper.b_buyer);
			String buyer = cursor.getString(i10);
			Book b = new Book(code, name, publisher, price, author, condition,status,image,seller,buyer);
			l.add(b);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
			}while(cursor.moveToNext());
		return l;
	}
	
	public List<User> getalluusersinList() {
		// TODO Auto-generated method stub
		List<User> l = new ArrayList<User>();
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.u_id , DBhelper.rollno,DBhelper.name,DBhelper.password,DBhelper.branch,DBhelper.year};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.User_table, cols,null , null, null,null, DBhelper.rollno);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		do{
		int i1 = cursor.getColumnIndex(DBhelper.u_id);
		String id = cursor.getString(i1);
		int i2 = cursor.getColumnIndex(DBhelper.rollno);
		String name = cursor.getString(i2);
		int i3 = cursor.getColumnIndex(DBhelper.name);
		String rollno = cursor.getString(i3);
		int i4 = cursor.getColumnIndex(DBhelper.password);
		String password = cursor.getString(i4);
		int i5 = cursor.getColumnIndex(DBhelper.branch);
		String branch = cursor.getString(i5);
		int i6 = cursor.getColumnIndex(DBhelper.year);
		String year = cursor.getString(i6);
		User u = new User(rollno,name,password,branch,year);
		l.add(u);
	}while(cursor.moveToNext());
			//buff.append(cid + " " + cname + " " + cpass + "\n");
		return l;
	}


	public List<Book> getallbooksinsellerList(String u){
		List<Book> l = new ArrayList<Book>();
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.row_id1, DBhelper.b_code , DBhelper.b_name,DBhelper.b_publisher,DBhelper.b_price,DBhelper.b_author,DBhelper.b_condition,DBhelper.b_status,DBhelper.b_image,DBhelper.b_seller,DBhelper.b_buyer};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.Book_table, cols,DBhelper.b_seller + " = '" + u + "'" , null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		do{
			int i1 = cursor.getColumnIndex(DBhelper.b_code);
			String code = cursor.getString(i1);
			int i2 = cursor.getColumnIndex(DBhelper.b_name);
			String name = cursor.getString(i2);
			int i3 = cursor.getColumnIndex(DBhelper.b_publisher);
			String publisher = cursor.getString(i3);
			int i4 = cursor.getColumnIndex(DBhelper.b_price);
			String price = cursor.getString(i4);
			int i5 = cursor.getColumnIndex(DBhelper.b_author);
			String author = cursor.getString(i5);
			int i6 = cursor.getColumnIndex(DBhelper.b_condition);
			String condition = cursor.getString(i6);
			int i7 = cursor.getColumnIndex(DBhelper.b_status);
			String status = cursor.getString(i7);
			int i8 = cursor.getColumnIndex(DBhelper.b_image);
			String image = cursor.getString(i8);
			int i9 = cursor.getColumnIndex(DBhelper.b_seller);
			String seller = cursor.getString(i9);
			int i10 = cursor.getColumnIndex(DBhelper.b_buyer);
			String buyer = cursor.getString(i10);
			Book b = new Book(code, name, publisher, price, author, condition,status,image,seller,buyer);
			l.add(b);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
			}while(cursor.moveToNext());
		return l;
	}
	
	public List<Cartbook> getallbooksincartList(){
		List<Cartbook> l = new ArrayList<Cartbook>();
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.row_id1, DBhelper.b_code , DBhelper.b_name,DBhelper.b_publisher,DBhelper.b_price,DBhelper.b_author};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.Cart_table, cols, null, null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		do{
			int i1 = cursor.getColumnIndex(DBhelper.b_code);
			String code = cursor.getString(i1);
			int i2 = cursor.getColumnIndex(DBhelper.b_name);
			String name = cursor.getString(i2);
			int i3 = cursor.getColumnIndex(DBhelper.b_publisher);
			String publisher = cursor.getString(i3);
			int i4 = cursor.getColumnIndex(DBhelper.b_price);
			String price = cursor.getString(i4);
			int i5 = cursor.getColumnIndex(DBhelper.b_author);
			String author = cursor.getString(i5);
			//int i6 = cursor.getColumnIndex(DBhelper.b_condition);
			//String condition = cursor.getString(i6);
			Cartbook b = new Cartbook(code, name, publisher, price, author);
			l.add(b);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
			}while(cursor.moveToNext());
		return l;
	}

	public Book getbook(String code){
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.b_code , DBhelper.b_name,DBhelper.b_publisher,DBhelper.b_price,DBhelper.b_author,DBhelper.b_condition,DBhelper.b_status,DBhelper.b_image,DBhelper.b_seller,DBhelper.b_buyer};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.Book_table, cols,DBhelper.b_code + " = '" + code + "'" , null, null, null, null);
		//startManagingCursor(cursor);
		cursor.moveToFirst();
		int i1 = cursor.getColumnIndex(DBhelper.b_code);
		String code1 = cursor.getString(i1);
		int i2 = cursor.getColumnIndex(DBhelper.b_name);
		String name = cursor.getString(i2);
		int i3 = cursor.getColumnIndex(DBhelper.b_publisher);
		String publisher = cursor.getString(i3);
		int i4 = cursor.getColumnIndex(DBhelper.b_price);
		String price = cursor.getString(i4);
		int i5 = cursor.getColumnIndex(DBhelper.b_author);
		String author = cursor.getString(i5);
		int i6 = cursor.getColumnIndex(DBhelper.b_condition);
		String condition = cursor.getString(i6);
		int i7 = cursor.getColumnIndex(DBhelper.b_status);
		String status = cursor.getString(i7);
		int i8 = cursor.getColumnIndex(DBhelper.b_image);
		String image = cursor.getString(i8);
		int i9 = cursor.getColumnIndex(DBhelper.b_seller);
		String seller = cursor.getString(i9);
		int i10 = cursor.getColumnIndex(DBhelper.b_buyer);
		String buyer = cursor.getString(i10);
		cursor.close();
		Book b = new Book(code1, name, publisher, price, author, condition,status,image,seller,buyer);
			//buff.append(cid + " " + cname + " " + cpass + "\n");
		return b;
	}
	public String checkcart(String code){
		
		String result;
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.row_id1};
		Cursor cursor = sql_data.query(DBhelper.Cart_table, cols, DBhelper.b_code + " = '" + code + "'", null, null, null, null);
		cursor.moveToFirst();
		//cursor.moveToFirst();
		if(cursor.getCount()>0)
			result = "true";
		else 
			result="false";
		cursor.close();
		return result;
	}
	public String checkcartifempty(){
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String cols[] = {DBhelper.row_id1, DBhelper.b_code , DBhelper.b_name,DBhelper.b_publisher,DBhelper.b_price,DBhelper.b_author};
		//StringBuffer buff = new StringBuffer();
		Cursor cursor = sql_data.query(DBhelper.Cart_table, cols, null, null, null, null, null);
		//Cursor cursor = sql_data.rawQuery("SELECT COUNT(*) FROM Cart_table" , null);
		if(cursor.getCount()>0)
			return "no";
		else
			return "yes";
	}
	public void deletecart(){

		SQLiteDatabase sql_data = helper.getWritableDatabase();
		sql_data.delete(DBhelper.Cart_table, null, null);
	}
	public void deletebook(){

		SQLiteDatabase sql_data = helper.getWritableDatabase();
		sql_data.delete(DBhelper.Book_table, null, null);
	}
	

	public void change_status(Cartbook[] bname, int size) {
		// TODO Auto-generated method stub
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		for(int i=0;i<size;i++){
			Book b = getbook(bname[i].code);
			ContentValues cv = new ContentValues();
			cv.put(DBhelper.b_status, "sold");
			long id = sql_data.update(DBhelper.Book_table, cv,DBhelper.b_code + " = '" + b.code +"'" , null);
		}
	}

	public void addbuyer() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cart_details");
        ParseUser user = ParseUser.getCurrentUser();
        String u = user.getObjectId();
        query.whereEqualTo("Current_user",u);
        // TODO Auto-generated method stub
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> itemList, ParseException e) {
                if (e == null) {
                    //	SQLiteDatabase sql_data = helper.getWritableDatabase();
                    for (int i = 0; i < itemList.size(); i++) {
                        //Book b = getbook(bname[i].code);

                        String code = itemList.get(i).getString("Book_code");
                        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Cart_details");
                        query2.whereEqualTo("Book_code",code);
                        query2.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list2, ParseException e) {
                                if(e==null){
                                    for(int i=0;i< list2.size();i++){
                                        list2.get(i).deleteInBackground();
                                    }
                                }
                            }
                        });
                       // itemList.get(i).deleteInBackground();
                        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Book_details");
                        query1.whereEqualTo("objectId", code);
                        query1.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> parseObjects, ParseException e) {
                                if(e==null) {
                                    parseObjects.get(0).put("Book_status", "order under process");
                                    ParseUser currentuser = ParseUser.getCurrentUser();
                                    parseObjects.get(0).put("Book_buyer", currentuser.getObjectId());
                                    parseObjects.get(0).saveInBackground();
                                }
                            }
                        });

                    }
                }
            }
        });
    }

	public void deleteuser(String roll) {
		// TODO Auto-generated method stub
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String query = " DELETE FROM " + DBhelper.User_table + " WHERE " + DBhelper.name + "= '" + roll + "'";
		sql_data.execSQL(query);
		//long id = sql_data.delete(DBhelper.User_table, DBhelper.name + " = '" +roll + "'" , null);
		//return id;
	}

	public void deletebookbycode(String code) {
		// TODO Auto-generated method stub
		SQLiteDatabase sql_data = helper.getWritableDatabase();
		String query = " DELETE FROM " + DBhelper.Book_table + " WHERE " + DBhelper.b_code + "= '" + code + "'";
		sql_data.execSQL(query);
		//long id = sql_data.delete(DBhelper.User_table, DBhelper.name + " = '" +roll + "'" , null);
		//return id;
	}
	
	

	
}
