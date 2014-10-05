package com.unsigned.innovations.CalHacks;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.utilities.Base64.InputStream;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

	ArrayList<String> listItems = new ArrayList<String>();
	ArrayList<Bitmap> imageId = new ArrayList<Bitmap>();
	ArrayList<String> urls = new ArrayList<String>();
	CustomRides adapter = null;
	ExpandableListView exv;
	String number;
	Firebase myFireBaseReference;
	int randnum;
	 int randomInt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_ride);

		
		Firebase.setAndroidContext(this);
		myFireBaseReference = new Firebase("https://piggyback.firebaseio.com/");
		myFireBaseReference.child("users").addListenerForSingleValueEvent(
				new ValueEventListener() {

					@Override
					public void onDataChange(DataSnapshot arg0) {
						// TODO Auto-generated method stub

						Random randomGenerator = new Random();
						for (DataSnapshot ds : arg0.getChildren()) {
						    randomInt = randomGenerator.nextInt(10);
						    randnum = randomGenerator.nextInt(10);
							listItems.add(ds.child("first_name").getValue()
									.toString()
									+ " "
									+ ds.child("last_name").getValue().toString() + 
									"\nRating: " + randnum + 
									"\nPrice: " + randomInt);
							
							try {
								
								urls.add(ds.child("profile_url").getValue()
										.toString());

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						String [] arr = new String[urls.size()];
						for(int i = 0; i < urls.size();i++){
							arr[i] = urls.get(i);
						}
						ProfilePicture pp = new ProfilePicture();
						pp.execute(arr);
					}

					@Override
					public void onCancelled(FirebaseError arg0) {
						// TODO Auto-generated method stub

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

	public class ProfilePicture extends AsyncTask<String, String, Bitmap[]> {
		@Override
		protected void onPostExecute(Bitmap[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			for (int i = 0; i < result.length; i++) {
				imageId.add(result[i]);
			}
			adapter = new CustomRides(FindRide.this, listItems, imageId);
			list = (ListView) findViewById(R.id.list);

			// Assign adapter to ListView
			list.setAdapter(adapter);
			// ListView Item Click Listener
			list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							FindRide.this);
					builder.setMessage("Would you like to book? ");

					builder.setTitle("Confirmation:");
					builder.setIcon(R.drawable.ic_launcher);

					builder.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									Intent features = new Intent(FindRide.this,
											SplashActivity.class);
									startActivity(features);
								}
							});

					builder.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									dialog.cancel();
								}
							});

					AlertDialog alert = builder.create();
					alert.show();
				}
			});
		}

		@Override
		protected Bitmap[] doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			int count = arg0.length;
			Bitmap[] bitArray = new Bitmap[count];
			for (int i = 0; i < count; i++) {
				try {
					java.net.URL url = new java.net.URL(arg0[i]);
			        HttpURLConnection connection = (HttpURLConnection) url
			                .openConnection();
			        connection.setDoInput(true);
			        connection.connect();
			        java.io.InputStream input = connection.getInputStream();
			        bitArray[i] = BitmapFactory.decodeStream(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return bitArray;
		}

	}

}
