package com.example.chahakgupta.testproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PagerFrag3 extends Fragment implements OnClickListener{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v =inflater.inflate(R.layout.pagerfrag3, container, false); 
		 Button b = (Button) v.findViewById(R.id.signin);
		 b.setOnClickListener(this);
		 Button b1 = (Button) v.findViewById(R.id.signup);
		// Button b2 = (Button) v.findViewById(R.id.admin);
		 b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), Signup.class);
				startActivity(i);
				
			}
		});

		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getActivity(), Signin.class);
		startActivity(i);
	}
	
	
}
