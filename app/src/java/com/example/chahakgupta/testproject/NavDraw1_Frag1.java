package com.example.chahakgupta.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class NavDraw1_Frag1 extends Fragment{
    Context ctx;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.navdraw1_frag1, container, false);
		// TODO Auto-generated method stub
        ctx=getActivity();
        Button bb2 = (Button)root.findViewById(R.id.btn1_1);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Booklist_ndfrag fr = new Booklist_ndfrag();
                ft.replace(R.id.fl1,fr);
                ft.commit();*/
                Intent i5 = new Intent(ctx,Sell_Books.class);
                startActivity(i5);
            }
        });

        Button bb3 = (Button)root.findViewById(R.id.btn1_2);
        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm=getActivity().getSupportFragmentManager();//
                FragmentTransaction ft=fm.beginTransaction();
                Seller_status fr = new Seller_status();
                ft.replace(R.id.fl1,fr);
                ft.commit();
            }
        });


        Button bb5 = (Button)root.findViewById(R.id.btn1_3);
        bb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getActivity().finish();
            }
        });



        return root;
	}

}