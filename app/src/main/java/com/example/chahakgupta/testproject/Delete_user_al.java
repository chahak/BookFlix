package com.example.chahakgupta.testproject;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Delete_user_al extends Fragment{
	private ListView lv;
	private List<Cartbook> l;
	private Cartbook[] bname;
	DBAdapter db;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String logged;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootview = inflater.inflate(R.layout.cartlistndfr, container, false);
		final DBAdapter db = new DBAdapter(getActivity());
		//name =  db.getalldatainList().toArray();
		//name = show.getalldatainList().toArray(new String[l.size()]);
		lv = (ListView) rootview.findViewById(R.id.listView3);
		
		if(db.checkcartifempty().equals("yes")){
			Message.message(this.getActivity(), "cart is empty");
		}
		else{
		l = db.getallbooksincartList();
		bname = new Cartbook[l.size()];
		bname = l.toArray(bname);
		Button b = (Button) rootview.findViewById(R.id.order_now);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				db.change_status(bname, l.size());
				prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
				prefs=getActivity().getSharedPreferences("user detail",Context.MODE_PRIVATE);
				        logged=prefs.getString("roll", "");
				        User u = db.getuser(logged);
				//db.addbuyer(bname,l.size(),u.rollno);
				FragmentManager fm=getActivity().getSupportFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				Order_success fr1=new Order_success();
				ft.replace(android.R.id.content,fr1,"frag1");
				ft.addToBackStack("TAG");
				ft.commit();
			}
		});
		adapter ad =   new adapter(this.getActivity(), bname);
		lv.setAdapter(ad);
		}
		return rootview;
	}
class adapter extends ArrayAdapter<Cartbook>{
		
		Context context;
		Cartbook barray[];
		public adapter(Context c, Cartbook btitles[]) {
			// TODO Auto-generated constructor stub
			super(c, R.layout.rowview_cart,btitles);
			this.context = c;
			this.barray=btitles; 
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.rowview_cart, parent,false);
			//View row = convertView;
			//LayoutInflater li;
			//li = LayoutInflater.from(getContext());
			//row = li.inflate(R.layout.rowview,null);
			final TextView textView1 = (TextView) row.findViewById(R.id.tvcode1);
			textView1.setText(barray[position].code);
			final TextView textView2 = (TextView) row.findViewById(R.id.tvname1);
			textView2.setText(barray[position].name);
			final TextView textView3 = (TextView) row.findViewById(R.id.tvprice1);
			textView3.setText(barray[position].price);
			textView3.setTextColor(Color.RED);
			final TextView textView4 = (TextView) row.findViewById(R.id.tvauthor1);
			textView4.setText(barray[position].author);
			
						return row;
		}
}
	
}
