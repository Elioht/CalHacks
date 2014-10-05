package com.unsigned.innovations.CalHacks;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabSwipes extends Fragment
{
    View rootView;

	public TabSwipes() 
	{
	    // Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle     savedInstanceState)
	{   
	
	getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	
	// Apply the layout for the fragment
	rootView = inflater.inflate(R.layout.fragment_driver, container, false);
	
	
	getActivity().setTitle("New tabbed layout inside Fragment :-) ");
	
	
	ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // show the given tab
	    }
	
	    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // hide the given tab
	    }
	
	    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
	        // probably ignore this event
	    }
	};

	// Add tabs
	getActivity().getActionBar().addTab(
            getActivity().getActionBar().newTab()
            .setText("Driver")
            .setTabListener(tabListener));
 getActivity().getActionBar().addTab(
            getActivity().getActionBar().newTab()
            .setText("Passenger")
            .setTabListener(tabListener));
 
 	


	return rootView;
	}
}
