package com.eonsahead.schauspieler;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String AUTHORITY = "com.eonsahead.schauspieler";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";
    public static final String PATH_REVIEWS = "reviews";
    public static final String PATH_TRAILERS = "trailers";


    public static final Uri MOVIES_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
    public static final Uri REVIEWS_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEWS).build();
    public static final Uri TRAILERS_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRAILERS).build();

    private MoviesContract() {
    } // MoviesContract()

    public static final class Details implements BaseColumns {
        public static final String TABLE_NAME = "details";
        public static final String COLUMN_NAME_MOVIE_ID = "movieId";
        public static final String COLUMN_NAME_ORIGINAL_TITLE = "originalTitle";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "voteAverage";
        public static final String COLUMN_NAME_POPULARITY = "popularity";
        public static final String COLUMN_NAME_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_NAME_POSTER_PATH = "posterPath";
        public static final String COLUMN_NAME_IS_FAVORITE = "isFavorite";
        public static final String COLUMN_NAME_IS_POPULAR = "isPopular";
        public static final String COLUMN_NAME_IS_RATED = "isRated";
    } // Details

    public static final class Reviews implements BaseColumns {
        public static final String TABLE_NAME = "reviews";
        public static final String COLUMN_NAME_MOVIE_ID = "movieId";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_CONTENT = "content";
    } // Reviews

    public static final class Trailers implements BaseColumns {
        public static final String TABLE_NAME = "trailers";
        public static final String COLUMN_NAME_MOVIE_ID = "movieId";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_KEY = "key";
        public static final String COLUMN_NAME_TYPE = "type";
    } // Trailers
} // MoviesContract
