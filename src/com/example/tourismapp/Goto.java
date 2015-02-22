package com.example.tourismapp;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Goto extends Activity {
	ListView options;
	String[] optionsAvailable;
	String detail;
	Intent localIntent, mapIntent, metroIntent;
	Dialog d;
	AutoCompleteTextView lookUp;
	/*
	 * defining history of all the locations
	 */

	String historyOfRedfort = "red fort was the residence of old mughals , it is red in color. it is in delhi";
	String historyOfJantarMantar = "";
	String historyOfLalBangla = "";
	String historyOfLalGumbad = "";
	String historyOfAshokPillar = "";
	String historyOfDelhiGate = "";
	String historyOfHumayunTomb = "";
	String historyOfAkshardhamTemple = "";
	String historyOfLakshmiNarayanTemple = "";
	String historyOfCathedralChurchOfRedemption = "";
	String historyOfGurudwaraBanglaSahib = "";
	String historyOfISKCONTemple = "";
	String historyOfJamaMasjid = "";
	String historyOfLotusTemple = "";
	String historyOfStJamesChurch = "";
	String historyOfKalkaJiMandir = "";
	String historyOfNizamuddinDargah = "";
	String historyOfFunland = "";
	String historyOfHangOut = "";
	String historyOfAdventureIsland = "";
	String historyOfIndianMountaineeringFoundation = "";
	String historyOfSurajKundLake = "";
	String historyOfFerozShahKotla = "Feroz Shah Kotla Stadium was founded in 1883. It is one of the best cricket stadiums in India. Known to host some of the biggest cricket matches in the country this stadium offers a seating capacity of 48,000.";
	String historyOfIndiraGandhiSportsComplex = "Constructed in the year 1982 to host the indoor sports events of the Asian Games, the Indira Gandhi Sports Complex is situated in Indraprastha Estate in Delhi. Spreading over an area of 100 acres of lush green land this stadium is appointed as one of the major venues for hosting numerous sports of the 2010 Commonwealth Games. Centrally air-conditioned and offering a seating capacity of 25,000 this stadium is ideally located in the city.";
	String historyOfMajorDhyanChandStadium = "Situated in close proximity to the India Gate the Major Dhyan Chand National Stadium is named after the famous field hockey player, Dhyan Chand. Used for hosting several hockey matches, rallies, marathons and march past held in the city this sports complex is one of the major venues for the 2010 Commonwealth Games. The stadium has a spectator capacity of 25,000. ";
	String historyOfTalkatoraIndoorStadium = "The Talkatora Indoor Stadium is situated in Willingdon Crescent Road in New Delhi. Enjoying central location this stadium offers all the facilities of a modern sports complex. An indoor sports stadium that is popular for hosting games like table tennis and basketball this stadium is a popular practicing ground for most youngsters in the city. Serving as one of the venues for the 2010 Commonwealth Games this stadium offers a seating capacity of 3035";
	String historyOfJawaharLalNehruStadium = "Built in 1982 by the Government of India the Jawaharlal Nehru Stadium is situated in the C.G.O. Complex in Lodhi Road. Serving as an all purpose sports events stadium this ground was founded in 1982. Sprawling across 100 acres of land this stadium offers a spectator capacity of 60,000. Fully revamped this stadium has been chosen as one of the venues for the 2010 Commonwealth Games.";
	String historyOfNationalMuseum = "";
	String historyOfNationalRailMuseum = "";
	String historyOfNationalZoologicalPark = "";

	/*
	 * defining address of all locations
	 */

	String addressOfRedfort = "";
	String addressOfJantarMantar = "";
	String addressOfLalBangla = "";
	String addressOfLalGumbad = "Malviya Nagar, New Delhi, India";
	String addressOfAshokPillar = "";
	String addressOfDelhiGate = "RajPath, India Gate, New Delhi 110001";
	String addressOfHumayunTomb = "";
	String addressOfAkshardhamTemple = "";
	String addressOfLakshmiNarayanTemple = "";
	String addressOfCathedralChurchOfRedemption = "";
	String addressOfGurudwaraBanglaSahib = "";
	String addressOfISKCONTemple = "";
	String addressOfJamaMasjid = "";
	String addressOfLotusTemple = "";
	String addressOfStJamesChurch = "";
	String addressOfKalkaJiMandir = "";
	String addressOfNizamuddinDargah = "";
	String addressOfFunland = "";
	String addressOfHangOut = "";
	String addressOfAdventureIsland = "";
	String addressOfIndianMountaineeringFoundation = "";
	String addressOfSurajKundLake = "";
	String addressOfFerozShahKotla = "Bahadur Shah Zafar Marg, ITO, Delhi- 110002";
	String addressOfIndiraGandhiSportsComplex = "Sports Athority of India, ITO, Delhi- 110002";
	String addressOfMajorDhyanChandStadium = "Purana Quila, Pragati Maidan, Delhi - 110001";
	String addressOfTalkatoraIndoorStadium = "Talkatora Garden, Connaught Place, Delhi- 110001";
	String addressOfJawaharLalNehruStadium = "Bhisma Pitamah Road, Lodhi Road, Delhi- 110003";
	String addressOfNationalMuseum = "";
	String addressOfNationalRailMuseum = "";
	String addressOfNationalZoologicalPark = "";

	/*
	 * latitudes and longitudes of locations
	 */

	double latitudeOfRedFort = 28.655812600000000000;
	double longitudeOfRedFort = 77.241952200000010000;
	double latitudeOfJantarMantar = 28.6271;
	double longitudeOfJantarMantar = 77.2165;
	double latitudeOfLalBangla = 26.426805900000000000;
	double longitudeOfLalBangla = 80.386741700000020000;
	double latitudeOfLalGumbad = 28.540082300000000000;
	double longitudeOfLalGumbad = 77.213469700000020000;
	double latitudeOfAshokPillar = 13.09457800000000000;
	double longitudeOfAshokPillar = 80.172288699999970000;
	double latitudeOfDelhiGate = 28.612896200000000000;
	double longitudeOfDelhiGate = 77.22944800000000000;
	double latitudeOfHumayunTomb ;
	double longitudeOfHumayunTomb;
	double latitudeOfAkshardhamTemple;
	double longitudeOfAkshardhamTemple;
	double latitudeOfLakshmiNarayanTemple;
	double longitudeOfLakshmiNarayanTemple;
	double latitudeOfCathedralChurchOfRedemption;
	double longitudeOfCathedralChurchOfRedemption;
	double latitudeOfGurudwaraBanglaSahib;
	double longitudeOfGurudwaraBanglaSahib;
	double latitudeOfISKCONTemple;
	double longitudeOfISKCONTemple;
	double latitudeOfJamaMasjid;
	double longitudeOfJamaMasjid;
	double latitudeOfLotusTemple;
	double longitudeOfLotusTemple;
	double latitudeOfStJamesChurch;
	double longitudeOfStJamesChurch;
	double latitudeOfKalkaJiMandir;
	double longitudeOfKalkaJiMandir;
	double latitudeOfNizamuddinDargah;
	double longitudeOfNizamuddinDargah;
	double latitudeOfFunland;
	double longitudeOfFunland;
	double latitudeOfHangOut;
	double longitudeOfHangOut;
	double latitudeOfAdventureIsland;
	double longitudeOfAdventureIsland;
	double latitudeOfIndianMountaineeringFoundation;
	double longitudeOfIndianMountaineeringFoundation;
	double latitudeOfSurajKundLake;
	double longitudeOfSurajKundLake;
	double latitudeOfFerozShahKotla;
	double longitudeOfFerozShahKotla;
	double latitudeOfIndiraGandhiSportsComplex;
	double longitudeOfIndiraGandhiSportsComplex;
	double latitudeOfMajorDhyanchandStadium;
	double longitudeOfMajorDhyanchandStadium;
	double latitudeOfTalkatoraStadium;
	double longitudeOfTalkatoraStadium;
	double latitudeOfJawaharLalNehruStadium;
	double longitudeOfJawaharLalNehruStadium;
	double latitudeOfNationalMuseum;
	double longitudeOfNationalMuseum;
	double latitudeOfNationalRailMuseum;
	double longitudeOfNationalRailMuseum;
	double latitudeOfNationalZoologicalPark;
	double longitudeOfNationalZoologicalPark;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// code that displays the content in full screen mode
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		options = (ListView) findViewById(R.id.listofplaces);
		optionsAvailable = getIntent().getExtras().getStringArray("options");
		detail = getIntent().getExtras().getString("detail");
		localIntent = new Intent(Goto.this, HistoryAndAddress.class);
		mapIntent = new Intent(Goto.this, ShowMap.class);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Goto.this,
				android.R.layout.simple_list_item_1, optionsAvailable);
		options.setAdapter(adapter);

		options.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0: // for history //
					if (detail.equals("Red-Fort")) {
						localIntent.putExtra("history", historyOfRedfort);
						localIntent.putExtra("address", addressOfRedfort);
					} else if (detail.equals("Jantar-Mantar (Connaught Place)")) {
						localIntent.putExtra("history", historyOfJantarMantar);
						localIntent.putExtra("address", addressOfJantarMantar);
					} else if (detail.equals("Lal Bangla")) {
						localIntent.putExtra("history", historyOfLalBangla);
						localIntent.putExtra("address", addressOfLalBangla);
					} else if (detail.equals("Lal Gumbad")) {
						localIntent.putExtra("history", historyOfLalGumbad);
						localIntent.putExtra("address", addressOfLalGumbad);
					} else if (detail.equals("Ashoka's Pillar")) {
						localIntent.putExtra("history", historyOfAshokPillar);
						localIntent.putExtra("address", addressOfAshokPillar);
					} else if (detail.equals("Delhi Gate(India Gate)")) {
						localIntent.putExtra("history", historyOfDelhiGate);
						localIntent.putExtra("address", addressOfDelhiGate);
					} else if (detail.equals("Humayun's Tomb")) {
						localIntent.putExtra("history", historyOfHumayunTomb);
						localIntent.putExtra("address", addressOfHumayunTomb);
					} else if (detail.equals("Akshardham-Temple")) {
						localIntent.putExtra("history",
								historyOfAkshardhamTemple);
						localIntent.putExtra("address",
								addressOfAkshardhamTemple);
					} else if (detail.equals("LakshmiNarayan Temple")) {
						localIntent.putExtra("history",
								historyOfLakshmiNarayanTemple);
						localIntent.putExtra("address",
								addressOfLakshmiNarayanTemple);
					} else if (detail.equals("Cathedral Church Of Redemption")) {
						localIntent.putExtra("history",
								historyOfCathedralChurchOfRedemption);
						localIntent.putExtra("address",
								addressOfCathedralChurchOfRedemption);
					} else if (detail.equals("Gurudwara Bangla Sahib")) {
						localIntent.putExtra("history",
								historyOfGurudwaraBanglaSahib);
						localIntent.putExtra("address",
								addressOfGurudwaraBanglaSahib);
					} else if (detail.equals("ISKCON Temple")) {
						localIntent.putExtra("history", historyOfISKCONTemple);
						localIntent.putExtra("address", addressOfISKCONTemple);
					} else if (detail.equals("Jama Masjid")) {
						localIntent.putExtra("history", historyOfJamaMasjid);
						localIntent.putExtra("address", addressOfJamaMasjid);
					} else if (detail.equals("Lotus Temple")) {
						localIntent.putExtra("history", historyOfLotusTemple);
						localIntent.putExtra("address", addressOfLotusTemple);
					} else if (detail.equals("St. James Church")) {
						localIntent.putExtra("history", historyOfStJamesChurch);
						localIntent.putExtra("address", addressOfStJamesChurch);
					} else if (detail.equals("Kalka Ji Mandir")) {
						localIntent.putExtra("history", historyOfKalkaJiMandir);
						localIntent.putExtra("address", addressOfKalkaJiMandir);
					} else if (detail.equals("Nizamuddin Dargah")) {
						localIntent.putExtra("history",
								historyOfNizamuddinDargah);
						localIntent.putExtra("address",
								addressOfNizamuddinDargah);
					} else if (detail.equals("Funland")) {
						localIntent.putExtra("history", historyOfFunland);
						localIntent.putExtra("address", addressOfFunland);
					} else if (detail.equals("HangOut")) {
						localIntent.putExtra("history", historyOfHangOut);
						localIntent.putExtra("address", addressOfHangOut);
					} else if (detail.equals("Adventure-Island")) {
						localIntent.putExtra("history",
								historyOfAdventureIsland);
						localIntent.putExtra("address",
								addressOfAdventureIsland);
					} else if (detail
							.equals("Indian Mountaineering Foundation")) {
						localIntent.putExtra("history",
								historyOfIndianMountaineeringFoundation);
						localIntent.putExtra("address",
								addressOfIndianMountaineeringFoundation);
					} else if (detail.equals("SurajKund Lake")) {
						localIntent.putExtra("history", historyOfSurajKundLake);
						localIntent.putExtra("address", addressOfSurajKundLake);
					} else if (detail.equals("FerozShahKotla")) {
						localIntent
								.putExtra("history", historyOfFerozShahKotla);
						localIntent
								.putExtra("address", addressOfFerozShahKotla);
					} else if (detail.equals("Indira Gandhi Sports Complex")) {
						localIntent.putExtra("history",
								historyOfIndiraGandhiSportsComplex);
						localIntent.putExtra("address",
								addressOfIndiraGandhiSportsComplex);
					} else if (detail.equals("Major DhyanChand Stadium")) {
						localIntent.putExtra("history",
								historyOfMajorDhyanChandStadium);
						localIntent.putExtra("address",
								addressOfMajorDhyanChandStadium);
					} else if (detail.equals("Talkatora Indoor Stadium")) {
						localIntent.putExtra("history",
								historyOfTalkatoraIndoorStadium);
						localIntent.putExtra("address",
								addressOfTalkatoraIndoorStadium);
					} else if (detail.equals("JawaharLal Nehru Stadium")) {
						localIntent.putExtra("history",
								historyOfJawaharLalNehruStadium);
						localIntent.putExtra("address",
								addressOfJawaharLalNehruStadium);
					} else if (detail.equals("National Museum")) {
						localIntent
								.putExtra("history", historyOfNationalMuseum);
						localIntent
								.putExtra("address", addressOfNationalMuseum);
					} else if (detail.equals("National Rail Museum")) {
						localIntent.putExtra("history",
								historyOfNationalRailMuseum);
						localIntent.putExtra("address",
								addressOfNationalRailMuseum);
					} else if (detail.equals("National Zoological Park")) {
						localIntent.putExtra("history",
								historyOfNationalZoologicalPark);
						localIntent.putExtra("address",
								addressOfNationalZoologicalPark);
					}

					startActivity(localIntent);
					break;
				case 1: // for navigation through google maps//
					if (detail.equals("Red-Fort")) {
						mapIntent.putExtra("latitude", latitudeOfRedFort);
						mapIntent.putExtra("longitude", longitudeOfRedFort);
						mapIntent.putExtra("title", "Red-Fort");
					} else if (detail.equals("Jantar-Mantar (Connaught Place)")) {
						mapIntent.putExtra("latitude", latitudeOfJantarMantar);
						mapIntent
								.putExtra("longitude", longitudeOfJantarMantar);
						mapIntent.putExtra("title", "Jantar-Mantar");
					} else if (detail.equals("Lal Bangla")) {
						mapIntent.putExtra("latitude", latitudeOfLalBangla);
						mapIntent.putExtra("longitude", longitudeOfLalBangla);
						mapIntent.putExtra("title", "Lal-Bangla");
					} else if (detail.equals("Lal Gumbad")) {
						mapIntent.putExtra("latitude", latitudeOfLalGumbad);
						mapIntent.putExtra("longitude", longitudeOfLalGumbad);
						mapIntent.putExtra("title", "Lal-Gumbad");
					} else if (detail.equals("Ashoka's Pillar")) {
						mapIntent.putExtra("latitude", latitudeOfAshokPillar);
						mapIntent.putExtra("longitude", longitudeOfAshokPillar);
						mapIntent.putExtra("title", "Ashok-Pillar");
					} else if (detail.equals("Delhi Gate(India Gate)")) {
						mapIntent.putExtra("latitude", latitudeOfDelhiGate);
						mapIntent.putExtra("longitude", longitudeOfDelhiGate);
						mapIntent.putExtra("title", "Delhi-Gate(India-Gate)");
					} else if (detail.equals("Humayun's Tomb")) {
						mapIntent.putExtra("latitude", latitudeOfHumayunTomb);
						mapIntent.putExtra("longitude", longitudeOfHumayunTomb);
						mapIntent.putExtra("title", "Humayun's Tomb");
					} else if (detail.equals("Akshardham-Temple")) {
						mapIntent.putExtra("latitude",
								latitudeOfAkshardhamTemple);
						mapIntent.putExtra("longitude",
								longitudeOfAkshardhamTemple);
						mapIntent.putExtra("title", "Akshardham Temple");
					} else if (detail.equals("LakshmiNarayan Temple")) {
						mapIntent.putExtra("latitude",
								latitudeOfLakshmiNarayanTemple);
						mapIntent.putExtra("longitude",
								longitudeOfLakshmiNarayanTemple);
						mapIntent.putExtra("title", "LakshmiNarayan Temple");
					} else if (detail.equals("Cathedral Church Of Redemption")) {
						mapIntent.putExtra("latitude",
								latitudeOfCathedralChurchOfRedemption);
						mapIntent.putExtra("longitude",
								longitudeOfCathedralChurchOfRedemption);
						mapIntent.putExtra("title",
								"Cathedral Church Of Redemption");
					} else if (detail.equals("Gurudwara Bangla Sahib")) {
						mapIntent.putExtra("latitude",
								latitudeOfGurudwaraBanglaSahib);
						mapIntent.putExtra("longitude",
								longitudeOfGurudwaraBanglaSahib);
						mapIntent.putExtra("title", "Gurudwara Bangla Sahib");
					} else if (detail.equals("ISKCON Temple")) {
						mapIntent.putExtra("latitude", latitudeOfISKCONTemple);
						mapIntent
								.putExtra("longitude", longitudeOfISKCONTemple);
						mapIntent.putExtra("title", "ISKCON Temple");
					} else if (detail.equals("Jama Masjid")) {
						mapIntent.putExtra("latitude", latitudeOfJamaMasjid);
						mapIntent.putExtra("longitude", longitudeOfJamaMasjid);
						mapIntent.putExtra("title", "Jama Masjid");
					} else if (detail.equals("Lotus Temple")) {
						mapIntent.putExtra("latitude", latitudeOfLotusTemple);
						mapIntent.putExtra("longitude", longitudeOfLotusTemple);
						mapIntent.putExtra("title", "Lotus Temple");
					} else if (detail.equals("St. James Church")) {
						mapIntent.putExtra("latitude", latitudeOfStJamesChurch);
						mapIntent.putExtra("longitude",
								longitudeOfStJamesChurch);
						mapIntent.putExtra("title", "St. James Church");
					} else if (detail.equals("Kalka Ji Mandir")) {
						mapIntent.putExtra("latitude", latitudeOfKalkaJiMandir);
						mapIntent.putExtra("longitude",
								longitudeOfKalkaJiMandir);
						mapIntent.putExtra("title", "Kalka Ji Mandir");
					} else if (detail.equals("Nizamuddin Dargah")) {
						mapIntent.putExtra("latitude",
								latitudeOfNizamuddinDargah);
						mapIntent.putExtra("longitude",
								longitudeOfNizamuddinDargah);
						mapIntent.putExtra("title", "Nizamuddin Dargah");
					} else if (detail.equals("Funland")) {
						mapIntent.putExtra("latitude", latitudeOfFunland);
						mapIntent.putExtra("longitude", longitudeOfFunland);
						mapIntent.putExtra("title", "FunLand");
					} else if (detail.equals("HangOut")) {
						mapIntent.putExtra("latitude", latitudeOfHangOut);
						mapIntent.putExtra("longitude", longitudeOfHangOut);
						mapIntent.putExtra("title", "HangOut");
					} else if (detail.equals("Adventure-Island")) {
						mapIntent.putExtra("latitude",
								latitudeOfAdventureIsland);
						mapIntent.putExtra("longitude",
								longitudeOfAdventureIsland);
						mapIntent.putExtra("title", "Adventure Island");
					} else if (detail
							.equals("Indian Mountaineering Foundation")) {
						mapIntent.putExtra("latitude",
								latitudeOfIndianMountaineeringFoundation);
						mapIntent.putExtra("longitude",
								longitudeOfIndianMountaineeringFoundation);
						mapIntent.putExtra("title",
								"Indian Mountaineering Foundation");
					} else if (detail.equals("SurajKund Lake")) {
						mapIntent.putExtra("latitude", latitudeOfSurajKundLake);
						mapIntent.putExtra("longitude",
								longitudeOfSurajKundLake);
						mapIntent.putExtra("title", "SurajKund Lake");
					} else if (detail.equals("FerozShahKotla")) {
						mapIntent
								.putExtra("latitude", latitudeOfFerozShahKotla);
						mapIntent.putExtra("longitude",
								longitudeOfFerozShahKotla);
						mapIntent.putExtra("title", "FerozShahKotla");
					} else if (detail.equals("Indira Gandhi Sports Complex")) {
						mapIntent.putExtra("latitude",
								latitudeOfIndiraGandhiSportsComplex);
						mapIntent.putExtra("longitude",
								longitudeOfIndiraGandhiSportsComplex);
						mapIntent.putExtra("title",
								"Indira Gandhi Sports Complex");
					} else if (detail.equals("Major DhyanChand Stadium")) {
						mapIntent.putExtra("latitude",
								latitudeOfMajorDhyanchandStadium);
						mapIntent.putExtra("longitude",
								longitudeOfMajorDhyanchandStadium);
						mapIntent.putExtra("title", "Major DhyanChand Stadium");
					} else if (detail.equals("Talkatora Indoor Stadium")) {
						mapIntent.putExtra("latitude",
								latitudeOfTalkatoraStadium);
						mapIntent.putExtra("longitude",
								longitudeOfTalkatoraStadium);
						mapIntent.putExtra("title", "Talkatora Stadium");
					} else if (detail.equals("JawaharLal Nehru Stadium")) {
						mapIntent.putExtra("latitude",
								latitudeOfJawaharLalNehruStadium);
						mapIntent.putExtra("longitude",
								longitudeOfJawaharLalNehruStadium);
						mapIntent.putExtra("title", "JawaharLal Nehru Stadium");
					} else if (detail.equals("National Museum")) {
						mapIntent
								.putExtra("latitude", latitudeOfNationalMuseum);
						mapIntent.putExtra("longitude",
								longitudeOfNationalMuseum);
						mapIntent.putExtra("title", "National Museum");
					} else if (detail.equals("National Rail Museum")) {
						mapIntent.putExtra("latitude",
								latitudeOfNationalRailMuseum);
						mapIntent.putExtra("longitude",
								longitudeOfNationalRailMuseum);
						mapIntent.putExtra("title", "National Rail Museum");
					} else if (detail.equals("National Zoological Park")) {
						mapIntent.putExtra("latitude",
								latitudeOfNationalZoologicalPark);
						mapIntent.putExtra("longitude",
								longitudeOfNationalZoologicalPark);
						mapIntent.putExtra("title", "National Zoological Park");
					}

					startActivity(mapIntent);
					// Toast.makeText(getApplicationContext(), "pressed",
					// Toast.LENGTH_LONG).show(); //
					break;
				case 2: // for the nearest metro station //
					if (detail.equals("Red-Fort")) {
						localIntent.putExtra("history", historyOfRedfort);
						localIntent.putExtra("address", addressOfRedfort);
					} else if (detail.equals("Jantar-Mantar (Connaught Place)")) {

					} else if (detail.equals("Lal Bangla")) {

					} else if (detail.equals("Lal Gumbad")) {

					} else if (detail.equals("Ashoka's Pillar")) {

					} else if (detail.equals("Delhi Gate(India Gate)")) {

					} else if (detail.equals("Humayun's Tomb")) {

					} else if (detail.equals("Akshardham-Temple")) {

					} else if (detail.equals("LakshmiNarayan Temple")) {

					} else if (detail.equals("Cathedral Church Of Redemption")) {

					} else if (detail.equals("Gurudwara Bangla Sahib")) {

					} else if (detail.equals("ISKCON Temple")) {

					} else if (detail.equals("Jama Masjid")) {

					} else if (detail.equals("Lotus Temple")) {

					} else if (detail.equals("St. James Church")) {

					} else if (detail.equals("Kalka Ji Mandir")) {

					} else if (detail.equals("Nizamuddin Dargah")) {

					} else if (detail.equals("Funland")) {

					} else if (detail.equals("HangOut")) {

					} else if (detail.equals("Adventure-Island")) {

					} else if (detail
							.equals("Indian Mountaineering Foundation")) {

					} else if (detail.equals("SurajKund Lake")) {

					} else if (detail.equals("FerozShahKotla")) {

					} else if (detail.equals("Indira Gandhi Sports Complex")) {

					} else if (detail.equals("Major DhyanChand Stadium")) {

					} else if (detail.equals("Talkatora Indoor Stadium")) {

					} else if (detail.equals("JawaharLal Nehru Stadium")) {

					} else if (detail.equals("National Museum")) {

					} else if (detail.equals("National Rail Museum")) {

					} else if (detail.equals("National Zoological Park")) {

					}

					startActivity(localIntent);

					break;
				}

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
			AlertDialog.Builder alert = new AlertDialog.Builder(Goto.this);
			alert.setMessage("Are you sure");
			alert.setCancelable(true); // this makes the dialog box to close
										// when the back button is pressed //
			alert.setTitle("Exit");
			alert.create();
			alert.setPositiveButton("ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Goto.this.finish();
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
			d = new Dialog(Goto.this);
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
					Toast.makeText(Goto.this, str, Toast.LENGTH_SHORT).show();
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

}
