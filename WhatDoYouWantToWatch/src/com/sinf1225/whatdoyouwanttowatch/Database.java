package com.sinf1225.whatdoyouwanttowatch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;


public class Database extends SQLiteOpenHelper {

	// constants for database handling
	public static final String DATABASE_NAME = "WDYWTW.db";
	public static final int DATABASE_VERSION = 1;
	
	// users table
	public static final String TABLE_USERS = "users";
	public static final String USERS_NAME  = "name";
	public static final String USERS_AGE   = "age";
	public static final String USERS_PSWD  = "password";
	public static final String[] USERS_COLUMNS = new String[] {
		_ID,
		USERS_NAME, 
		USERS_AGE,
		USERS_PSWD
		};
	
	
	
	// constructor
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// add a table and fake users
		db.execSQL("CREATE TABLE "+TABLE_USERS+ " (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				USERS_NAME + " TEXT NOT NULL," +
				USERS_AGE  + " INTEGER," +
				USERS_PSWD + " TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// nothing to do: this contains sensitive data (not changed by updates)
	}
	
	
	// methods used throughout the code
	// TODO: update to more secure password handling
	public boolean login(String userName, String password){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query( true, 
				 TABLE_USERS, 
				 new String[] {USERS_NAME},
				 USERS_NAME+" = \""+userName+"\" AND "+USERS_PSWD+" = \""+password+"\";",
				 null, //new String[] {userName, password},
				 null, null, null, null
				 );
		boolean okLogin = cursor.getCount()==1;
		db.close();
		return okLogin;
	}
	
	public boolean newUser(String userName, String password, int age){
		// check if name already exists
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query( true, 
				 TABLE_USERS, 
				 new String[] {USERS_NAME},
				 USERS_NAME+" = \""+userName+"\";",
				 null, //new String[] {userName},
				 null, null, null, null
				 );
		if(cursor.getCount() > 0 ){
			db.close();
			return false; // user already exists
		}
		db.close();
		// user does not exist
		db = this.getWritableDatabase();
		db.execSQL("INSERT INTO "+TABLE_USERS+"("+USERS_NAME+", "+
				USERS_PSWD+", "+USERS_AGE+") VALUES (\""
				+userName+"\", \""+password+
				"\", "+Integer.toString(age)+");");
		db.close();
		return true;
	}
	
	public int getUserAge(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query( true,
				TABLE_USERS, 
				new String[] {USERS_AGE},
				USERS_NAME+" = \""+name+"\"",
				null, null, null, null, null);
		cursor.moveToFirst();
		if(cursor.getCount() < 1){
			return 0;
		}
		int age = cursor.getInt(0);
		db.close();
		return age;
	}
	

	public void setPlayerAge(String name, int age){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("UPDATE "+TABLE_USERS+" SET "+
		USERS_AGE+" = "+Integer.toString(age) + 
		" WHERE " + USERS_NAME + " = \""+name+"\";");
		db.close();
	}

}
