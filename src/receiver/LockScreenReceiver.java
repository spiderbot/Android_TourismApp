package receiver;
import java.util.List;

import com.piyush.GlobalVariables;
import com.piyush.LockScreen;
import com.piyush.MyService;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;


@SuppressWarnings("deprecation")
public class LockScreenReceiver extends BroadcastReceiver {
	 public static boolean wasScreenOn = true;
	 public static Context mContext;
	 GlobalVariables g = GlobalVariables.getInstance();


	@Override
	public void onReceive(final Context context, Intent intent) {
	    Bundle extras = intent.getExtras();
		GlobalVariables g = GlobalVariables.getInstance();			

		ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
		// get the info from the currently running task 
	     List <ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1); 
	     ComponentName componentInfo = taskInfo.get(0).topActivity;
	     String previousAppName = componentInfo.getPackageName();
	     
			g.setPreviousAppComponent(componentInfo);
			g.setPreviousAppName(previousAppName);
     	 
		    extras = intent.getExtras();
		    if (extras != null) {
		    String state = extras.getString(TelephonyManager.EXTRA_STATE);

		     if (state.equals(TelephonyManager.EXTRA_STATE_RINGING) && g.getLockIsOn()) 
             {
		             new Handler().postDelayed(new Runnable() 
                     {
    		              public void run() 
                          {
        		              Intent intentPhoneCall = new Intent("android.intent.action.ANSWER");
        		              intentPhoneCall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        		              context.startActivity(intentPhoneCall);
		                  }
		             }, 500);
		         }
		    }
			
			mContext = context; 
		
			
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) 
        {
           	if(g.getServiceOn())
        	{
        	g.setSendToHome(false);
        	g.setSecurity(true);
            g.setFromPreference(false);

        	Log.w("com.atomic.lock", "SendToHome=false");
        	Log.w("com.atomic.lock", "LockIsOn=true");
        	Log.w("com.atomic.lock", "SecurityIsOn=true");
        	Log.w("com.atomic.lock", "screenOff");
        	
        	Intent intent11 = new Intent(context, LockScreen.class);
        	intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(intent11);
        	
        	Log.w("com.atomic.lock", "RUN LOCKSCREEN");

        	}

        } 
       
        
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) && g.getLockIsOn())
        {	
	       	Log.w("com.atomic.lock", "bootUp");
	    		
	    	if(g.getServiceOn())
	    	{
	        g.setFromPreference(false);
	       	context.startService(new Intent(mContext, MyService.class));
	    	Intent intent11 = new Intent(context,LockScreen.class);
	    	intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	context.startActivity(intent11);
			Log.w("com.atomic.lock", "Service has been restarted");
    		}

       }
    }
}
