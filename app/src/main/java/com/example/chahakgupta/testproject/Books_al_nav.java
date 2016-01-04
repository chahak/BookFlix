package com.example.chahakgupta.testproject;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Books_al_nav extends ActionBarActivity implements OnItemClickListener {
    private DrawerLayout draw_lay;
	private String[] titles1;
    private ListView draw_List;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private android.support.v4.app.ActionBarDrawerToggle dl;
    private Fragment fr;
    private android.support.v7.app.ActionBar ab;
    SharedPreferences.Editor editor;
	SharedPreferences sp;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books_al_nav);
		//**
		mTitle = mDrawerTitle = getTitle();
     draw_lay = (DrawerLayout) findViewById(R.id.drawl);
     draw_List = (ListView) findViewById(R.id.left_draw);
     titles1=getResources().getStringArray(R.array.items4);
     ab= getSupportActionBar();
     FragmentManager fm=getSupportFragmentManager();
	FragmentTransaction ft=fm.beginTransaction();
	fr= new Books_al_navfr1();
	ft.replace(R.id.fl4,fr).commit();
     draw_List.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles1));
     draw_List.setOnItemClickListener(new Click()); 
     
     dl = new android.support.v4.app.ActionBarDrawerToggle(
             this,                  /* host Activity */
             draw_lay,         /* DrawerLayout object */
             R.drawable.draw,  /* nav_drawer image to replace 'Up' caret */
             R.string.open_draw,  /* "open drawer" description for accessibility */
             R.string.close_draw  /* "close drawer" description for accessibility */
             ) {
         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onDrawerClosed(View view) {
        	 getActionBar().setTitle(mTitle);
             invalidateOptionsMenu();
         }

         
         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void onDrawerOpened(View drawerView) {
        	 getActionBar().setTitle(mDrawerTitle);
             invalidateOptionsMenu();
         }
     };
     draw_lay.setDrawerListener(dl);
     ab.setHomeButtonEnabled(true);
     ab.setDisplayHomeAsUpEnabled(true);//for creating a back arrow for home
     ab.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.draw));
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		  draw_lay = (DrawerLayout) findViewById(R.id.drawl);
		     draw_List = (ListView) findViewById(R.id.left_draw);
		     titles1=getResources().getStringArray(R.array.items4);
		     ab= getSupportActionBar();
		     FragmentManager fm=getSupportFragmentManager();
			FragmentTransaction ft=fm.beginTransaction();
			fr= new NavDrawFrag1();
			ft.replace(R.id.fl4,fr).commit();
		     draw_List.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles1));
		     draw_List.setOnItemClickListener(new Click()); 
		     
		     dl = new android.support.v4.app.ActionBarDrawerToggle(
		             this,                  /* host Activity */
		             draw_lay,         /* DrawerLayout object */
		             R.drawable.draw,  /* nav_drawer image to replace 'Up' caret */
		             R.string.open_draw,  /* "open drawer" description for accessibility */
		             R.string.close_draw  /* "close drawer" description for accessibility */
		             ) {
		         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
				public void onDrawerClosed(View view) {
		         }

		         
		         @TargetApi(Build.VERSION_CODES.HONEYCOMB)
				public void onDrawerOpened(View drawerView) {
		         }
		     };
		     draw_lay.setDrawerListener(dl);
		     ab.setHomeButtonEnabled(true);
		     ab.setDisplayHomeAsUpEnabled(true);//for creating a back arrow for home
		     ab.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.draw));
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {   //for syncing that 3 line icon
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		dl.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    dl.onConfigurationChanged(newConfig);
	}
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getActionBar().setTitle(mTitle);
	    }

    	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(dl.onOptionsItemSelected(item))
				{
			
				return true;
				}
		return super.onOptionsItemSelected(item);
	}
	private class Click implements ListView.OnItemClickListener{
		
		 public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
 	 		// TODO Auto-generated method stub
 	 		getItem(position);	 
  }	
	} 
 	    public void getItem(int position){
 	 	  FragmentManager fm=getSupportFragmentManager();
 			FragmentTransaction ft=fm.beginTransaction();
 			switch(position){
 			case 0: 
 				fr= new Books_al_navfr1();
 				ft.replace(R.id.fl4,fr).commit();
 				break;
 			case 1:
 				Intent i5 = new Intent(this,Books_list_al.class);
 				//fr= new NavDrawFrag2();
 				//Intent i = getIntent();
 				//String user = i.getExtras().getString("seller_roll");
 				//NavDrawFrag2 s = new NavDrawFrag2();
				//Bundle data1 = new Bundle();
				//data1.putString("user", user);
				//fr.setArguments(data1);
 				//ft.replace(R.id.fl4,fr).commit();
 				startActivity(i5);
 				break;
 			case 2:
 				Intent i6 = new Intent(this,Book_al_delete.class);
 				startActivity(i6);
 				break;
 			
 			case 3: 
 				sp=getSharedPreferences("admin detail",Context.MODE_PRIVATE);
 				editor=sp.edit();
 				editor.clear();
 				editor.commit();
 				Intent i2 = new Intent(this, Main.class);
 				startActivity(i2);
 				break;
 			}
 			draw_List.setItemChecked(position,true);
 			 draw_lay.closeDrawer(draw_List);
 		}

		@Override
		public void onItemClick(AdapterViewCompat<?> parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			getItem(position);
			
		}
		@Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			//super.onBackPressed();
			backbuttonhandler();
			return;
		}

		private void backbuttonhandler() {
			// TODO Auto-generated method stub
			 if (getSupportFragmentManager().findFragmentByTag("frag1") != null) {
				    // I'm viewing Fragment C
				    getSupportFragmentManager().popBackStack("TAG",FragmentManager.POP_BACK_STACK_INCLUSIVE);
				  } else {
				   
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
		}
	}
	
