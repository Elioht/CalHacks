package com.unsigned.innovations.CalHacks;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyProfile extends Fragment{
	ListView list;
	//ArrayList<String> listItems = new ArrayList<String>();
	  
	
	  //CustomList adapter = new CustomList(MyProfile.this,listItems);
	  //list = (ListView)findViewById(R.id.listView1);
	 
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
		 
		 	
	        View root = inflater.inflate(R.layout.fragment_myprofile, container, false);
	        list = (ListView) getActivity().findViewById(R.id.listView1);
	        
	        return root;
	    }
}
