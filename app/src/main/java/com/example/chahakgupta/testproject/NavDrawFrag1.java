package com.example.chahakgupta.testproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NavDrawFrag1 extends Fragment{
    Context ctx;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.navdrawfrag1, container, false);
		// TODO Auto-generated method stub
        ctx=getActivity();
        ImageView search = (ImageView) root.findViewById(R.id.search_button);
        final EditText et = (EditText)root.findViewById(R.id.search_et);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle d = new Bundle();
                d.putString("result",et.getText().toString());
                FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Search_book fr = new Search_book();
                fr.setArguments(d);
                ft.replace(R.id.fl,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        Button bb2 = (Button)root.findViewById(R.id.btn2);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Booklist_ndfrag fr = new Booklist_ndfrag();
                ft.replace(R.id.fl,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button bb3 = (Button)root.findViewById(R.id.btn3);
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Cartlist_ndfr fr = new Cartlist_ndfr();
                ft.replace(R.id.fl,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button bb4 = (Button)root.findViewById(R.id.btn4);
        bb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Buyer_status fr = new Buyer_status();
                ft.replace(R.id.fl,fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        Button bb5 = (Button)root.findViewById(R.id.btn5);
        bb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().finish();
            }
        });



        return root;
	}

}