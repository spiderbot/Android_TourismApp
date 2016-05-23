package com.piyush;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

public class GlobalVariables extends Activity{
	
    private static GlobalVariables instance;
    private boolean serviceOn;
    private boolean lockIsOn;
    private double LockScore;
    private ComponentName previousAppComponent;
    private String previousAppName;
    private boolean sendToHome;
    private String pin;
    private String pass;
    private boolean pinEnabled;
    private boolean passEnabled;
    private String packageName;
    private String name;
    private boolean appNotShowing;
    private double autoLockTime;
    private boolean keyguard;
    private boolean security;
    private boolean fromPreference;
    private boolean reverse;
    private boolean reverse2;
    private boolean gestureSaved;

    private Context context;
    
    public void update()
    {   
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("serviceOn", serviceOn).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("lockIsOn", lockIsOn).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putLong("LockScore", Math.round(LockScore)).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("previousAppComponent", "" + previousAppComponent).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("previousAppName", previousAppName).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("sendToHome", sendToHome).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("pin", pin).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("pass", pass).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("pinEnabled", pinEnabled).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("passEnabled", passEnabled).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("packageName", packageName).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putString("name", name).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("appNotShowing", appNotShowing).commit();
    	PreferenceManager.getDefaultSharedPreferences(context).edit().putLong("autoLockTime", Math.round(autoLockTime)).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("keyguard", keyguard).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("security", security).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("fromPreference", fromPreference).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("packageName",packageName).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("reverse", reverse).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("reverse2", reverse2).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("gestureSaved", gestureSaved).commit();
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("name",name).commit();
    }
    
    public static synchronized GlobalVariables getInstance(){
	     if(instance==null)
	     {
	       Log.d("com.atomic.lock", "************* NEW GLOBAL VARIABLES *************");
	       instance=new GlobalVariables();
	     }
	     return instance;
	}
    
    public boolean getServiceOn() {
        return serviceOn;
    }

    public void setServiceOn(Boolean serviceOn) {
    	this.serviceOn = serviceOn;
    	update();
        Log.d("com.atomic.lock", "UPDATE: serviceOn = " + serviceOn);

    }
    

    public boolean getLockIsOn() {
        return lockIsOn;
    }

    public void setLockIsOn(Boolean lockIsOn) {
        this.lockIsOn = lockIsOn;
    	update();
        Log.d("com.atomic.lock", "UPDATE: lockIsOn = " + lockIsOn);
    }
    
    public double getLockScore() {
        return LockScore;
    }

    public void setLockScore(Double LockScore) {
        this.LockScore = LockScore;
    	update();
        Log.d("com.atomic.lock", "UPDATE: LockScore = " + LockScore);
    }
    
    public ComponentName getPreviousAppComponent() {
        return previousAppComponent;
        
    }

    public void setPreviousAppComponent(ComponentName previousAppComponent) {
        this.previousAppComponent = previousAppComponent;
    	update();
        Log.d("com.atomic.lock", "UPDATE: previousAppComponent = " + previousAppComponent);
    }
    
    public String getPreviousAppName() {
        return previousAppName;
    }

    public void setPreviousAppName(String previousAppName) {
        this.previousAppName = previousAppName;
    	update();
        Log.d("com.atomic.lock", "UPDATE: previousAppName = " + previousAppName);
    }
    
    public boolean getSendToHome() {
        return sendToHome;
    }

    public void setSendToHome(Boolean sendToHome) {
        this.sendToHome = sendToHome;
    	update();
        Log.d("com.atomic.lock", "UPDATE: sendToHome = " + sendToHome);
    }

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
		update();
        Log.d("com.atomic.lock", "UPDATE: pass = " + pass);
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
		update();
        Log.d("com.atomic.lock", "UPDATE: pin = " + pin);
	}

	public boolean isPinEnabled() {
		return pinEnabled;
	}

	public void setPinEnabled(boolean pinEnabled) {
		this.pinEnabled = pinEnabled;
		update();
        Log.d("com.atomic.lock", "UPDATE: pinEnabled = " + pinEnabled);
	}

	public boolean isPassEnabled() {
		return passEnabled;
	}

	public void setPassEnabled(boolean passEnabled) {
		this.passEnabled = passEnabled;
		update();
        Log.d("com.atomic.lock", "UPDATE: passEnabled = " + passEnabled);
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
		update();
        Log.d("com.atomic.lock", "UPDATE: packageName = " + packageName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		update();
        Log.d("com.atomic.lock", "UPDATE: name = " + name);
	}

	public boolean isAppNotShowing() {
		return appNotShowing;
	}

	public void setAppNotShowing(boolean appNotShowing) {
		this.appNotShowing = appNotShowing;
		update();
        Log.d("com.atomic.lock", "UPDATE: appNotShowing = " + appNotShowing);
	}

	public double getAutoLockTime() {
		return autoLockTime;
	}

	public void setAutoLockTime(double autoLockTime) {
		this.autoLockTime = autoLockTime;
		update();
        Log.d("com.atomic.lock", "UPDATE: autoLockTime = " + autoLockTime);
	}

	public boolean isKeyguard() {
		return keyguard;
	}

	public void setKeyguard(boolean keyguard) {
		this.keyguard = keyguard;
		update();
        Log.d("com.atomic.lock", "UPDATE: keyguard = " + keyguard);
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
		update();
        Log.d("com.atomic.lock", "UPDATE: security = " + security);
	}

	public boolean isFromPreference() {
		return fromPreference;
	}

	public void setFromPreference(boolean fromPreference) {
		this.fromPreference = fromPreference;
		update();
        Log.d("com.atomic.lock", "UPDATE: fromPreference = " + fromPreference);
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
		update();
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public boolean isReverse2() {
		return reverse;
	}

	public void setReverse2(boolean reverse) {
		this.reverse = reverse;
	}
	
	public boolean isGestureSaved() {
		return gestureSaved;
	}

	public void setGestureSaved(boolean gestureSaved) {
		this.gestureSaved = gestureSaved;
		update();
        Log.d("com.atomic.lock", "UPDATE: gestureSaved = " + gestureSaved);
	}
}