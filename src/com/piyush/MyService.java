	package com.piyush;
	
import java.util.List;
import java.util.Timer;

import receiver.LockScreenReceiver;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
	
	public class MyService extends Service
	{
		 BroadcastReceiver mReceiver;
 
		 public GlobalVariables g = GlobalVariables.getInstance();
			
		 private final IBinder myBinder = new MyLocalBinder();
			
			@Override
			public IBinder onBind(Intent arg0) 
			{
				return myBinder;
			}

			public class MyLocalBinder extends Binder 
			{
		        MyService getService() 
		        {
		            return MyService.this;
		        }
			}
	
			
	@Override
	public void onCreate() {
		Log.w("piyush");
		Log.w("com.atomic.lock", "Service RUN");

	     IntentFilter filterScreen = new IntentFilter(Intent.ACTION_SCREEN_OFF);
	     IntentFilter filterBoot = new IntentFilter(Intent.ACTION_BOOT_COMPLETED);
	     
	     mReceiver = new LockScreenReceiver();
	     registerReceiver(mReceiver, filterScreen);
	     registerReceiver(mReceiver, filterBoot);

	     super.onCreate();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		Notification note= new Notification(0, "Atomic Lock Enabled", System.currentTimeMillis());
		startForeground(startId, note);
	}
	
	@Override
	public void onDestroy() {
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	public void Run()
	{
	   		Intent intent = new Intent(getApplicationContext(), LockScreen.class);
	   		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   		startActivity(intent);	   
	}	
	
	public void autoLockTimer() {
		double time = 1000*g.getAutoLockTime();
		Log.w("com.atomic.lock", "Timer Started");

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  @Override
		  public void run() {
			  g.setLockIsOn(true);
				Log.w("com.atomic.lock", "Lock is On");

		  }
		}, (long) time);
	}
}
