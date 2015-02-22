package com.example.tourismapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewClass extends Activity {
	ListView listOfPlaces;
	Bundle bundle;
	Intent newIntent;
	Dialog d;
	AutoCompleteTextView lookUp;
	String[] places;
	int[] imagesOfPlaces;
	String detail;
	String[] options = { "Read History Or Request Address",
			"Navigate Through Google Map", "Know The Nearest Metro Station" };

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// code that displays the content in full screen mode
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		listOfPlaces = (ListView) findViewById(R.id.listofplaces);

		bundle = getIntent().getExtras();
		places = bundle.getStringArray("places");

		newIntent = new Intent(NewClass.this, Goto.class);

		MyAdapter adapter2 = new MyAdapter(NewClass.this,
				R.layout.placestypelayout, places);
		listOfPlaces.setAdapter(adapter2);

		listOfPlaces.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (places[arg2].equals("Red-Fort")) {
					detail = "Red-Fort";
				} else if (places[arg2]
						.equals("Jantar-Mantar (Connaught Place)")) {
					detail = "Jantar-Mantar (Connaught Place)";
				} else if (places[arg2].equals("Lal Bangla")) {
					detail = "Lal Bangla";
				} else if (places[arg2].equals("Lal Gumbad")) {
					detail = "Lal Gumbad";
				} else if (places[arg2].equals("Ashoka's Pillar")) {
					detail = "Ashoka's Pillar";
				} else if (places[arg2].equals("Delhi Gate(India Gate)")) {
					detail = "Delhi Gate(India Gate)";
				} else if (places[arg2].equals("Humayun's Tomb")) {
					detail = "Humayun's Tomb";
				} else if (places[arg2].equals("Akshardham-Temple")) {
					detail = "Akshardham-Temple";
				} else if (places[arg2].equals("LakshmiNarayan Temple")) {
					detail = "LakshmiNarayan Temple";
				} else if (places[arg2]
						.equals("Cathedral Church Of Redemption")) {
					detail = "Cathedral Church Of Redemption";
				} else if (places[arg2].equals("Gurudwara Bangla Sahib")) {
					detail = "Gurudwara Bangla Sahib";
				} else if (places[arg2].equals("ISKCON Temple")) {
					detail = "ISKCON Temple";
				} else if (places[arg2].equals("Jama Masjid")) {
					detail = "Jama Masjid";
				} else if (places[arg2].equals("Lotus Temple")) {
					detail = "Lotus Temple";
				} else if (places[arg2].equals("St. James Church")) {
					detail = "St. James Church";
				} else if (places[arg2].equals("Kalka Ji Mandir")) {
					detail = "Kalka Ji Mandir";
				} else if (places[arg2].equals("Nizamuddin Dargah")) {
					detail = "Nizamuddin Dargah";
				} else if (places[arg2].equals("Funland")) {
					detail = "Funland";
				} else if (places[arg2].equals("HangOut")) {
					detail = "HangOut";
				} else if (places[arg2].equals("Adventure-Island")) {
					detail = "Adventure-Island";
				} else if (places[arg2]
						.equals("Indian Mountaineering Foundation")) {
					detail = "Indian Mountaineering Foundation";
				} else if (places[arg2].equals("SurajKund Lake")) {
					detail = "SurajKund Lake";
				} else if (places[arg2].equals("FerozShahKotla")) {
					detail = "FerozShahKotla";
				} else if (places[arg2].equals("Indira Gandhi Sports Complex")) {
					detail = "Indira Gandhi Sports Complex";
				} else if (places[arg2].equals("Major DhyanChand Stadium")) {
					detail = "Major DhyanChand Stadium";
				} else if (places[arg2].equals("Talkatora Indoor Stadium")) {
					detail = "Talkatora Indoor Stadium";
				} else if (places[arg2].equals("JawaharLal Nehru Stadium")) {
					detail = "JawaharLal Nehru Stadium";
				} else if (places[arg2].equals("National Museum")) {
					detail = "National Museum";
				} else if (places[arg2].equals("National Rail Museum")) {
					detail = "National Rail Museum";
				} else if (places[arg2].equals("National Zoological Park")) {
					detail = "National Zoological Park";
				}
				newIntent.putExtra("detail", detail);
				newIntent.putExtra("options", options);
				startActivity(newIntent);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_for_others, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.gotomain:
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);

			break;
		case R.id.exit:
			AlertDialog.Builder alert = new AlertDialog.Builder(NewClass.this);
			alert.setMessage("Are you sure");
			alert.setCancelable(true); // this makes the dialog box to close
										// when the back button is pressed //
			alert.setTitle("Exit");
			alert.create();
			alert.setPositiveButton("ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

				}
			});
			alert.setNegativeButton("cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss(); // closes the dialog box

				}
			});
			alert.show();
			break;
		case R.id.lookupdirectly:
			String[] places = { "Red-Fort", "Jantar-Mantar (Connaught Place)",
					"Lal Bangla", "Lal Gumbad", "Ashoka's Pillar",
					"Delhi Gate(India Gate)", "Humayun's Tomb",
					"Akshardham-Temple", "LakshmiNarayan Temple",
					"Cathedral Church Of Redemption", "Gurudwara Bangla Sahib",
					"ISKCON Temple", "Jama Masjid", "Lotus Temple",
					"St. James Church", "Kalka Ji Mandir", "Nizamuddin Dargah",
					"Funland", "HangOut", "Adventure-Island",
					"Indian Mountaineering Foundation", "SurajKund Lake",
					"FerozShahKotla", "Indira Gandhi Sports Complex",
					"Major DhyanChand Stadium", "Talkatora Indoor Stadium",
					"JawaharLal Nehru Stadium", "National Museum",
					"National Rail Museum", "National Zoological Park" };
			d = new Dialog(NewClass.this);
			d.setContentView(R.layout.newlayout);
			d.setTitle("Enter The Location");
			d.setCancelable(true);
			d.show();
			Button b1 = (Button) d.findViewById(R.id.dbutton1);
			Button b2 = (Button) d.findViewById(R.id.dbutton2);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.select_dialog_item, places);
			lookUp = (AutoCompleteTextView) d
					.findViewById(R.id.autoCompleteTextView1);
			lookUp.setThreshold(1);
			lookUp.setTextColor(Color.BLUE);
			lookUp.setAdapter(adapter);
			b1.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					String str = lookUp.getText().toString();
					Toast.makeText(NewClass.this, str, Toast.LENGTH_SHORT)
							.show();
					d.dismiss();
				}
			});
			b2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					d.dismiss();
				}
			});
			break;
		}
		return super.onOptionsItemSelected(item);

	}

	class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View placestypelayout = inflater.inflate(R.layout.placestypelayout,
					null);
			ImageView imageOfPlace = (ImageView) placestypelayout
					.findViewById(R.id.imageofplace);
			TextView nameOfPlace = (TextView) placestypelayout
					.findViewById(R.id.nameofplace);
			nameOfPlace.setText(places[position]);
			return placestypelayout;
		}

	}
}
