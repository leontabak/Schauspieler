package com.eonsahead.schauspieler;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ContentProviderWrapper {
    private static final String TAG = "ContentProviderWrapper";

    private AppCompatActivity activity;

    public ContentProviderWrapper(AppCompatActivity activity) {
        this.activity = activity;
    } // ContentProviderWrapper( AppCompatActivity )

    public Cursor getAllMovies() {
        Log.d(TAG, "-(0)");
        Uri uri = MoviesContract.MOVIES_URI;
        String[] projection = {
                MoviesContract.Details.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE,
                MoviesContract.Details.COLUMN_NAME_OVERVIEW,
                MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE,
                MoviesContract.Details.COLUMN_NAME_POPULARITY,
                MoviesContract.Details.COLUMN_NAME_RELEASE_DATE,
                MoviesContract.Details.COLUMN_NAME_POSTER_PATH,
                MoviesContract.Details.COLUMN_NAME_IS_FAVORITE,
                MoviesContract.Details.COLUMN_NAME_IS_POPULAR,
                MoviesContract.Details.COLUMN_NAME_IS_RATED
        };
        String selection = null;
        String[] selectionArguments = null;
        Log.d(TAG, "-(1)");
        Log.d(TAG, uri.toString());
        Cursor result = activity.getContentResolver().query(uri, null, null,
                null, null);
        Log.d(TAG, "-(2)");
        return result;
    } // getAllMovies()

    public Cursor getReviews(int id) {
        Uri uri = MoviesContract.REVIEWS_URI;
        String[] projection = {
                MoviesContract.Reviews.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Reviews.COLUMN_NAME_AUTHOR,
                MoviesContract.Reviews.COLUMN_NAME_CONTENT
        };
        String selection = MoviesContract.Details.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArguments = {Integer.toString(id)};
        return activity.getContentResolver().query(uri, projection, selection,
                selectionArguments, null);
    } // getReviews( int )

    public Cursor getTrailers(int id) {
        Uri uri = MoviesContract.TRAILERS_URI;
        String[] projection = {
                MoviesContract.Trailers.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Trailers.COLUMN_NAME_TYPE,
                MoviesContract.Trailers.COLUMN_NAME_NAME,
                MoviesContract.Trailers.COLUMN_NAME_KEY
        };
        String selection = MoviesContract.Details.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArguments = {Integer.toString(id)};
        return activity.getContentResolver().query(uri, projection, selection,
                selectionArguments, null);
    } // getTrailers( int )

    public Cursor getFavorites() {
        Uri uri = MoviesContract.MOVIES_URI;
        String[] projection = {
                MoviesContract.Details.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE,
                MoviesContract.Details.COLUMN_NAME_OVERVIEW,
                MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE,
                MoviesContract.Details.COLUMN_NAME_POPULARITY,
                MoviesContract.Details.COLUMN_NAME_RELEASE_DATE,
                MoviesContract.Details.COLUMN_NAME_POSTER_PATH,
                MoviesContract.Details.COLUMN_NAME_IS_FAVORITE,
                MoviesContract.Details.COLUMN_NAME_IS_POPULAR,
                MoviesContract.Details.COLUMN_NAME_IS_RATED
        };
        String selection = MoviesContract.Details.COLUMN_NAME_IS_FAVORITE + " = ?";
        String[] selectionArguments = {"1"};
        return activity.getContentResolver().query(uri, projection, selection,
                selectionArguments, null);
    } // getFavorites()

    public Cursor getPopular() {
        Uri uri = MoviesContract.MOVIES_URI;
        String[] projection = {
                MoviesContract.Details.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE,
                MoviesContract.Details.COLUMN_NAME_OVERVIEW,
                MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE,
                MoviesContract.Details.COLUMN_NAME_POPULARITY,
                MoviesContract.Details.COLUMN_NAME_RELEASE_DATE,
                MoviesContract.Details.COLUMN_NAME_POSTER_PATH,
                MoviesContract.Details.COLUMN_NAME_IS_FAVORITE,
                MoviesContract.Details.COLUMN_NAME_IS_POPULAR,
                MoviesContract.Details.COLUMN_NAME_IS_RATED
        };
        String selection = MoviesContract.Details.COLUMN_NAME_IS_POPULAR + " = ?";
        String[] selectionArguments = {"1"};
        return activity.getContentResolver().query(uri, projection, selection,
                selectionArguments, null);
    } // getPopular()

    public Cursor getHighlyRated() {
        Uri uri = MoviesContract.MOVIES_URI;
        String[] projection = {
                MoviesContract.Details.COLUMN_NAME_MOVIE_ID,
                MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE,
                MoviesContract.Details.COLUMN_NAME_OVERVIEW,
                MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE,
                MoviesContract.Details.COLUMN_NAME_POPULARITY,
                MoviesContract.Details.COLUMN_NAME_RELEASE_DATE,
                MoviesContract.Details.COLUMN_NAME_POSTER_PATH,
                MoviesContract.Details.COLUMN_NAME_IS_FAVORITE,
                MoviesContract.Details.COLUMN_NAME_IS_POPULAR,
                MoviesContract.Details.COLUMN_NAME_IS_RATED
        };
        String selection = MoviesContract.Details.COLUMN_NAME_IS_RATED + " = ?";
        String[] selectionArguments = {"1"};
        return activity.getContentResolver().query(uri, projection, selection,
                selectionArguments, null);
    } // getHighlyRated()

    public void addToFavorites(int id) {
        Uri uri = MoviesContract.MOVIES_URI;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.Details.COLUMN_NAME_IS_FAVORITE, 1);
        String selection = MoviesContract.Details.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArguments = {Integer.toString(id)};
        activity.getContentResolver().update(uri, contentValues, selection, selectionArguments);
    } // addToFavorites( int )

    public void removeFromFavorites(int id) {
        Uri uri = MoviesContract.MOVIES_URI;
        ContentValues contentValues = new ContentValues();
        contentValues.put(MoviesContract.Details.COLUMN_NAME_IS_FAVORITE, 0);
        String selection = MoviesContract.Details.COLUMN_NAME_MOVIE_ID + " = ?";
        String[] selectionArguments = {Integer.toString(id)};
        activity.getContentResolver().update(uri, contentValues, selection, selectionArguments);
    } // removeFromFavorites( int )
} // ContentProviderWrapper



