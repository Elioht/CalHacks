package com.unsigned.innovations.CalHacks;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

	public MapsFragment() {
		// Required empty public constructor
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.map_fragment, container, false);
		return v;
	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		FragmentManager fm = getChildFragmentManager();
		fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
		if(fragment == null){
			fragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map, fragment).commit();
		}
	}



	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(map == null){
			map = fragment.getMap();
			//map.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
		}
	}
	
	
	
	

	// TODO: Rename method, update argument and hook method into UI event
	
	

}