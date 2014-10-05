package com.unsigned.innovations.CalHacks;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity implements Communicator {
	private FacebookFragment facebookFragment;
	private static final String TAG = "facebookFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		android.app.FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		EmailFragment emailFragment = new EmailFragment();
		fragmentTransaction.add(R.id.emailContainer, emailFragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void response(boolean accepted) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		if(accepted){
			FacebookFragment fb = new FacebookFragment();
			fragmentTransaction.add(R.id.facebookContainer, fb);
			fragmentTransaction.commit();
		}
		
	}
}
