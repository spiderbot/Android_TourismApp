package com.piyush;

import java.io.File;
import java.util.ArrayList;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.piyush.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


@SuppressWarnings("deprecation")
public class PreferenceLockScreen extends Activity implements OnGesturePerformedListener {
	    private GestureLibrary gestureLib;
	    private WallpaperManager wallpaperManager;
	    private Drawable wallpaperDrawable;
		private GlobalVariables g = GlobalVariables.getInstance();

 	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.plockscreen);
 		 
		LinearLayout view99 = (LinearLayout)findViewById(R.id.ads);
	    
    	wallpaperManager = WallpaperManager.getInstance(this);
    	//wallpaperManager.setWallpaperOffsetSteps (100,100);
        wallpaperDrawable = wallpaperManager.getDrawable();
    	getWindow().setBackgroundDrawable(wallpaperDrawable);
		
		 
    	if(getIntent()!=null&&getIntent().hasExtra("kill")&&getIntent().getExtras().getInt("kill")==1)
    	{
    	      finish();
    	}

    	 GestureOverlayView overlayLock = (GestureOverlayView) findViewById(R.id.gestures_overlay_lock2);
    	 overlayLock.addOnGesturePerformedListener(this);
    	 
         final String path = new File(Environment.getExternalStorageDirectory(),"gestures").getAbsolutePath();
         gestureLib = GestureLibraries.fromFile(path);

         
     	
        try
        {
        	ImageView password = (ImageView)findViewById(R.id.password2);
        	password.setOnClickListener(new View.OnClickListener() 
        	{
				public void onClick(View v) 
        		{   
					enterPin();
        		}
        	});
        }
        catch (Exception e) 
        {
		}
        
        try
        {
        	ImageView pin = (ImageView)findViewById(R.id.pin2);
        	pin.setOnClickListener(new View.OnClickListener() 
        	{
				public void onClick(View v) 
        		{   
					enterPass();
        		}
        	});
        }
        catch (Exception e) 
        {
		}
			
    	super.onCreate(savedInstanceState);
    }
	 
	 public void enterPin()
	 {
		 
		 if(g.isPinEnabled())
		{
			 
			   	final EditText enterPin = new EditText(this);
			   	enterPin.setTransformationMethod(PasswordTransformationMethod.getInstance());
			   	enterPin.setTextSize(40); 
			   	enterPin.setRawInputType(2);
			   	enterPin.setImeOptions(EditorInfo.IME_ACTION_DONE);
			   	CharSequence hint = new String("******");
			   	enterPin.setHint(hint);
			   	enterPin.setFilters( new InputFilter[] { new InputFilter.LengthFilter(6) } );
			   	AlertDialog.Builder builder = new AlertDialog.Builder(PreferenceLockScreen.this);
			   	builder.setView(enterPin);
			   	enterPin.setOnEditorActionListener(new OnEditorActionListener() {
			   		
	        @Override
	   		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
	            	if(enterPin.getText().toString().equals(g.getPin()))
	        	    {
						GlobalVariables g = GlobalVariables.getInstance();
						Log.w("com.atomic.lock", "LockIsOn=false");
						
						end();
	        	    }
	       			else
	       			{
	       			}
	            }    
	            return false;
	        }
	    });
	   	
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
			   	
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.showSoftInput(enterPin, InputMethodManager.SHOW_IMPLICIT);
			   	
			final AlertDialog dialog = builder.create();
			dialog.show();	
		}
		else
		{}
	 }
	 public void enterPass()
	 {
	
		 if(g.isPassEnabled())
		{
			   	final EditText enterPass = new EditText(this);
			   	enterPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
			   	enterPass.setTextSize(40);
			   	enterPass.setRawInputType(1);
			   	enterPass.setImeOptions(EditorInfo.IME_ACTION_DONE);
			   	CharSequence hint = new String("**********");
			   	enterPass.setHint(hint);
			   	enterPass.setFilters( new InputFilter[] { new InputFilter.LengthFilter(10) } );			   	
			   	AlertDialog.Builder builder = new AlertDialog.Builder(PreferenceLockScreen.this);
			   	builder.setView(enterPass);
			   	
	   	enterPass.setOnEditorActionListener(new OnEditorActionListener() {
	        @Override
	   		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
	            	if(enterPass.getText().toString().equals(g.getPass()))
	        	    {
						GlobalVariables g = GlobalVariables.getInstance();
						Log.w("com.atomic.lock", "LockIsOn=false");
					
						
						end();
	        	    }
	       			else
	       			{

	       			}
	            }    
	            return false;
	        }
	    });
	   	
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
		   	
			InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			mgr.showSoftInput(enterPass, InputMethodManager.SHOW_IMPLICIT);
			
			AlertDialog dialog = builder.create();
			dialog.show();	
			
		}
		else
		{}
	 }

	@SuppressWarnings("deprecation")
	public void end()
	{		
		g.setSecurity(false);
		
		Intent intent11 = new Intent(this, Preferences.class);
    	intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent11);  
		Log.w("com.atomic.lock", "SEND TO PREFERENCE");

		
     	finish();
	}

	@Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		
		if (!gestureLib.load()) {
			Log.w("com.atomic.lock", "could not load gesture library");
			finish();
		}
		
		
		
		ArrayList<Gesture> gestureLock = gestureLib.getGestures("Current");
		Gesture lock = gestureLock.get(0);
	       float lengthLock = lock.getLength();
	       double strokeCountLock = lock.getStrokesCount();			

	   ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
    
      float length = gesture.getLength();
      double strokeCount = gesture.getStrokesCount();
      
      String lengthS = String.valueOf(length); 
      String strokeCountS = String.valueOf(strokeCount); 

      Log.w("com.atomic.lock", "length:" + lengthS);
      Log.w("com.atomic.lock", "length:" + strokeCountS);
             
      if (predictions.size() > 0) {
              for(Prediction prediction: predictions){
                      
                      Log.w("com.atomic.lock", "Prediction Stroke: " + strokeCount + " Lock Stroke: " + strokeCountLock + " Prediction length: " + length + " Lock length: " + lengthLock + " Prediction Score: " + prediction.score + "Lock Score: " + g.getLockScore());
                      
                      if(strokeCount == strokeCountLock && (length > (lengthLock-400) || length < (lengthLock+400)) &&  prediction.score > 2.5)//g.getLockScore())
                      {		
                          Log.w("com.atomic.lock", "Lock Score: " + g.getLockScore() + " Prediction Score: " + prediction.score);
                                              
                      end();
                      
                      }
              }
      }
	}
	
	public void goHome()
	{
    	Intent goHome = new Intent(Intent.ACTION_MAIN);
    	Log.w("com.atomic.lock", g.getPackageName() + "  +  " + g.getName());
    	goHome.setClassName(g.getPackageName(), g.getName());
    	startActivity(goHome); 
	}
	
	
    @Override
    public void onBackPressed() {
    	goHome();
    	return;
        }
    
	@Override
	public void onResume()
	{
		Log.d("com.atomic.lock", "Security on resume");
		super.onResume();
	}
	
}