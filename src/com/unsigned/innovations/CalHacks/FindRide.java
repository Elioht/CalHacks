package com.unsigned.innovations.CalHacks;


import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FindRide extends Activity {

	ListView list;
	
	ArrayList<String> listItems =new ArrayList<String>();
	ArrayList<Integer> imageId =new ArrayList<Integer>();
	
	
	
	ExpandableListView exv;
	String number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_ride);
		
		/////delete this later on///
		listItems.add("we in");
		imageId.add(R.drawable.ic_launcher);
		//////////////////////////
		
		CustomList adapter = new
		CustomList(FindRide.this, listItems, imageId);
		list = (ListView)findViewById(R.id.list);
    
        // Assign adapter to ListView
        list.setAdapter(adapter); 
        // ListView Item Click Listener
	            list.setOnItemClickListener(new OnItemClickListener() {
	 
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
							
							AlertDialog.Builder builder = new AlertDialog.Builder(FindRide.this);
							builder.setMessage("Would you like to book? ");
					
					builder.setTitle("Confirmation:");
					builder.setIcon(R.drawable.ic_launcher);
					
					builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							
							Intent features = new Intent(FindRide.this,SplashActivity.class);
							startActivity(features);
						}
					});	
	
					builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							
							dialog.cancel();
						}
					});
					
					AlertDialog alert = builder.create();
		            alert.show();
					
				
				}
	
	     });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.find_ride, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		return super.onOptionsItemSelected(item);
	}

}
