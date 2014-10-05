package com.unsigned.innovations.CalHacks;



import com.unsigned.innovations.CalHacks.adapter.TabsPagerAdapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TabMyRides extends Fragment implements
ActionBar.TabListener {
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	String number;
	// Tab titles
	private String[] tabs = { "Driver", "Passenger"};
	
	
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
	View result=inflater.inflate(R.layout.activity_tab_my_rides, container, false);
	ViewPager pager=(ViewPager)result.findViewById(R.id.pager);
	// Initilization
	viewPager = (ViewPager)result.findViewById(R.id.pager);
	actionBar = getActivity().getActionBar();
	mAdapter = new TabsPagerAdapter(getActivity().getSupportFragmentManager());
	
	viewPager.setAdapter(mAdapter);
	//actionBar.setHomeButtonEnabled(false);
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
	
	// Adding Tabs
//	for (String tab_name : tabs) {
//		actionBar.addTab(actionBar.newTab().setText(tab_name)
//				.setTabListener(this));
//	}
	
	/**
	 * on swiping the viewpager make respective tab selected
	 * */
	viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
	
		@Override
		public void onPageSelected(int position) {
			// on changing the page
			// make respected tab selected
			actionBar.setSelectedNavigationItem(position);
		}
	
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
	
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	});
	
	return(result);
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}
	
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	public void onPause(){
		// TODO Auto-generated method stub
		super.onPause(); 
		actionBar.removeAllTabs();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
	}
	

}