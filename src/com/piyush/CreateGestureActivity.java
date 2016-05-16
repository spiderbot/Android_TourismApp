package com.piyush;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.MotionEvent;
import android.gesture.GestureOverlayView;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.File;

import com.piyush.R;

public class CreateGestureActivity extends Activity {
    private static final float LENGTH_THRESHOLD = 200.0f;
    private static final float LENGTH_MAX = 5000.0f;
    private String str;
    private Gesture mGesture;
    private View mDoneButton;
    private GestureOverlayView clearOverlay;
    private GestureLibrary gestureLib;
    private final String path = new File(Environment.getExternalStorageDirectory(),"gestures").getAbsolutePath();
    private WallpaperManager wallpaperManager;
    private Drawable wallpaperDrawable;
    private String string;
    //comment
    //commenting again
    //checking
    private int i;
    private double d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.create_gesture);

        mDoneButton = findViewById(R.id.done);

    	wallpaperManager = WallpaperManager.getInstance(this);
        wallpaperDrawable = wallpaperManager.getDrawable();
    	getWindow().setBackgroundDrawable(wallpaperDrawable);
    	
        GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);
        clearOverlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);

        overlay.addOnGestureListener(new GesturesProcessor());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        if (mGesture != null) {
            outState.putParcelable("gesture", mGesture);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        
        mGesture = savedInstanceState.getParcelable("gesture");
        if (mGesture != null) {
            final GestureOverlayView overlay =
                    (GestureOverlayView) findViewById(R.id.gestures_overlay);
            overlay.post(new Runnable() {
                public void run() {
                    overlay.setGesture(mGesture);
                }
            });

            mDoneButton.setEnabled(true);
        }
    }

    @SuppressWarnings({"UnusedDeclaration"})
    public void addGesture(View v) {
        if (mGesture != null && mGesture.getLength() > 100) {
            final CharSequence name = "Current";

//            File mStoreFile = new File(Environment.getExternalStorageDirectory(), "gestures");

            final GestureLibrary store = GestureBuilderActivity.getStore();
            store.addGesture(name.toString(), mGesture);
            store.save();

            setResult(RESULT_OK);
            
            Toast.makeText(this, getString(R.string.save_success, path), Toast.LENGTH_LONG).show();
            
        } 
        
        else {
            setResult(RESULT_CANCELED);
        }

        
        
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        
    }
    
    @SuppressWarnings({"UnusedDeclaration"})
    public void clearGesture(View v) {
    	clearOverlay.clear(false);
    }
    
    @SuppressWarnings({"UnusedDeclaration"})
    public void cancelGesture(View v) {
        setResult(RESULT_CANCELED);
        Intent intent = new Intent(this, Preferences.class);
        startActivity(intent);
        //finish();
    }
    
    
    private class GesturesProcessor implements GestureOverlayView.OnGestureListener {
        public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
            mDoneButton.setEnabled(false);
            mGesture = null;
        }

        public void onGesture(GestureOverlayView overlay, MotionEvent event) {
        }

        public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
            mGesture = overlay.getGesture();
            
            if (mGesture.getLength() < LENGTH_THRESHOLD) {
                overlay.clear(false);
                mDoneButton.setEnabled(false);

                final CharSequence lengthShort = "Please Design A Longer Pattern";
	    		final Toast toast = Toast.makeText(getApplicationContext(), lengthShort, Toast.LENGTH_SHORT);
	    		toast.show();
	    		
	    		Handler handler = new Handler();
	            handler.postDelayed(new Runnable() {
	               @Override
	               public void run() {
	                   toast.cancel(); 
	               }
	        }, 1000);
            }
	            
	            if (mGesture.getLength() > LENGTH_MAX) {
	                overlay.clear(false);
	                mDoneButton.setEnabled(false);

	                final CharSequence lengthShort2 = "Please Design A Shorter Pattern";
		    		final Toast toast2 = Toast.makeText(getApplicationContext(), lengthShort2, Toast.LENGTH_SHORT);
		    		toast2.show();
		    		
		    		Handler handler2 = new Handler();
		            handler2.postDelayed(new Runnable() {
		               @Override
		               public void run() {
		                   toast2.cancel(); 
		               }
		        }, 1000);
	            }
	            
	            double strokeCount = mGesture.getStrokesCount();

	            if (strokeCount > 6) {
	                overlay.clear(false);
	                mDoneButton.setEnabled(false);

	                final CharSequence lengthShort3 = "Please Use Fewer Than 7 Strokes";
		    		final Toast toast3 = Toast.makeText(getApplicationContext(), lengthShort3, Toast.LENGTH_SHORT);
		    		toast3.show();
		    		
		    		Handler handler3 = new Handler();
		            handler3.postDelayed(new Runnable() {
		               @Override
		               public void run() {
		                   toast3.cancel(); 
		               }
		        }, 1000);
		            
	            }
            
            else
            mDoneButton.setEnabled(true);
        }

        public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
        }
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    }
}
