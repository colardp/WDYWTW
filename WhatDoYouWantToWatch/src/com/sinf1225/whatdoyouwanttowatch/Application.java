package com.sinf1225.whatdoyouwanttowatch;


import java.util.Hashtable;


import android.content.Context;

/**
 * Application
 * Sort of super class containing global data and user interaction
 * */
public class Application {
	private static User currentUser;
	
	/**
	 * Login the user onto the application
	 * @param name: name of the user
	 * @param password: password of the user
	 * @return true if the name-password combination is correct
	 */
	public static boolean login(Context context, String name, String password){
		Database db = new Database(context);
		boolean canLogin = db.login(
				name,
				password
				);
		if(canLogin){
			currentUser = new User( context, name );
		}
		return canLogin;
	}
	
	/**
	 * Logout the user from the application.
	 * Always succeeds.
	 */
	public static void logout(){
		currentUser = null;
		clearMovies();  // empty the database
	}
	
	/**
	 * Checks if a user is currently logged in
	 * @return true if a user is logged in
	 */
	public static boolean isUserLoggedIn(){
		return currentUser != null;
	}
	
	/**
	 * Returns the current user of the application
	 * @return the current user, or null if no current user
	 */
	public static User getUser(){
		return currentUser;
	}
	
	
	// dictionary containing the pairs ID-movie object
	private static Hashtable<String, Movie> encounteredMovies = new Hashtable<String, Movie>();
	
	/**
	 * Returns a movie from given imdbID
	 * This makes sure Movie objects already encountered (and filled) are not lost! 
	 * @param imdbID: a String, holding the unique imdb id of the movie
	 * @return a Movie (that may or may not be filled) object
	 */
	public static Movie getMovie( String imdbID ){
		Movie res = encounteredMovies.get(imdbID);
		if(res == null){
			// build a new movie object
			res = new Movie( imdbID );
			encounteredMovies.put(imdbID, res);
		}
		return res;
	}
	
	// free the memory taken from all movies (usually on logout)
	public static void clearMovies(){
		encounteredMovies.clear();
	}
}
