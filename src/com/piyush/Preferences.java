package com.piyush;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.piyush.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Preferences extends PreferenceActivity{
	public GlobalVariables g = GlobalVariables.getInstance();
	MyService myService;
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {		

		g.setContext(getApplicationContext());

		reCreateVariables();
		
		if(!(g.getServiceOn()))
		{
		PackageManager pm = getBaseContext().getPackageManager(); 
		pm.setComponentEnabledSetting(new ComponentName(Preferences.this, LockScreen.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		}
		
		Log.d("com.lockscreen", "ONCREATE");

		try{
			if(g.isSecurity())
				{
					Log.d("com.lockscreen", "LAUNCHSECURITY ONCREATE = " + g.isSecurity());
					
				    Intent myIntent = new Intent(Preferences.this, PreferenceLockScreen.class);
				    Preferences.this.startActivity(myIntent);
				}
			}
			catch(NullPointerException e)
			{
				
			}

		super.onCreate(savedInstanceState);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		addPreferencesFromResource(R.xml.preferences);

		final SwitchPreference enablePin = (SwitchPreference) getPreferenceManager().findPreference("enablePin");
		enablePin.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	        public boolean onPreferenceClick(Preference preference) {
	            Log.d("com.atomic.lock", "clicked");

	            final CharSequence on = "PIN IS ON";
	    		final CharSequence off = "PIN IS OFF";
	    		final Context mContext=getApplicationContext();
	    		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		 		if(prefs.getBoolean("setPin", false)) {
		 				g.setPinEnabled(false);
		    	    	Toast.makeText(mContext, off, Toast.LENGTH_SHORT).show();			    	    	
						Log.w("com.lockscreen", "setPinEnabled=false");
		    	} 
		    	else
		    	{
		    	    	Toast.makeText(mContext, on, Toast.LENGTH_SHORT).show();			    	    	
						setPin();
		    	}   
	            
	            boolean checked = ((SwitchPreference) preference).isChecked();
	            if (checked) {
		            Log.d("com.atomic.lock", "REVERSE + set to false");

	            	enablePin.setChecked(false);
            		g.setReverse(true);
		            return false;
	            }
	            else
	            {
		            Log.d("com.atomic.lock", "REVERSE + set to true");

	            	enablePin.setChecked(true);
            		g.setReverse(true);
		            return true;
	            }
	        }
	        });
		enablePin.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
	        @Override
	        public boolean onPreferenceChange(Preference preference,Object newValue) 
	        {
	            boolean switched = ((SwitchPreference) preference).isChecked();
	            if(g.isReverse())
	            {
		            Log.d("com.atomic.lock", "Reverse");

		            g.setReverse(false);
			        onPreferenceChange(preference, newValue);
		            return true;
	            }
	            else
	            {
		            Log.d("com.atomic.lock", "NO REVERSE");

	            	enablePin.setChecked(switched);
		            boolean switched2 = ((SwitchPreference) preference).isChecked();
		            Log.d("com.atomic.lock", "" + !switched2);
		            
		            if(!switched2)
		            {
		            	g.setPinEnabled(false);
			            Log.d("com.atomic.lock", "g.setPinEnabled(true);");

		            }
		            if(switched2)
		            {
		            	g.setPinEnabled(true);
			            Log.d("com.atomic.lock", "g.setPinEnabled(false);");
		            }


		            return true;
	            }
	        }
	    });
			
		final SwitchPreference enablePass = (SwitchPreference) getPreferenceManager().findPreference("enablePass");
		enablePass.setOnPreferenceClickListener(new OnPreferenceClickListener() {
	        public boolean onPreferenceClick(Preference preference) {
	            Log.d("com.atomic.lock", "clicked");

	            final CharSequence on = "Pass IS ON";
	    		final CharSequence off = "Pass IS OFF";
	    		final Context mContext=getApplicationContext();
	    		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

	    		 		if(prefs.getBoolean("setPass", false)) {
	    		 				g.setPassEnabled(false);
	    		    	    	Toast.makeText(mContext, off, Toast.LENGTH_SHORT).show();			    	    	
	    						Log.w("com.lockscreen", "setPassEnabled=false");
	    		    	} 
	    		    	else
	    		    	{
	    		    	    	Toast.makeText(mContext, on, Toast.LENGTH_SHORT).show();			    	    	
	    						setPass();
	    		    	}   
	            
	            boolean checked = ((SwitchPreference) preference).isChecked();
	            if (checked) {
		            Log.d("com.atomic.lock", "REVERSE + set to false");

	            	enablePass.setChecked(false);
            		g.setReverse2(true);
		            return false;
	            }
	            else
	            {
		            Log.d("com.atomic.lock", "REVERSE + set to true");

	            	enablePass.setChecked(true);
            		g.setReverse2(true);
		            return true;
	            }
	        }
	        });
		enablePass.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
	        @Override
	        public boolean onPreferenceChange(Preference preference,Object newValue) 
	        {
	            boolean switched = ((SwitchPreference) preference).isChecked();
	            if(g.isReverse2())
	            {
		            Log.d("com.atomic.lock", "Reverse");

		            g.setReverse2(false);
			        onPreferenceChange(preference, newValue);
		            return true;
	            }
	            else
	            {
		            Log.d("com.atomic.lock", "NO REVERSE");

	            	enablePass.setChecked(switched);
		            boolean switched2 = ((SwitchPreference) preference).isChecked();
		            Log.d("com.atomic.lock", "" + !switched2);
		            
		            if(!switched2)
		            {
		            	g.setPassEnabled(false);
			            Log.d("com.atomic.lock", "g.setPinEnabled(true);");

		            }
		            if(switched2)
		            {
		            	g.setPinEnabled(true);
			            Log.d("com.atomic.lock", "g.setPinEnabled(false);");
		            }


		            return true;
	            }
	        }
	    });
		
		final CheckBoxPreference enableService = (CheckBoxPreference) getPreferenceManager().findPreference("service");
		enableService.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {            
		        public boolean onPreferenceChange(Preference preference, Object newValue) {
		        	enableService();
			    	return true;
		        }
		    }); 
		
		final CheckBoxPreference keyguard = (CheckBoxPreference) getPreferenceManager().findPreference("keyguard");
		keyguard.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {            
		        public boolean onPreferenceChange(Preference preference, Object newValue) {
		        	keyguard();
			    	return true;
		        }
		    }); 
	
		
		
		final ListPreference listPreference = (ListPreference) findPreference("lp_list_preference");
	        if(listPreference.getValue()==null) {
	            listPreference.setValueIndex(0);
				g.setAutoLockTime(0.0);
	        }
	        
	    listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
	            public boolean onPreferenceChange(Preference preference, Object newValue) {
	            	listPreference.setValue((String) newValue);
	            	double autoLockTime = Double.parseDouble((String) newValue);
	            	g.setAutoLockTime(autoLockTime);
	            	return false;
	            }
	        });
	    
		final ListPreference launchers = (ListPreference) findPreference("launchers");
		CharSequence[] entries = getLaunchersPackages();
		CharSequence[] entryValues = getLaunchersNames();		
		launchers.setEntries(entries);
		launchers.setEntryValues(entryValues);
		launchers.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
            	int index = launchers.findIndexOfValue((String) newValue); 
            	String name = (String) newValue;   
            	String packageName = (String) launchers.getEntries()[index];
            	g.setName(name);
            	g.setPackageName(packageName);
            	try{
            		if(g.isGestureSaved())
            		{
        				Log.w("com.atomic.lock", "enableService.setEnabled(true);");
            			enableService.setEnabled(true);
            		}
            	}
            	catch(NullPointerException e){}
				Log.w("com.atomic.lock", "" + newValue + " : " + packageName);
            	return false;
            }
        });
			
			setEnabled();
	}

	@SuppressWarnings("deprecation")
	public void enableService()
	{
		final CharSequence on = "ATOMIC LOCK IS ON";
		final CharSequence off = "ATOMIC LOCK IS OFF";
		final Context mContext=getApplicationContext();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		final Preference keyguard = (Preference) getPreferenceManager().findPreference("keyguard");

		 		if(prefs.getBoolean("service", false)) 
		 		{
		    		PackageManager pm = getBaseContext().getPackageManager(); 
		    		pm.setComponentEnabledSetting(new ComponentName(Preferences.this, LockScreen.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
		        	
			    		stopService(new Intent(mContext, MyService.class));
			    		Toast.makeText(mContext, off, Toast.LENGTH_SHORT).show();		    		
						g.setServiceOn(false);
				    	keyguard.setEnabled(false);
				    	g.setFromPreference(false);
						Log.w("com.lockscreen", "ServiceOn=false");
		    	} 
		    	else
		    	{
		    		PackageManager pm = getBaseContext().getPackageManager(); 
		    		pm.setComponentEnabledSetting(new ComponentName(Preferences.this, LockScreen.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
		        	
			    		startService(new Intent(mContext, MyService.class));
		    	    	Toast.makeText(mContext, on, Toast.LENGTH_SHORT).show();			    	    	
						GlobalVariables g = GlobalVariables.getInstance();
						g.setServiceOn(true);
						g.setSendToHome(true);
						g.setFromPreference(true);
				    	keyguard.setEnabled(true);
						Log.w("com.lockscreen", "ServiceOn=true");
		    	}   
	}
	
	
	public void keyguard()
	{
		final CheckBoxPreference keyguard = (CheckBoxPreference) getPreferenceManager().findPreference("keyguard");
	
		if (keyguard.isChecked()) {
				g.setKeyguard(false);
				Log.w("com.lockscreen", "KEYGUARD" + g.isKeyguard());
			} 
		    	
		else
		   	{
		   		g.setKeyguard(true);
				Log.w("com.lockscreen", "KEYGUARD" + g.isKeyguard());
		   	}
	}

	public void setPin()
	{
		View dialogLayout = getLayoutInflater().inflate(R.layout.set_pin, null);

   		final EditText pin = (EditText)dialogLayout.findViewById(R.id.pin);
		final EditText pin2 = (EditText)dialogLayout.findViewById(R.id.pin2);
		final EditText pin3 = (EditText)dialogLayout.findViewById(R.id.pin3);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);		
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	    if(pin.getText().toString().equals(g.getPin()) && pin2.getText().toString().equals(pin3.getText().toString()))
		        	    {
		        	    	
							AlertDialog.Builder error = new AlertDialog.Builder(Preferences.this);
							error.setTitle("Pin");
							error.setMessage("Your pin was set successfully.");
							error.setPositiveButton("OK", new OnClickListener() {
					            public void onClick(DialogInterface arg0, int arg1) {
					            }
					        });
							error.show();
							
		        	    g.setPin(pin3.getText().toString());	
		    			g.setPinEnabled(true);
		                Log.w("com.lockscreen", "SET: " + pin.getText().toString() +" + "+ pin2.getText().toString() +" + "+ pin3.getText().toString());
		        	    }
		        	    
		       			else
		       			{
		       				
		       				AlertDialog.Builder error = new AlertDialog.Builder(Preferences.this);
							error.setTitle("Pin");
							error.setMessage("Some of your information did not match. Pin not set.");
							error.setPositiveButton("OK", new OnClickListener() {
					            public void onClick(DialogInterface arg0, int arg1) {
					            }
					        });
							error.show();

		       			}
		           }
		       });
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               Log.w("com.atomic.lock", "CANCEL");
		           }
		       });
		builder.setView(dialogLayout);
		AlertDialog dialog = builder.create();
		dialog.show();	
		
		Log.w("com.atomic.lock", "set pin");
	}
	
	
	public void setPass()
	{
		View dialogLayout = getLayoutInflater().inflate(R.layout.set_pass, null);

   		final EditText pass = (EditText)dialogLayout.findViewById(R.id.pass);
		final EditText pass2 = (EditText)dialogLayout.findViewById(R.id.pass2);
		final EditText pass3 = (EditText)dialogLayout.findViewById(R.id.pass3);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);		
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() 
		{
           public void onClick(DialogInterface dialog, int id) 
           {
        	    if(pass.getText().toString().equals(g.getPin()) && pass2.getText().toString().equals(pass3.getText().toString()))
        	    {
        	    	
        	    	AlertDialog.Builder error = new AlertDialog.Builder(Preferences.this);
					error.setTitle("Passwrod");
					error.setMessage("Your passwrod was set successfully.");
					error.setPositiveButton("OK", new OnClickListener() {
			            public void onClick(DialogInterface arg0, int arg1) 
			            {
			            }
			        });
					error.show();
					
        	    g.setPass(pass3.getText().toString());	
    			g.setPassEnabled(true);
                Log.w("com.lockscreen", "SET: " + pass.getText().toString() +" + "+ pass2.getText().toString() +" + "+ pass3.getText().toString());
        	    }
       			else
       			{
        	    	AlertDialog.Builder error = new AlertDialog.Builder(Preferences.this);
					error.setTitle("Password");
					error.setMessage("Some of your information did not match. Password not set.");
					error.setPositiveButton("OK", new OnClickListener() {
			            public void onClick(DialogInterface arg0, int arg1) {
			            }
			        });
					error.show();
       			}
           }
       });
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               Log.w("com.atomic.lock", "CANCEL");
		           }
		       });
		builder.setView(dialogLayout);
		AlertDialog dialog = builder.create();
		dialog.show();
		Log.w("com.atomic.lock", "set pass");
	}

	public CharSequence[] getLaunchersPackages()
	{			
		List<String> listItems = new ArrayList<String>();

		PackageManager pm = getPackageManager();
		Intent i = new Intent("android.intent.action.MAIN");
		i.addCategory("android.intent.category.HOME");
		List<ResolveInfo> lst = pm.queryIntentActivities(i, 0);
		if (lst != null) {
		   for (ResolveInfo resolveInfo : lst) {
			   if(!resolveInfo.activityInfo.packageName.equalsIgnoreCase("com.atomic.lock"))
			   {
		        Log.d("Package", "New Launcher Found: " + resolveInfo.activityInfo.packageName);
		        listItems.add(resolveInfo.activityInfo.packageName);	
		   	   }
		   }
		}
		
		final CharSequence[] launchers = listItems.toArray(new CharSequence[listItems.size()]);
		return launchers;
	}
	public CharSequence[] getLaunchersNames()
	{	
		List<String> listItems = new ArrayList<String>();

		PackageManager pm = getPackageManager();
		Intent i = new Intent("android.intent.action.MAIN");
		i.addCategory("android.intent.category.HOME");
		List<ResolveInfo> lst = pm.queryIntentActivities(i, 0);
		if (lst != null) {
		   for (ResolveInfo resolveInfo : lst) {
			   if(!resolveInfo.activityInfo.name.equalsIgnoreCase("com.atomic.lock.LockScreen"))
			   {
		        Log.d("Name", "New Launcher Found: " + resolveInfo.activityInfo.name);
		        listItems.add(resolveInfo.activityInfo.name);		
			   }
		   }
		}
		
		final CharSequence[] launchers = listItems.toArray(new CharSequence[listItems.size()]);

		return launchers;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onResume(){
		Log.d("com.lockscreen", "ONRESUME");
        super.onResume();

        setEnabled();
        
			try{
				if(g.isSecurity())
					{
						Log.d("com.lockscreen", "LAUNCHSECURITY ONRESUME= " + g.isSecurity());
					    Intent myIntent = new Intent(Preferences.this, PreferenceLockScreen.class);
					    Preferences.this.startActivity(myIntent);
					}
				}
				catch(NullPointerException e)
				{
					
				}	
			Log.w("com.atomic.lock", "set launcher = " + g.getPackageName() + " : " + g.getName());
    }
	
	private void reCreateVariables() 
	{        
		try
		{
			g.setServiceOn(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("serviceOn", false));
			g.setLockIsOn(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("lockIsOn", false));
			g.setLockScore(Double.longBitsToDouble(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong("LockScore", 0)));
			
			ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
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
	}
	
	public void setEnabled()
	{
	    GestureLibrary sStore;
	    File mStoreFile = new File(Environment.getExternalStorageDirectory(), "gestures");
    	sStore = GestureLibraries.fromFile(mStoreFile);
    	if(sStore!=null)
    	{
			Log.w("com.atomic.lock", "setGestureSaved(true);");
    		g.setGestureSaved(true);
    	}
		
		CheckBoxPreference enableService = (CheckBoxPreference) getPreferenceManager().findPreference("service");
		
		CheckBoxPreference keyguard = (CheckBoxPreference) getPreferenceManager().findPreference("keyguard");
		
		try {
			if(!(g.getPackageName()==null) && !(g.getPackageName()==null) && g.isGestureSaved());
			{
				enableService.setEnabled(true);
			}
			if(g.getPackageName()==null || (g.getPackageName()==null) || !g.isGestureSaved());
			{
				enableService.setEnabled(false);
				keyguard.setEnabled(false);
			}
			if(g.getServiceOn());
			{
				keyguard.setEnabled(true);
			}
			if(!g.getServiceOn());
			{
				keyguard.setEnabled(false);
			}
	    } catch (NullPointerException e) {	       
	    }
	}
	
}






