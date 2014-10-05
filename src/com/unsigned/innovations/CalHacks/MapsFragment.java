package com.unsigned.innovations.CalHacks;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link FragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events. Use the {@link FragmentA#newInstance} factory
 * method to create an instance of this fragment.
 *
 */
public class MapsFragment extends Fragment {

	private SupportMapFragment fragment;
	private GoogleMap map;
	private Button button;


	public MapsFragment() {
		// Required empty public constructor
	}


	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.map_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FragmentManager fm = getChildFragmentManager();
		fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
		if (fragment == null) {
			fragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map, fragment).commit(); 	
		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(getActivity());
	    // Set the layout full width, full height
	    LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	    linearLayout.setLayoutParams(params);
	    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
	    
	    Button button = new Button(getActivity());
	    Button button1 = new Button(getActivity());
	    button.setText("Find Piggy Back");
	    button1.setText("Set up Piggy Back");
	    button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    button1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    //For buttons visibility, you must set the layout params in order to give some width and height: 
	    LayoutParams paramet = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    button.setLayoutParams(paramet);
	    
	    ViewGroup viewGroup = (ViewGroup) view;

	    linearLayout.addView(button);
	    linearLayout.addView(button1);

	    viewGroup.addView(linearLayout);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = fragment.getMap();
		}
	}

	
	public void onDestroyView() {
		   super.onDestroyView(); 
		   Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));   
		   FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		   ft.remove(fragment);
		   ft.commit();
		}
	
	
	
	

	// TODO: Rename method, update argument and hook method into UI event
	
	

}