package com.unsigned.innovations.CalHacks;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
=======
>>>>>>> 843849e5a5fc5780c2d0c84da52d93ac46d68603
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.Window;
import android.view.WindowManager;
=======
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SplashActivity extends ActionBarActivity {
	
	private final int SPLASH_DISPLAY_LENGHT = 2000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);



        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
               
        		Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
	
}
