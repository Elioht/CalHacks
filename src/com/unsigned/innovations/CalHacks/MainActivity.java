package com.unsigned.innovations.CalHacks;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	public static FragmentManager fragmentManager;
	private DrawerLayout drawerLayout;
	public ActionBarDrawerToggle drawerListener;
	private ListView listView;
	private String[] options;
	private MyAdapter myAdapter;
	int previousPos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		previousPos = 0;
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
		MapsFragment mapFragment = new MapsFragment();
		fragmentTransaction.add(R.id.mainContent, mapFragment);
		fragmentTransaction.commit();
		
		drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		listView = (ListView)findViewById(R.id.drawerList);
		myAdapter = new MyAdapter(this);
		listView.setAdapter(myAdapter);
		fragmentManager = getSupportFragmentManager();
		options = getResources().getStringArray(R.array.nav_drawer);
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
				
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
				
			}
			
		};
		
		drawerLayout.setDrawerListener(drawerListener);
		getActionBar().setHomeButtonEnabled(true);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		listView.setOnItemClickListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (  Integer.valueOf(android.os.Build.VERSION.SDK) < 7 //Instead use android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
	            && keyCode == KeyEvent.KEYCODE_BACK
	            && event.getRepeatCount() == 0) {
	        // Take care of calling this method on earlier versions of
	        // the platform where it doesn't exist.
	        onBackPressed();
	    }

	    return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
	    // This will be called either automatically for you on 2.0
	    // or later, or by the code above on earlier versions of the
	    // platform.
	    return;
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(drawerListener.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		//switch statement
		//launch activity depending on the position
		selectItem(position);
		
	}
	
	public void selectItem(int position){
		listView.setItemChecked(position, true);
		setTitle(options[position]);
		
		if(previousPos == position)
		{
			drawerLayout.closeDrawer(listView);
			return;
		}
			

		// Create a new fragment and specify the planet to show based on position
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		switch(position)
		{
		case 0:
			transaction.replace(R.id.mainContent, new MapsFragment()).commit(); 
		case 1:
            transaction.replace(R.id.mainContent, new MyProfile()).commit();
			break;
		case 2:
			transaction.replace(R.id.mainContent, new MyPayments()).commit();
		    break;
		case 3:
            transaction.replace(R.id.mainContent, new TabMyRides()).commit();
			break;
		case 4:
			transaction.replace(R.id.mainContent, new MyNotifications()).commit();
			break;
		}
		previousPos = position;
	    drawerLayout.closeDrawer(listView);
	    return;
	}

	public void setTitle(String title){
		getActionBar().setTitle("");
	}

}

//fetching string array
class MyAdapter extends BaseAdapter{
	private Context context;
	String[] options;
	int[] images = {R.drawable.home,R.drawable.profile2, R.drawable.payment, R.drawable.car2, R.drawable.notification};
	
	public MyAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		options = context.getResources().getStringArray(R.array.nav_drawer);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return options.length;
	}

	@Override
	public Object getItem(int positions) {
		// TODO Auto-generated method stub
		return options[positions];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = null;
		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			row = inflater.inflate(R.layout.custom_row, parent, false);
		}
		else{
			row = convertView;
		}
		
		TextView textView1 = (TextView) row.findViewById(R.id.textView1);
		ImageView imageView1 = (ImageView) row.findViewById(R.id.emailConfirmationImageView);
		//Typeface customText = Typeface.createFromAsset(context.getAssets(), "fonts/Airstream.ttf");
		//textView1.setTypeface(customText);
		
		textView1.setText(options[position]);
		imageView1.setImageResource(images[position]);
		
		return row;	
	}	
}
