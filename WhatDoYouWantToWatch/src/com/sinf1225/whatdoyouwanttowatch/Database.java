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

	// movies table
	public static final String TABLE_MOVIES = "movies";
	public static final String MOVIES_ID = "imdbID";
	public static final String MOVIES_NAME = "name";
	public static final String MOVIES_DIRECTOR = "director";
	public static final String MOVIES_DURATION = "duration";
	public static final String MOVIES_YEAR = "year";
	public static final String MOVIES_RATING = "rating";
	public static final String MOVIES_DESCRIPTION = "description";
	public static final String MOVIES_AGERESTR = "agerestriction";
	public static final String[] MOVIES_COLUMNS = new String[] {
		_ID,
		MOVIES_ID,
		MOVIES_NAME,
		MOVIES_DIRECTOR,
		MOVIES_DURATION,
		MOVIES_YEAR,
		MOVIES_RATING,
		MOVIES_DESCRIPTION,
		MOVIES_AGERESTR
	};


	// coming soon table
	public static final String TABLE_COMINGSOON = "comingsoon";
	public static final String COMINGSOON_MOVIE = MOVIES_ID;
	public static final String[] COMINGSOON_COLUMNS = new String[] {COMINGSOON_MOVIE};

	// playing now table
	public static final String TABLE_PLAYINGNOW = "playingnow";
	public static final String PLAYINGNOW_MOVIE = MOVIES_ID;
	public static final String[] PLAYINGNOW_COLUMNS = new String[] {PLAYINGNOW_MOVIE};

	// most watched table
	public static final String TABLE_MOSTWATCHED = "mostwatched";
	public static final String MOSTWATCHED_MOVIE = MOVIES_ID;
	public static final String[] MOSTWATCHED_COLUMNS = new String[] {MOSTWATCHED_MOVIE};

	// genre table
	public static final String TABLE_GENRE = "genre";
	public static final String GENRE_MOVIE = MOVIES_ID;
	public static final String GENRE_GENRE = "genre";
	public static final String[] GENRE_COLUMNS = new String[] {GENRE_MOVIE, GENRE_GENRE};

	// related movies table
	public static final String TABLE_RELATED = "relatedmovies";
	public static final String RELATED_MOVIE1 = MOVIES_ID+"1";
	public static final String RELATED_MOVIE2 = MOVIES_ID+"2";
	public static final String[] RELATED_COLUMNS = new String[] {RELATED_MOVIE1, RELATED_MOVIE2};

	// awards table
	public static final String TABLE_AWARDS = "awards";
	public static final String AWARDS_MOVIE = MOVIES_ID;
	public static final String AWARDS_NAME  = "name";
	public static final String AWARDS_YEAR  = "year";
	public static final String[] AWARDS_COLUMNS = new String[]{ AWARDS_MOVIE, AWARDS_NAME, AWARDS_YEAR };

	// cast table
	public static final String TABLE_CAST = "cast";
	public static final String CAST_MOVIE = MOVIES_ID;
	public static final String CAST_ACTOR = "actor";
	public static final String CAST_CHARACTER = "character";
	public static final String[] CAST_TABLE = new String[] { CAST_MOVIE, CAST_ACTOR, CAST_CHARACTER };

	// interest table (!)
	public static final String TABLE_INTEREST = "interest";
	public static final String INTEREST_MOVIE = MOVIES_ID;
	public static final String INTEREST_USER  = USERS_NAME;
	public static final String INTEREST_INTEREST = "interest";
	public static final String[] INTEREST_COLUMNS = new String[] {
		INTEREST_MOVIE,
		INTEREST_USER,
		INTEREST_INTEREST
	};


	// constructor: wrap around the default constructor
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	// creation of the database: create the tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		// users table
		db.execSQL("CREATE TABLE "+TABLE_USERS+ " (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				USERS_NAME + " TEXT NOT NULL UNIQUE," +
				USERS_AGE  + " INTEGER," +
				USERS_PSWD + " TEXT NOT NULL);");
		// movies table
		db.execSQL("CREATE TABLE "+TABLE_MOVIES+" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				MOVIES_ID + " TEXT NOT NULL UNIQUE," +
				MOVIES_NAME + " TEXT NOT NULL," +
				MOVIES_DIRECTOR + " TEXT NOT NULL default 'Unknown', " +
				MOVIES_DURATION + " INTEGER NOT NULL," + // minutes
				MOVIES_YEAR + " INTEGER NOT NULL," +
				MOVIES_RATING + " FLOAT NOT NULL," +
				MOVIES_DESCRIPTION + " TEXT default 'No description available'," +
				MOVIES_AGERESTR  + "INTEGER default -1);"
				);
		// coming soon
		db.execSQL("CREATE TABLE "+TABLE_COMINGSOON+" ("+ _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				COMINGSOON_MOVIE + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+COMINGSOON_MOVIE+") REFERENCES "+
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// playing now
		db.execSQL("CREATE TABLE "+TABLE_PLAYINGNOW+" ("+ _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				PLAYINGNOW_MOVIE + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+PLAYINGNOW_MOVIE+") REFERENCES "+
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// most watched 
		db.execSQL("CREATE TABLE "+TABLE_MOSTWATCHED+" ("+ _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				MOSTWATCHED_MOVIE + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+MOSTWATCHED_MOVIE+") REFERENCES "+
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// genre table
		db.execSQL("CREATE TABLE "+ TABLE_GENRE +" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				GENRE_MOVIE + " TEXT NOT NULL, " +
				GENRE_GENRE + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+ GENRE_MOVIE +") REFERENCES " +
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// related table
		db.execSQL("CREATE TABLE "+ TABLE_RELATED +" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				RELATED_MOVIE1 + " TEXT NOT NULL, " +
				RELATED_MOVIE2 + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+ RELATED_MOVIE1 +") REFERENCES " +
				"TABLE_MOVIES("+MOVIES_ID+"), " +
				"FOREIGN KEY ("+ RELATED_MOVIE2 +") REFERENCES " +
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// awards table
		db.execSQL("CREATE TABLE "+ TABLE_AWARDS +" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				AWARDS_MOVIE + " TEXT NOT NULL, " +
				AWARDS_NAME  + " TEXT NOT NULL, " +
				AWARDS_YEAR  + " INTEGER NOT NULL, " +
				"FOREIGN KEY ("+ AWARDS_MOVIE +") REFERENCES " +
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// cast table
		db.execSQL("CREATE TABLE "+ TABLE_CAST +" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				CAST_MOVIE + " TEXT NOT NULL, " +
				CAST_ACTOR + " TEXT NOT NULL, " +
				CAST_CHARACTER + " DEFAULT 'Unknown', " +
				"FOREIGN KEY ("+ CAST_MOVIE +") REFERENCES " +
				TABLE_MOVIES+"("+MOVIES_ID+") );"
				);
		// interest table
		db.execSQL("CREATE TABLE "+ TABLE_INTEREST +" (" + _ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT, " +
				INTEREST_MOVIE + " TEXT NOT NULL, " +
				INTEREST_USER  + " TEXT NOT NULL, " +
				INTEREST_INTEREST + " TEXT NOT NULL, " +
				"FOREIGN KEY ("+ INTEREST_MOVIE +") REFERENCES " +
				TABLE_MOVIES+"("+MOVIES_ID+"), " +
				"FOREIGN KEY ("+ INTEREST_USER + ") REFERENCES " +
				TABLE_USERS +"("+ USERS_NAME+ ") );"
				);
		// TODO: add some data into the database!
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
