package com.eonsahead.schauspieler;

import android.provider.BaseColumns;

public class MoviesContract {
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
