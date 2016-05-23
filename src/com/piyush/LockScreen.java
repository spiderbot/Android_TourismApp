package com.piyush;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.piyush.R;
import com.piyush.MyService.MyLocalBinder;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;


@SuppressWarnings("deprecation")
public class LockScreen extends Activity implements OnGesturePerformedListener {
	    private GestureLibrary gestureLib;
	    private WallpaperManager wallpaperManager;
	    private Drawable wallpaperDrawable;
		public GlobalVariables g = GlobalVariables.getInstance();

		MyService myService;
	    boolean isBound = false;

	    private ServiceConnection myConnection = new ServiceConnection() {

	    public void onServiceConnected(ComponentName className, IBinder service) {
	        MyLocalBinder binder = (MyLocalBinder) service;
	        myService = binder.getService();
	        isBound = true;
	    }
	    
	    public void onServiceDisconnected(ComponentName arg0) {
	        isBound = false;
	    }
	    };   	 
	   

	 public void onCreate(Bundle savedInstanceState) {
			
		g.setContext(getApplicationContext());

		ReCreateVariables();
		 
	   	if(g.getServiceOn()==false || g.isFromPreference()==true)
	   	{
	   		goHome();
	    }
	    
	    if(g.getServiceOn()==true)
	    {
	    	g.setLockIsOn(true);
	    }
		 
	    
		Boolean keyguard = g.isKeyguard();		
		if(keyguard)
		{
	    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		}
		
		
    	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	
    	setContentView(R.layout.lockscreen);

	    final AdView adView = new AdView(this, AdSize.BANNER, "a150be899eda109");

		LinearLayout view99 = (LinearLayout)findViewById(R.id.ads);
		view99.addView(adView);
	    AdRequest request = new AdRequest();
	    adView.loadAd(request); 
    	
		adView.setVisibility(View.VISIBLE);

	    adView.setAdListener(new AdListener() {
			@Override
			public void onDismissScreen(Ad arg0) {
				Log.w("com.atomic.lock", "Hit1");
				myService.Run();
			}
			@Override
			public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
				Log.w("com.atomic.lock", "Hit2");
				myService.Run();
			}
			@Override
			public void onLeaveApplication(Ad arg0) {
				Log.w("com.atomic.lock", "Hit3");
				myService.Run();
			}
			@Override
			public void onPresentScreen(Ad arg0) {
				Log.w("com.atomic.lock", "Hit4");
				myService.Run();
				adView.setVisibility(View.GONE);  //THEN SEND TO ATOMIC LOCK?
			}
			@Override
			public void onReceiveAd(Ad arg0) {
				Log.w("com.atomic.lock", "Hit5");
				myService.Run();
			}
	    });
	    
	    
	    
    	wallpaperManager = WallpaperManager.getInstance(this);
    	//wallpaperManager.setWallpaperOffsetSteps (100,100);
        wallpaperDrawable = wallpaperManager.getDrawable();
    	getWindow().setBackgroundDrawable(wallpaperDrawable);

    	
    	if(getIntent()!=null&&getIntent().hasExtra("kill")&&getIntent().getExtras().getInt("kill")==1)
    	{
    	      finish();
    	}

    	 GestureOverlayView overlayLock = (GestureOverlayView) findViewById(R.id.gestures_overlay_lock);
    	 overlayLock.addOnGesturePerformedListener(this);
    	 
         final String path = new File(Environment.getExternalStorageDirectory(),"gestures").getAbsolutePath();
         gestureLib = GestureLibraries.fromFile(path);

        try
        {
        	ImageView password = (ImageView)findViewById(R.id.password);
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
        	ImageView pin = (ImageView)findViewById(R.id.pin);
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
		
        
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

        
		ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

		List <ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1); 
	     ComponentName componentInfo = taskInfo.get(0).topActivity;
	     String previousAppName = componentInfo.getPackageName();
	     
			g.setPreviousAppComponent(componentInfo);
			g.setPreviousAppName(previousAppName);
    	
			
			LinearLayout view = (LinearLayout)findViewById(R.id.lockscreen);
			LinearLayout view2 = (LinearLayout)findViewById(R.id.lockscreen2);
			LinearLayout view3 = (LinearLayout)findViewById(R.id.lockscreen3);
			
			if(g.getSendToHome() || !g.getLockIsOn())
	    	{		
			     goHome();
	    	}
			
