package com.example.tourismapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView listOfPlaceType;
	AutoCompleteTextView lookUp;
	Dialog d;
	String[] placesType = { "Historical", "Spiritual", "Adventure",
			"Kids-Zone", "Shopping-Malls", "Stadiums", "Museums" };
	int[] placesTypeImages = { R.drawable.india_gate,
			R.drawable.iskcon_temple2, 0, R.drawable.kids_zone, 0,
			R.drawable.jawaharlal_nehru_stadium,
			R.drawable.national_zoological_park };
	int[] historicalImages = {};
	int[] spiritualImages = {};
	int[] adventureImages = {};
	int[] kidsZoneImages = {};
	int[] shoppingMallsImages = {};
	int[] stadiumsImages = {};
	int[] museumsImages = {};
	String[] historicalPlaces = { "Red-Fort",
			"Jantar-Mantar (Connaught Place)", "Lal Bangla", "Lal Gumbad",
			"Ashoka's Pillar", "Delhi Gate(India Gate)", "Humayun's Tomb" };
	String[] spiritualPlaces = { "Akshardham-Temple", "LakshmiNarayan Temple",
			"Cathedral Church Of Redemption", "Gurudwara Bangla Sahib",
			"ISKCON Temple", "Jama Masjid", "Lotus Temple",
			"St. James' Church", "Kalka Ji Mandir", "Nizamuddin Dargah" };
	String[] adventurePlaces = { "FunLand", "HangOut", "Adventure-Island",
			"Indian Mountaineering Foundation", "SurajKund Lake" };
	String[] kidsZone = {};
	String[] shoppingMalls = { "GIP Noida", "TDI Mall", "Galaxy Mall",
			"Pacific Mall", "CTC Mall", "Shipra Mall",
			"The Great Indian Palace", "Ansal Plaza", "Sahara Mall",
			"Square One Mall", "MGF Metropolitan Mall", "Sab Mall",
			"Crowne Plaza", "Ambience Mall", "DLF Mega Mall", "Shopprix Mall", };
	String[] stadiums = { "FerozShahKotla", "Indira Gandhi Sports Complex",
			"Major DhyanChand Stadium", "Talkatora Indoor Stadium",
			"JawaharLal Nehru Stadium" };
	String[] museums = { "National Museum", "National Rail Museum",
			"National Zoological Park" };
	Bundle bundleOfPlaces;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// code that displays the content in full screen mode
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);

		bundleOfPlaces = new Bundle();
		intent = new Intent(MainActivity.this, NewClass.class);

		listOfPlaceType = (ListView) findViewById(R.id.listofplaces);
		MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.row,
				placesType);
		listOfPlaceType.setAdapter(adapter);
		listOfPlaceType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					bundleOfPlaces.putStringArray("places", historicalPlaces);
					bundleOfPlaces.putIntArray("images", historicalImages);
					break;
				case 1:
					bundleOfPlaces.putStringArray("places", spiritualPlaces);
					bundleOfPlaces.putIntArray("images", spiritualImages);
					break;
				case 2:
					bundleOfPlaces.putStringArray("places", adventurePlaces);
					bundleOfPlaces.putIntArray("images", adventureImages);
					break;
				case 3:
					bundleOfPlaces.putStringArray("places", kidsZone);
					bundleOfPlaces.putIntArray("images", kidsZoneImages);
					break;
				case 4:
					bundleOfPlaces.putStringArray("places", shoppingMalls);
					bundleOfPlaces.putIntArray("images", shoppingMallsImages);
					break;
				case 5:
					bundleOfPlaces.putStringArray("places", stadiums);
					bundleOfPlaces.putIntArray("images", stadiumsImages);
					break;
				case 6:
					bundleOfPlaces.putStringArray("places", museums);
					bundleOfPlaces.putIntArray("images", museumsImages);
					break;
				}
				intent.putExtras(bundleOfPlaces);
				startActivity(intent);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			Toast.makeText(MainActivity.this, "about us", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.exit:
			AlertDialog.Builder alert = new AlertDialog.Builder(
					MainActivity.this);
			alert.setMessage("Are you sure");
			alert.setCancelable(true); // this makes the dialog box to close
										// when the back button is pressed //
			alert.setTitle("Exit");
			alert.create();
			alert.setPositiveButton("ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					MainActivity.this.finish();
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
			d = new Dialog(MainActivity.this);
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
					Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT)
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
			LayoutInflater li = getLayoutInflater();
			View row = li.inflate(R.layout.row, null);
			TextView typeName = (TextView) row.findViewById(R.id.typename);
			typeName.setText(placesType[position]);
			ImageView typeImage = (ImageView) row.findViewById(R.id.typepic);
			typeImage.setBackgroundResource(placesTypeImages[position]);
			return row;
		}

	}
}
