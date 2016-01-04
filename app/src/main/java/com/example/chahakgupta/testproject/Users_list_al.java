package com.example.chahakgupta.testproject;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Users_list_al extends FragmentActivity{
	private ListView lv;
	private List<User> l;
	private User[] uname;
	DBAdapter db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users_list_al);
		DBAdapter db = new DBAdapter(this);
		lv = (ListView) findViewById(R.id.listView5);
		l = db.getalluusersinList();
		uname = new User[l.size()];
		uname = l.toArray(uname);
		adapter ad =   new adapter(this, uname);
		lv.setAdapter(ad);
	}
	
class adapter extends ArrayAdapter<User>{
		
		Context context;
		User barray[];
		String userroll;
		public adapter(Context c, User btitles[]) {
			// TODO Auto-generated constructor stub
			super(c, R.layout.rowview_user,btitles);
			this.context = c;
			this.barray=btitles;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.rowview_user, parent,false);
			final TextView textView2 = (TextView) row.findViewById(R.id.user_rollno);
			textView2.setText(barray[position].name);
			final TextView textView3 = (TextView) row.findViewById(R.id.user_name);
			textView3.setText(barray[position].rollno);
			Button b = (Button) row.findViewById(R.id.show_user);
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i1 = new Intent(Users_list_al.this, Userdetail.class);
					i1.putExtra("pos", barray[position].rollno);
				//	Message.message(this, b);
					startActivity(i1);
				}
			});
			
						return row;
			
		}
}

}
