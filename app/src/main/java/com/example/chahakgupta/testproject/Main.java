package com.example.chahakgupta.testproject;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class Main extends FragmentActivity{


	private ViewPager vp;
	private PagerAdapter mAdapter;
	SharedPreferences.Editor editor;
	SharedPreferences prefs;
	String Log;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

       /* ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

            }
        });*/


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        Log=prefs.getString("remember", "");



		//now check the value of shared pref and apply the condition like this
		Intent intent ;
		    if(Log.equalsIgnoreCase("yes"))
		            {
		                intent = new Intent(this, Start.class);
		                startActivity(intent);
		                finish();

		            }
		            else
		            {


			//now check the value of shared pref and apply the condition like this
			            	vp=(ViewPager) findViewById(R.id.vp);
			        		mAdapter=new PagerTabs(getSupportFragmentManager());
			        		vp.setAdapter(mAdapter);

                        ImageView img_page1 = (ImageView)findViewById(R.id.img1);
                        ImageView img_page2 = (ImageView)findViewById(R.id.img2);
                        ImageView img_page3 = (ImageView)findViewById(R.id.img3);
                        img_page1.setImageResource(R.drawable.selected_dot);
                        img_page2.setImageResource(R.drawable.unselected_dot);
                        img_page3.setImageResource(R.drawable.unselected_dot);
                        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                ImageView img_page1 = (ImageView)findViewById(R.id.img1);
                                ImageView img_page2 = (ImageView)findViewById(R.id.img2);
                                ImageView img_page3 = (ImageView)findViewById(R.id.img3);
                                switch (position) {
                                    case 0:
                                        img_page1.setImageResource(R.drawable.selected_dot);
                                        img_page2.setImageResource(R.drawable.unselected_dot);
                                        img_page3.setImageResource(R.drawable.unselected_dot);

                                        break;

                                    case 1:
                                        img_page1.setImageResource(R.drawable.unselected_dot);
                                        img_page2.setImageResource(R.drawable.selected_dot);
                                        img_page3.setImageResource(R.drawable.unselected_dot);

                                        break;

                                    case 2:
                                        img_page1.setImageResource(R.drawable.unselected_dot);
                                        img_page2.setImageResource(R.drawable.unselected_dot);
                                        img_page3.setImageResource(R.drawable.selected_dot);

                                        break;



                                    default:
                                        break;
                                }

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
		            }

	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        Log=prefs.getString("remember", "");


		//now check the value of shared pref and apply the condition like this
		Intent intent ;
		    if(Log.equalsIgnoreCase("yes"))
		            {
		                intent = new Intent(this, NavDraw.class);
		                startActivity(intent);
		                finish();

		            }
		            else
		            {


			//now check the value of shared pref and apply the condition like this

			            	vp=(ViewPager) findViewById(R.id.vp);
			        		mAdapter=new PagerTabs(getSupportFragmentManager());
			        		vp.setAdapter(mAdapter);

                        vp=(ViewPager) findViewById(R.id.vp);
                        mAdapter=new PagerTabs(getSupportFragmentManager());
                        vp.setAdapter(mAdapter);
                        ImageView img_page1 = (ImageView)findViewById(R.id.img1);
                        ImageView img_page2 = (ImageView)findViewById(R.id.img2);
                        ImageView img_page3 = (ImageView)findViewById(R.id.img3);
                        img_page1.setImageResource(R.drawable.selected_dot);
                        img_page2.setImageResource(R.drawable.unselected_dot);
                        img_page3.setImageResource(R.drawable.unselected_dot);
		            }
	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        Log=prefs.getString("remember", "");
		        

		//now check the value of shared pref and apply the condition like this
		Intent intent ;
		    if(Log.equalsIgnoreCase("yes"))
		            {
		                intent = new Intent(this, NavDraw.class);
		                startActivity(intent);
		                finish();

		            }
		            else
		            {
		            	
			    
			//now check the value of shared pref and apply the condition like this

			            	vp=(ViewPager) findViewById(R.id.vp);
			        		mAdapter=new PagerTabs(getSupportFragmentManager());
			        		vp.setAdapter(mAdapter);

                        vp=(ViewPager) findViewById(R.id.vp);
                        mAdapter=new PagerTabs(getSupportFragmentManager());
                        vp.setAdapter(mAdapter);
                        ImageView img_page1 = (ImageView)findViewById(R.id.img1);
                        ImageView img_page2 = (ImageView)findViewById(R.id.img2);
                        ImageView img_page3 = (ImageView)findViewById(R.id.img3);
                        img_page1.setImageResource(R.drawable.selected_dot);
                        img_page2.setImageResource(R.drawable.unselected_dot);
                        img_page3.setImageResource(R.drawable.unselected_dot);
		            }
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs=getSharedPreferences("user detail",Context.MODE_PRIVATE);
		        Log=prefs.getString("remember", "");

		//now check the value of shared pref and apply the condition like this
		Intent intent ;
		    if(Log.equalsIgnoreCase("yes"))
		            {
		                intent = new Intent(this, NavDraw.class);
		                startActivity(intent);
		                finish();

		            }
		            else
		            {
		            	
			    
			//now check the value of shared pref and apply the condition like this

			            	vp=(ViewPager) findViewById(R.id.vp);
			        		mAdapter=new PagerTabs(getSupportFragmentManager());
			        		vp.setAdapter(mAdapter);

                        vp=(ViewPager) findViewById(R.id.vp);
                        mAdapter=new PagerTabs(getSupportFragmentManager());
                        vp.setAdapter(mAdapter);
                        ImageView img_page1 = (ImageView)findViewById(R.id.img1);
                        ImageView img_page2 = (ImageView)findViewById(R.id.img2);
                        ImageView img_page3 = (ImageView)findViewById(R.id.img3);
                        img_page1.setImageResource(R.drawable.selected_dot);
                        img_page2.setImageResource(R.drawable.unselected_dot);
                        img_page3.setImageResource(R.drawable.unselected_dot);
		            }
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	//	backbuttonhandler();
		return;
	}

	private void backbuttonhandler() {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setTitle("Exit");
		ad.setMessage("Are you sure you want to leave application?");
		ad.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		ad.show();
	}
    public void server(View v){
        Intent i = new Intent(Main.this,Server1.class);
        startActivity(i);
    }
	
	
}