    	super.onCreate(savedInstanceState);
    }
	 
	@Override
		public void onAttachedToWindow() {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
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
		   	AlertDialog.Builder builder = new AlertDialog.Builder(LockScreen.this);
		   	builder.setView(enterPin);
		   	enterPin.setOnEditorActionListener(new OnEditorActionListener() {
			   		
	        @Override
	   		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
	            	if(enterPin.getText().toString().equals(g.getPin()))
	        	    {
						g.setSendToHome(true);
						GlobalVariables g = GlobalVariables.getInstance();
						g.setLockIsOn(false);
						Log.w("com.atomic.lock", "LockIsOn=false");
						
						end();
	        	    }
	       			else
	       			{
	       				Log.w("com.atomic.lock", "Something went wrong: " + enterPin.getText().toString() + " : " + g.getPin());
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
		   	AlertDialog.Builder builder = new AlertDialog.Builder(LockScreen.this);
		   	builder.setView(enterPass);
			   	
	   	enterPass.setOnEditorActionListener(new OnEditorActionListener() {
	        @Override
	   		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
	            	if(enterPass.getText().toString().equals(g.getPass()))
	        	    {
						g.setSendToHome(true);
						GlobalVariables g = GlobalVariables.getInstance();
						g.setLockIsOn(false);
						Log.w("com.atomic.lock", "LockIsOn=false");
											
						end();
	        	    }
	       			else
	       			{
	       				Log.w("com.atomic.lock", "Something went wrong: " + enterPass.getText().toString() + " : " + g.getPass());
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
		Log.w("com.atomic.lock", "End");
		
		g.setLockIsOn(false);
		g.setSendToHome(true);	
		
		myService.autoLockTimer();

        ComponentName previousAppComponent = g.getPreviousAppComponent();
        String previousAppName = g.getPreviousAppName();

     	   Intent intent = new Intent();
     	   intent.setComponent(new ComponentName(previousAppName, previousAppComponent.getClassName()));
     	   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     	   startActivity(intent);  

     	   finish();
	}
	 
	public void goHome()
	{
    	Log.w("com.atomic.lock", "GO HOME");

    	Intent goHome = new Intent(Intent.ACTION_MAIN);
    	Log.w("com.atomic.lock", g.getPackageName() + "  +  " + g.getName());
    	goHome.setClassName(g.getPackageName(), g.getName());
    	startActivity(goHome); 
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
                                              g.setSendToHome(true);
                            end();
                      }
              }
      }
	}
	
	@Override
	public void onResume()
	{
		Log.w("com.atomic.lock", "On Resume: " + g.getLockIsOn());
		
    	if(g.getServiceOn()==false || g.isFromPreference()==true || g.getSendToHome()==true)
	    {
    		Log.w("com.atomic.lock", "Go Home");
    		
    		goHome();
	    }		

		g.setLockIsOn(true);
	
		super.onResume();
	}
	
    @Override
    public void onBackPressed() {
    	//super.onBackPressed();
            return;
        }
	
	@Override 
	protected void onPause() 
    {
		Log.w("com.atomic.lock", "Service RUN5");
		
		Log.d("com.atomic.lock", "************************************************************************************");

		Log.d("com.atomic.lock", "isApplicationBroughtToBackground()="+isApplicationBroughtToBackground()  + "      g.getLockIsOn()="+g.getLockIsOn()+"   g.getSendToHome()="+g.getSendToHome()+"      g.isFromPreference()="+g.isFromPreference());

		try
        {
    	    if (isApplicationBroughtToBackground() && g.getLockIsOn() && !g.getSendToHome() && !g.isFromPreference()) 
            {
    			Log.w("com.atomic.lock", "app brought to background");
    			
    			Log.d("com.atomic.lock", "g.getLockIsOn()="+g.getLockIsOn()+"   g.getSendToHome()="+g.getSendToHome()+"      g.isFromPreference()="+g.isFromPreference());
    			
    			myService.Run();
    			Log.d("com.atomic.lock", "************************************************************************************");
    	    }

    		
    		else
    	    {
    			Log.w("com.atomic.lock", "app not brought to background");
    			Log.d("com.atomic.lock", "************************************************************************************");

            }
	    }
		catch(NullPointerException e){}
	    
	    
		Log.w("com.atomic.lock", "beforeOnPause");
	    super.onPause();
		Log.w("com.atomic.lock", "afterOnPause");

	}
	
	private boolean isApplicationBroughtToBackground() 
    {
	    ActivityManager am = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> tasks = am.getRunningTasks(1);
	    if (!tasks.isEmpty()) {
	        ComponentName topActivity = tasks.get(0).topActivity;
	        if (!topActivity.getPackageName().equals(getBaseContext().getPackageName())) 
            {
	            return true;
	        }
	    }

	    return false;
	}
	
	@Override
	public void onDestroy()
	{
		unbindService(myConnection);
		super.onDestroy();
	}
	
	public void ReCreateVariables() 
	{        
		try{
		g.setServiceOn(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("serviceOn", false));
		g.setLockIsOn(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("lockIsOn", false));
		g.setLockScore(Double.longBitsToDouble(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong("LockScore", 0)));
		
		ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
		// get the info from the currently running task 
	    List <ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1); 
	    ComponentName componentInfo = taskInfo.get(0).topActivity;
		
		g.setPreviousAppComponent(componentInfo); 
		g.setPreviousAppName(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("previousAppName", ""));
		g.setSendToHome(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("sendToHome", false));
		g.setPin(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pin", ""));
		g.setPass(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("pass", ""));
	    g.setPackageName(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("packageName",""));
		g.setName(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("name", ""));
		g.setPinEnabled(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("pinEnabled", false));
		g.setPinEnabled(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("passEnabled", false));
		g.setAppNotShowing(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("appNotShowing", false));
		g.setAutoLockTime(Double.longBitsToDouble(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong("autoLockTime", 0)));
		g.setKeyguard(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("keyguard", false));
		g.setSecurity(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("security", false));
		g.setFromPreference(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("fromPreference", false));
		g.setReverse(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("reverse", false));
		g.setReverse2(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("reverse2", false));
		g.setGestureSaved(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("gestureSaved", false));

		}
		catch(NullPointerException e){}

   		Intent intent = new Intent(getApplicationContext(), LockScreen.class);
   		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   		startActivity(intent);	 
	}
	
}
