package com.unsigned.innovations.CalHacks;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class LoginActivity extends FragmentActivity {
		private FacebookFragment facebookFragment;
		private static final String TAG = "facebookFragment";
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);

		    if (savedInstanceState == null) {
		        // Add the fragment on initial activity setup
		        facebookFragment = new FacebookFragment();
		        getSupportFragmentManager()
		        .beginTransaction()
		        .add(android.R.id.content, facebookFragment)
		        .commit();
		    } else {
		        // Or set the fragment from restored state info
		        facebookFragment = (FacebookFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
		    }
		}      
}