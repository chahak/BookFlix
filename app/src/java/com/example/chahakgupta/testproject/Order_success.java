package com.example.chahakgupta.testproject;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Order_success extends Fragment{
	EditText e1,e2;
    String address,phone;
    Button b1;
    Context context;
    DBAdapter db;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View rootview = inflater.inflate(R.layout.order_success, container, false);
		//db = new DBAdapter(getActivity());
        context = getActivity();
        e1=(EditText)rootview.findViewById(R.id.u_add);
        e2=(EditText) rootview.findViewById(R.id.u_phn);
        b1=(Button)rootview.findViewById(R.id.confirm_now);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  DBAdapter db = new DBAdapter(context);
                address = e1.getText().toString();
                phone = e2.getText().toString();
                final ParseQuery q1 = ParseQuery.getQuery("User_details");
                q1.whereEqualTo("user",ParseUser.getCurrentUser().getObjectId());
                q1.countInBackground(new CountCallback() {
                    @Override
                    public void done(int i, ParseException e) {
                        if(i>0){
                            q1.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> parseObjects, ParseException e) {
                                    if(e==null) {
                                        parseObjects.get(0).put("address",address);
                                        parseObjects.get(0).put("mobile",phone);
                                        parseObjects.get(0).saveInBackground();
                                    }
                                }
                            });
                        }
                        else{
                            ParseObject user_detail = new ParseObject("User_details");
                            user_detail.put("user", ParseUser.getCurrentUser().getObjectId());
                            user_detail.put("address", address);
                            user_detail.put("mobile", phone);
                            user_detail.saveInBackground();
                        }
                    }
                });

                db = new DBAdapter(context);
                db.addbuyer();
                db.deletecart();
                Message.message(context, "order confirmed");
                getFragmentManager().popBackStack();
            }
        });
		//db.deletecart();
		return rootview;
	}
	
}
