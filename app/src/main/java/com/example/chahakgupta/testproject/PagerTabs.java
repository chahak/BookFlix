package com.example.chahakgupta.testproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerTabs extends FragmentStatePagerAdapter {
	
	private String tabtitles[]={"welcome","home","contact us"};
	public PagerTabs(FragmentManager fm)
	{
		super(fm);
	}
	public Fragment getItem(int position){
		switch(position){
		case 0: 
			return new PagerFrag1();
		case 1:
			return new PagerFrag2();
		case 2:
			return new PagerFrag3();
		}
		return null;
	}
	public CharSequence getPage(int position)
	{
		return tabtitles[position];
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
