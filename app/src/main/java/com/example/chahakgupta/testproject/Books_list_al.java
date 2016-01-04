package com.example.chahakgupta.testproject;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Books_list_al extends FragmentActivity{
	private ListView lv;
	private List<Book> l;
	private ParseObject[] bname;
	DBAdapter db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books_list_al);
		DBAdapter db = new DBAdapter(this);
		lv = (ListView) findViewById(R.id.listView6);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> itemList, ParseException e) {
                if (e == null) {

                    bname = new ParseObject[itemList.size()];
                    bname = itemList.toArray(bname);
                    adapter ad =   new adapter(Books_list_al.this, bname);
                    lv.setAdapter(ad);
                } else {
                    Log.d("item", "Error: " + e.getMessage());
                }
            }
        });

		/*l = db.getallbooksinList();
		bname = new Book[l.size()];
		bname = l.toArray(bname);
		adapter ad =   new adapter(this, bname);
		lv.setAdapter(ad);*/
	}
	
class adapter extends ArrayAdapter<ParseObject>{
		
		Context context;
		ParseObject barray[];
		String userroll;
		public adapter(Context c, ParseObject btitles[]) {
			// TODO Auto-generated constructor stub
			super(c, R.layout.rowview_book,btitles);
			this.context = c;
			this.barray=btitles;
		}
		
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			LayoutInflater layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			final View row = layoutInflater.inflate(R.layout.rowview_book, parent,false);
			final TextView textView2 = (TextView) row.findViewById(R.id.book_code);
			textView2.setText(barray[position].getString("Book_code"));
			final TextView textView3 = (TextView) row.findViewById(R.id.book_name);
			textView3.setText(barray[position].getString("Book_name"));
			Button b = (Button) row.findViewById(R.id.show_book_al);
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i1 = new Intent(Books_list_al.this, Show_book_al.class);
					i1.putExtra("bookcode", barray[position].getString("Book_code"));
				//	Message.message(this, b);
					startActivity(i1);
				}
			});
			
						return row;
			
		}
}

}
