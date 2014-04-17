package com.sinf1225.whatdoyouwanttowatch;
/**
 * Class holding data for a user
 */
public class User {
	private String name;
	private int age;
	public User(String username){
		name = username;
		Database db = new Database(null);
		age = db.getPlayerAge(username);
	}
	
	public int getAge(){
		return age;
	}
	public void setAge(int newAge){
		age = newAge;
		Database db = new Database(null);
		db.setPlayerAge(name, age);
	}
}
