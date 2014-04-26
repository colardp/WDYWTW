package com.sinf1225.whatdoyouwanttowatch;


import android.content.Context;

public class Movie {

	// data
	private String imdbID;
	public String title;
	public String director;
	public int year;
	public int duration; // minutes
	public float rating; // 0 <= rating <= 10
	public String description;
	public int ageRestrictions;
	
	public boolean isComingSoon;
	public boolean isMostWatched;
	public boolean isPlayingNow;
	
	public String[] RelatedMoviesID;
	public String Awards[];
	public String Cast[];

	public Genre MovieGenre[];
	
	public Interest InterestForUser[];
	
	private MovieQuickData quickData;
	private boolean filled;

	
	// constructor: takes as argument the ID of the movie
	public Movie( String imdbID ) {
		this.imdbID = imdbID;
	}
	
	// getter for the IMDB ID: no setter! (of course not!)
	public String getID(){
		return imdbID;
	}
	
	// fill all the movie's data
	// This gets "a lot of" information from the database
	// so it is to be used only if the movie needs filling
	// the context argument is used to create a database
	// In fact, it is a good practice to use movie.fillData(...) every time you
	//  access a movie in the program
	public void fillData( Context context ){
		if(filled){
			return;
		}
		Database db = new Database( context );
		this.fillData( db );
		db.close();
	}
	
	// fill all the movie's data using a pre-instantiated database
	public void fillData( Database db ){
		if(filled){
			return;
		}

		db.fillMovie(this); // what did you expect?
		
		filled = true;
		// also, add quick data...
		if(quickData == null){
			quickData = new MovieQuickData(title, director, year);
		}
	}

	// get some quick data from the Movie, for display for example
	public MovieQuickData getQuickData( Context context ){
		if(this.quickData != null){
			return this.quickData;
		}
		Database db = new Database( context );
		MovieQuickData res = this.getQuickData(db);
		db.close();
		return res;
	}
	
	// get some quick data from the movie, using an existing database
	public MovieQuickData getQuickData( Database db ){
		if(this.quickData != null){
			return this.quickData;
		}
		quickData = db.getMovieQuickData(this);
		return quickData;
	}
	
	

}
