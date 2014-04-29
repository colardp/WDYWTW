package com.sinf1225.whatdoyouwanttowatch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    // pour tester
	    Movie movie = Application.getMovie("movie1");
	    movie.fillData(this);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		}
		return true;
	}
	
	private void openSearch(){
		// Intent i = new Intent(this, searchActivity.class)
		// startActivity( i );
	}
	private void openSettings(){
		// Intent i = new Intent(this, settingsActivity.class)
		// startActivity( i );
	}

}
