package com.unsigned.innovations.CalHacks;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class LoginActivity extends ActionBarActivity implements Communicator {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
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
		if(id == R.id.action_settings)
			return true;
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void response(boolean accepted) {
		// TODO Auto-generated method stub
		FragmentManager manager= getFragmentManager();
		
	}
}
