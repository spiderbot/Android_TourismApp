package com.example.tourismapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class StartScreenActivity extends Activity implements Runnable {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// code that displays the content in full screen mode
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.frontscreenactivity);

		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent startIntent = new Intent(StartScreenActivity.this,
				MainActivity.class);
		startActivity(startIntent);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		StartScreenActivity.this.finish();
	}
}
