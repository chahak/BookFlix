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
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class NavDraw1 extends ActionBarActivity implements OnItemClickListener {
    private DrawerLayout draw_lay;
    private String[] titles1;
    private ListView draw_List;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment fr;
    Test_custom_adap adapter;
    private android.support.v7.app.ActionBar ab;

    List<Test_drawitem> dataList;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_draw1);
        //**

        dataList = new ArrayList<Test_drawitem>();
        mTitle = mDrawerTitle = getTitle();
        draw_lay = (DrawerLayout) findViewById(R.id.drawl);
        draw_List = (ListView) findViewById(R.id.left_draw);
        // titles1=getResources().getStringArray(R.array.items1);
        ab= getSupportActionBar();
        draw_lay.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(new Test_drawitem("Home", R.drawable.home));
        dataList.add(new Test_drawitem("Sell Books", R.drawable.sellbook));
        dataList.add(new Test_drawitem("Books Status", R.drawable.buybook));
        dataList.add(new Test_drawitem("Exit", R.drawable.signout));

        adapter = new Test_custom_adap(this, R.layout.test_item,
                dataList);

        draw_List.setAdapter(adapter);

        draw_List.setOnItemClickListener(new DrawerItemClickListener());

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        fr= new NavDrawFrag1();
        ft.replace(R.id.fl1,fr).commit();
        // draw_List.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles1));
        //draw_List.setOnItemClickListener(new Click());

        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(
                this,                  /* host Activity */
                draw_lay,         /* DrawerLayout object */
                R.drawable.draw,  /* nav_drawer image to replace 'Up' caret */
                R.string.open_draw,  /* "open drawer" description for accessibility */
                R.string.close_draw  /* "close drawer" description for accessibility */
        ) {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerClosed(View view) {
                ab.setTitle(mTitle);
                invalidateOptionsMenu();
            }


            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerOpened(View drawerView) {
                ab.setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        draw_lay.setDrawerListener(mDrawerToggle);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);//for creating a back arrow for home
        ab.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.draw));

        if (savedInstanceState == null) {
            SelectItem(0);
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        dataList = new ArrayList<Test_drawitem>();
        mTitle = mDrawerTitle = getTitle();
        draw_lay = (DrawerLayout) findViewById(R.id.drawl);
        draw_List = (ListView) findViewById(R.id.left_draw);
        // titles1=getResources().getStringArray(R.array.items1);
        ab= getSupportActionBar();
        draw_lay.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(new Test_drawitem("Home", R.drawable.home));
        dataList.add(new Test_drawitem("Sell Books", R.drawable.sellbook));
        dataList.add(new Test_drawitem("Books Status", R.drawable.book4));
        dataList.add(new Test_drawitem("Exit", R.drawable.signout));

        adapter = new Test_custom_adap(this, R.layout.test_item,
                dataList);

        draw_List.setAdapter(adapter);

        draw_List.setOnItemClickListener(new DrawerItemClickListener());

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        fr= new NavDraw1_Frag1();
        ft.replace(R.id.fl1,fr).commit();
        draw_List.setItemChecked(0,true);
        // draw_List.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles1));
        //draw_List.setOnItemClickListener(new Click());

        mDrawerToggle = new android.support.v4.app.ActionBarDrawerToggle(
                this,                  /* host Activity */
                draw_lay,         /* DrawerLayout object */
                R.drawable.draw,  /* nav_drawer image to replace 'Up' caret */
                R.string.open_draw,  /* "open drawer" description for accessibility */
                R.string.close_draw  /* "close drawer" description for accessibility */
        ) {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }


            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        draw_lay.setDrawerListener(mDrawerToggle);
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);//for creating a back arrow for home
        ab.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.draw));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {   //for syncing that 3 line icon
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(mDrawerToggle.onOptionsItemSelected(item))
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SelectItem(int position){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        // Bundle args = new Bundle();
        switch(position){
            case 0:
                fr= new NavDraw1_Frag1();
                //  args.putString(Test_fr1.ITEM_NAME, dataList.get(position)
                //        .getItemName());
                //args.putInt(Test_fr1.IMAGE_RESOURCE_ID, dataList.get(position)
                //      .getImgResID());
                ft.replace(R.id.fl1,fr).commit();
                break;
            case 1:
                Intent i5 = new Intent(this,Sell_Books.class);
                startActivity(i5);
                break;

            case 2:
                fr= new Seller_status();
                ft.replace(R.id.fl1,fr);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case 3:
                //  Intent i6 = new Intent(this,Signin.class);
                //i6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                break;
        }
        //  fr.setArguments(args);

        draw_List.setItemChecked(position,true);
        setTitle(dataList.get(position).getItemName());
        draw_lay.closeDrawer(draw_List);
    }

    @Override
    public void onItemClick(AdapterViewCompat<?> parent, View v, int position,
                            long id) {
        // TODO Auto-generated method stub
        SelectItem(position);

    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();


        if (getFragmentManager().getBackStackEntryCount()> 0) {
            // backbuttonhandler();
            Log.i("MainActivity", "popping backstack");

            getFragmentManager().popBackStack();
            // finish();
        } else {
            super.onBackPressed();
        }
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

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }
}

