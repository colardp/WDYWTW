package com.sinf1225.whatdoyouwanttowatch;

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
	}
	
	/**
	 * Checks if a user is currently logged in
	 * @return true if a user is logged in
	 */
	public static boolean isUserLoggedIn(){
		return currentUser != null;
	}
	
	// TODO: add "already encountered movies" feature to limit need to access database
}
