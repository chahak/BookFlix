package com.example.chahakgupta.testproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class User_profile extends Fragment {

Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.user_profile, container, false);
        ctx=getActivity();

        ParseQuery query = ParseUser.getQuery();
        query.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> parseObjects, ParseException e) {
                if(e==null){

                    final String code =parseObjects.get(0).getObjectId();
                    final String name = parseObjects.get(0).getString("name");
                    final String roll =parseObjects.get(0).getString("username");
                    final String email = parseObjects.get(0).getString("email");
                    final String branch = parseObjects.get(0).getString("branch");
                    final String year = parseObjects.get(0).getString("year");
                    TextView tv1 = (TextView) rootview.findViewById(R.id.name_p);
                    tv1.setText(name);
                    TextView tv2 = (TextView) rootview.findViewById(R.id.roll_p);
                    tv2.setText(roll);
                    TextView tv3 = (TextView) rootview.findViewById(R.id.email_p);
                    tv3.setText(email);
                    TextView tv4 = (TextView) rootview.findViewById(R.id.branch_p);
                    tv4.setText(branch);
                    TextView tv5 = (TextView) rootview.findViewById(R.id.year_p);
                    tv5.setText(year);
                    final ImageView imageview = (ImageView) rootview.findViewById(R.id.iv1);
                    final Button b1 = (Button) rootview.findViewById(R.id.edit_p);

                    //set image from image database if available


                    if(parseObjects.get(0).getString("image_status").equals("False")) {
                        int resID = getResources().getIdentifier("ic_action_group", "drawable", getActivity().getPackageName());
                        //  Drawable dr = getDrawable(resID);
                        imageview.setImageResource(resID);
                    }
                    else{
                        ParseFile file =parseObjects.get(0).getParseFile("file");
                        file.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                Bitmap bmp = BitmapFactory
                                        .decodeByteArray(bytes, 0, bytes.length);
                                imageview.setImageBitmap(bmp);
                            }
                        });
                    }




                }
            }
        });

        return rootview;
    }



}
