package com.piyush;

import java.util.ArrayList;
import java.util.Random;

import com.piyush.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



public class HomeScreen extends Activity {
	GlobalVariables g = GlobalVariables.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		Log.w("entering");
	            super.onCreate(savedInstanceState);

	    		g.setContext(getApplicationContext());
	            	
				Log.w("com.atomic.lock", "Splash_Screen_Before");
	        	  
	        	final Context context = getBaseContext();

				Log.w("com.atomic.lock", "Splash_Screen_After");

				new CountDownTimer(3153,3153) 
			    {
			         @Override
			         public void onTick(long millisUntilFinished) {
			         }
			         @Override
			         public void onFinish() {
			        	 Intent intent = new Intent(context, Preferences.class);
			         	 startActivity(intent);
			         }
			    }.start();
			    
	        	setContentView(R.layout.home);
	 }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
    	final ArrayList<Integer> picList = new ArrayList<Integer>(); 
    	picList.add(R.drawable.icon);
    	picList.add(R.drawable.icon2);
    	picList.add(R.drawable.icon3);
    	picList.add(R.drawable.icon4);
    	picList.add(R.drawable.icon5);
    	picList.add(R.drawable.icon6);
      final ImageView picture = (ImageView) findViewById(R.id.image);
  	  int randInt = new Random().nextInt(6);
  	  picture.setImageResource(picList.get(randInt));
	return false;
	}
	
	@Override
	public void onResume()
	{
		g.setContext(getApplicationContext());
        
    	super.onResume();

		new CountDownTimer(3153,3153) 
	    {
	         @Override
	         public void onTick(long millisUntilFinished) {
	         }
	         @Override
	         public void onFinish() {
	        	Intent intent = new Intent(getBaseContext(), Preferences.class);
	         	startActivity(intent);
	         }
	    }.start();
	}
	
}

	    
	  
