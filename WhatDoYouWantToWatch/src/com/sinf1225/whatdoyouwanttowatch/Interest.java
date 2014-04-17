package com.sinf1225.whatdoyouwanttowatch;

// enum containing the different values for interest
public enum Interest {
	NOINTEREST, // this is the default: the user never specified anything for this movie
	NOTWATCHED, // the user has acknowledged never seeing this movie, but it not very interested by it
	WILLNOTWATCHIT, // the user has never seen this movie, and doesn't want to (i. e. Twilight)
	WANTTOWATCHIT, // the user wants to watch this movie, and hasn't yet
	// beyond this point, the user has watched the movie
	HATED,
	NOTLIKED,
	INDIFFERENT,
	LIKED,
	LOVED,
	FAVORITE
}
