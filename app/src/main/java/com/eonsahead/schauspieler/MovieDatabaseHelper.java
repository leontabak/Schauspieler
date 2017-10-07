package com.eonsahead.schauspieler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MovieDatabaseHelper";
    private static final String DB_NAME = "movies";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_DETAILS_TABLE = "" +
            "CREATE TABLE " +
            MoviesContract.Details.TABLE_NAME +
            " (" +
            MoviesContract.Details._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoviesContract.Details.COLUMN_NAME_MOVIE_ID +
            " INTEGER UNIQUE, " +
            MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE +
            " TEXT, " +
            MoviesContract.Details.COLUMN_NAME_OVERVIEW +
            " TEXT, " +
            MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE +
            " FLOAT, " +
            MoviesContract.Details.COLUMN_NAME_POPULARITY +
            " FLOAT, " +
            MoviesContract.Details.COLUMN_NAME_RELEASE_DATE +
            " TEXT, " +
            MoviesContract.Details.COLUMN_NAME_POSTER_PATH +
            " TEXT, " +
            MoviesContract.Details.COLUMN_NAME_IS_FAVORITE +
            " INTEGER, " +
            MoviesContract.Details.COLUMN_NAME_IS_POPULAR +
            " INTEGER, " +
            MoviesContract.Details.COLUMN_NAME_IS_RATED +
            " INTEGER)";

    private static final String SQL_CREATE_REVIEWS_TABLE = "" +
            "CREATE TABLE " +
            MoviesContract.Reviews.TABLE_NAME +
            " (" +
            MoviesContract.Reviews._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoviesContract.Reviews.COLUMN_NAME_MOVIE_ID +
            " INTEGER, " +
            MoviesContract.Reviews.COLUMN_NAME_AUTHOR +
            " TEXT, " +
            MoviesContract.Reviews.COLUMN_NAME_CONTENT +
            " TEXT)";

    private static final String SQL_CREATE_TRAILERS_TABLE = "" +
            "CREATE TABLE " +
            MoviesContract.Trailers.TABLE_NAME +
            " (" +
            MoviesContract.Trailers._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoviesContract.Trailers.COLUMN_NAME_MOVIE_ID +
            " INTEGER, " +
            MoviesContract.Trailers.COLUMN_NAME_NAME +
            " TEXT, " +
            MoviesContract.Trailers.COLUMN_NAME_KEY +
            " TEXT, " +
            MoviesContract.Trailers.COLUMN_NAME_TYPE +
            " TEXT)";

    private static final String SQL_DROP_DETAILS_TABLE =
            "DROP TABLE IF EXISTS " +
                    MoviesContract.Details.TABLE_NAME;
    private static final String SQL_DROP_REVIEWS_TABLE =
            "DROP TABLE IF EXISTS " +
                    MoviesContract.Reviews.TABLE_NAME;
    private static final String SQL_DROP_TRAILERS_TABLE =
            "DROP TABLE IF EXISTS " +
                    MoviesContract.Trailers.TABLE_NAME;

    public MovieDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    } // MovieDatabaseHelper( Context )

    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "MovieDatabaseHelper (1)");

        // create table for movie details
        db.execSQL(SQL_DROP_DETAILS_TABLE);
        db.execSQL(SQL_CREATE_DETAILS_TABLE);

        // create table for movie reviews
        db.execSQL(SQL_DROP_REVIEWS_TABLE);
        db.execSQL(SQL_CREATE_REVIEWS_TABLE);

        // create table for movie trailers
        db.execSQL(SQL_DROP_TRAILERS_TABLE);
        db.execSQL(SQL_CREATE_TRAILERS_TABLE);
    } // onCreate( SQLiteDatabase )

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_DETAILS_TABLE);
        db.execSQL(SQL_DROP_REVIEWS_TABLE);
        db.execSQL(SQL_DROP_TRAILERS_TABLE);
        onCreate(db);
    } // onUpgrade( SQLiteDatabase, int, int )

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    } // on Downgrade( SQLiteDatabase, int, int )

    public void addMovie(SQLiteDatabase db, MovieDetails movieDetails) {
        ContentValues details = new ContentValues();
        details.put(MoviesContract.Details.COLUMN_NAME_MOVIE_ID, movieDetails.getId());
        details.put(MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE, movieDetails.getOriginalTitle());
        details.put(MoviesContract.Details.COLUMN_NAME_OVERVIEW, movieDetails.getOverview());
        details.put(MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE, movieDetails.getVoteAverage());
        details.put(MoviesContract.Details.COLUMN_NAME_POPULARITY, movieDetails.getPopularity());
        details.put(MoviesContract.Details.COLUMN_NAME_RELEASE_DATE, movieDetails.getReleaseDate());
        details.put(MoviesContract.Details.COLUMN_NAME_POSTER_PATH, movieDetails.getPosterPath());

        if (movieDetails.getIsFavorite()) {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_FAVORITE, 1);
        } // if
        else {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_FAVORITE, 0);
        } // else

        if (movieDetails.getIsPopular()) {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_POPULAR, 1);
        } // if
        else {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_POPULAR, 0);
        } // else

        if (movieDetails.getIsHighlyRated()) {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_RATED, 1);
        } // if
        else {
            details.put(MoviesContract.Details.COLUMN_NAME_IS_RATED, 0);
        } // else

        db.insert(MoviesContract.Details.TABLE_NAME, null, details);

        int movieId = movieDetails.getId();

        for (Review review : movieDetails.getReviews()) {
            details = new ContentValues();
            details.put(MoviesContract.Reviews.COLUMN_NAME_MOVIE_ID, movieId);
            details.put(MoviesContract.Reviews.COLUMN_NAME_AUTHOR, review.getAuthor());
            details.put(MoviesContract.Reviews.COLUMN_NAME_CONTENT, review.getContent());
            db.insert(MoviesContract.Reviews.TABLE_NAME, null, details);
        } // for

        for (Trailer trailer : movieDetails.getTrailers()) {
            details = new ContentValues();
            details.put(MoviesContract.Trailers.COLUMN_NAME_MOVIE_ID, movieId);
            details.put(MoviesContract.Trailers.COLUMN_NAME_NAME, trailer.getName());
            details.put(MoviesContract.Trailers.COLUMN_NAME_KEY, trailer.getKey());
            details.put(MoviesContract.Trailers.COLUMN_NAME_TYPE, trailer.getType());
            db.insert(MoviesContract.Trailers.TABLE_NAME, null, details);
        } // for
    } // addMovie( MovieDetails )

} // MovieDatabaseHelper
