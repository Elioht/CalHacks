package com.unsigned.innovations.CalHacks;


import com.facebook.widget.LoginButton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MyProfile extends Fragment{
	ListView list;
	//ArrayList<String> listItems = new ArrayList<String>();
	static String userID;
	TextView phone;
	TextView name;
	Button facebook;
	  
	
	  //CustomList adapter = new CustomList(MyProfile.this,listItems);
	  //list = (ListView)findViewById(R.id.listView1);
	 
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
		 
		    //Log.v("Test: ", userID);
	        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
	        //list = (ListView) getActivity().findViewById(R.id.listView1);
	        phone = (TextView) getActivity().findViewById(R.id.phone_number);
	        name = (TextView) getActivity().findViewById(R.id.name);
	       // LoginButton authButton = (LoginButton) getActivity().findViewById(R.id.logout_facebook);
	       
	        
 	        return root;
	    }
	 
	 public static void getID(String id)
	 {
		userID = id;
	 }
	 
}
