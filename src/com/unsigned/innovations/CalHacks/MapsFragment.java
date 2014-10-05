package com.unsigned.innovations.CalHacks;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
	    button1.setText("Set Piggy Back");
	    
	   
	    button.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    button1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    //For buttons visibility, you must set the layout params in order to give some width and height: 
	    LayoutParams paramet = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    //address.setLayoutParams(paramet);
	    
	    
	    ViewGroup viewGroup = (ViewGroup) view;

	    
	    linearLayout.addView(button);
	    linearLayout.addView(button1);

	    viewGroup.addView(linearLayout);
	    
	    final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

	    alert.setTitle("Find PiggyBack");
	    alert.setMessage("Enter an address to search for a Piggy Back");

	    // Set an EditText view to get user input 
	    final EditText input = new EditText(getActivity());
	    alert.setView(input);

	    alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int button) {
	      Editable value = input.getText();
	      // Do something with value!
	      }
	    });

	    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int whichButton) {
	        // Canceled.
	      }
	    });
	    
	    button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert.show();
				
			}
		});
	    
	    final AlertDialog.Builder alert2 = new AlertDialog.Builder(getActivity());

	    alert2.setTitle("Set Piggy Back");
	    alert2.setMessage("Enter an address to set a Piggy Back");

	    // Set an EditText view to get user input 
	    final EditText input2 = new EditText(getActivity());
	    alert2.setView(input2);

	    alert2.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int button) {
	      Editable value = input2.getText();
	      // Do something with value!
	      }
	    });

	    alert2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int whichButton) {
	        // Canceled.
	      }
	    });
	    
	    button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alert2.show();
				
			}
		});

	  
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = fragment.getMap();
			CameraUpdate center=
			        CameraUpdateFactory.newLatLng(new LatLng(37.87,
			                                                 -122.25));
			    CameraUpdate zoom=CameraUpdateFactory.zoomTo(5);

			    map.moveCamera(center);
			    map.animateCamera(zoom);
			map.addMarker(new MarkerOptions().position(new LatLng(37.87, -122.25)));
			map.addMarker(new MarkerOptions().position(new LatLng(36.65, -121.79)));
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