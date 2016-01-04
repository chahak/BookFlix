package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Show_book_al extends Activity {
    //DBAdapter db;
    //Book b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //rootview = inflater.inflate(R.layout.show_book, container, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_book_al);
        Intent i = getIntent();
        //Bundle data = this.getArguments();
        String code = i.getStringExtra("bookcode");
        // DBAdapter db = new DBAdapter(this);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Book_details");
        query.whereEqualTo("Book_code", code);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {

                    //Book b = db.getbook(code);
                    //Message.message(getActivity(),code);
                    TextView tv1 = (TextView) findViewById(R.id.code_al);
                    tv1.setText(parseObjects.get(0).getString("Book_code"));
                    TextView tv2 = (TextView) findViewById(R.id.name_al);
                    tv2.setText(parseObjects.get(0).getString("Book_name"));
                    TextView tv3 = (TextView) findViewById(R.id.publisher_al);
                    tv3.setText(parseObjects.get(0).getString("Book_publisher"));
                    TextView tv4 = (TextView) findViewById(R.id.price_al);
                    tv4.setText("Rs." + parseObjects.get(0).getString("Book_price"));
                    TextView tv5 = (TextView) findViewById(R.id.author_al);
                    tv5.setText(parseObjects.get(0).getString("Book_author"));
                    TextView tv6 = (TextView) findViewById(R.id.condition_al);
                    tv6.setText(parseObjects.get(0).getString("Book_condition"));
                    TextView tv7 = (TextView) findViewById(R.id.status_al);
                    tv7.setText(parseObjects.get(0).getString("Book_status"));
                    TextView tv8 = (TextView) findViewById(R.id.buyer_al);
                    tv8.setText(parseObjects.get(0).getString("Book_buyer"));
                    TextView tv9 = (TextView) findViewById(R.id.seller_al);
                    tv9.setText(parseObjects.get(0).getString("Book_seller"));
                    final ImageView imageview = (ImageView) findViewById(R.id.iv2);
                    int resID = getResources().getIdentifier(parseObjects.get(0).getString("Book_image"), "drawable", "com.thapar.bookbazar");
                    imageview.setImageResource(resID);
                }
            }
        });
    }
}

		/*Button back = (Button)rootview.findViewById(R.id.back_button);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*NavDrawFrag2 f2 = new NavDrawFrag2();
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				ft.replace(R.id.fl, f2);
				ft.commit();

 				Intent i5 = new Intent(this, NavDrawFrag2.class);
 				startActivity(i5);
			}
		});*/


