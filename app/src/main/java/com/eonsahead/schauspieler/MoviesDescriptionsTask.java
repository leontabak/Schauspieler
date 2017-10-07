package com.eonsahead.schauspieler;


import android.content.ContentUris;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MoviesDescriptionsTask extends AsyncTask<MainActivity, Void, MainActivity> {
    private final String TAG = "MoviesDescriptionsTask";
    private final String REVIEWS = "reviews";
    private final String VIDEOS = "videos";

    // Edit the following line. Insert your key for the themoviedb.org online
    // database on the right-hand side of the assignment in place of my call
    // to APIKey.getInstance().getKey().
    private final String theMovieDBKey = APIKey.getInstance().getKey();
    private MovieDB mMovieDB;
    private MovieDetails mMovieDetails;

    @Override
    protected MainActivity doInBackground(MainActivity... params) {
        MainActivity mainActivity = params[0];
        mMovieDB = mainActivity.getMovieDB();
//        SortCriterion sortCriterion = mainActivity.getSortCriterion();

        if (Mode.ONLINE) {
            query(SortCriterion.POPULARITY);
            query(SortCriterion.RATING);

//            for (MovieDetails details : mMovieDB.getRecords()) {
//                int movieId = details.getId();
//                query(movieId, REVIEWS);
//                query(movieId, VIDEOS);
//            } // for
        } // if
        else {
            generateData(12, SortCriterion.POPULARITY);
            generateData(12, SortCriterion.RATING);
        } // else

        MovieDatabaseHelper helper = new MovieDatabaseHelper(mainActivity);

        try {

            SQLiteDatabase db = helper.getWritableDatabase();

            helper.onCreate(db);

            for (MovieDetails details : mMovieDB.getRecords()) {
                Log.d(TAG, "MoviesDescriptionTask (4-add)");
                helper.addMovie(db, details);
            } // for

            db.close();

            ContentProviderWrapper wrapper = new ContentProviderWrapper(mainActivity);

            Cursor cursor = wrapper.getFavorites();

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                Log.d(TAG, id + " " + title + " (4-ContentProvider");
            } // while
            cursor.close();
        } // try
        catch (SQLiteException e) {
            Log.d(TAG, "MoviesDescriptionTask SQLiteException " + e.getMessage());
        } // catch( SQLiteException )

        return mainActivity;
    } // doInBackground()

    @Override
    protected void onPostExecute(MainActivity mainActivity) {
        mainActivity.refresh();
    } // onPostExecute( MainActivity )

    private void generateData(int movieCount, SortCriterion sortCriterion) {
        for (int i = 0; i < movieCount; i++) {
            MovieDetails details = new MovieDetails();
            mMovieDB.addRecord(details);
        } // for
    } // generateData( int, SortCriterion )

    private void generateData(int movieId, String dataType) {
        if (dataType.equals(REVIEWS)) {

        } // if
        else if (dataType.equals(VIDEOS)) {

        } // else if
    } // generateData( int, String )

    private void query(SortCriterion sortCriterion) {
        URL url = makeURL(sortCriterion);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            String text = "";
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                text = scanner.next();
            } // if
            scanner.close();

            try {
//                mMovieDB.clear();
                JSONObject object = new JSONObject(text.toString());
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject movie = array.getJSONObject(i);
                    mMovieDetails = new MovieDetails(movie, sortCriterion);

                    mMovieDB.addRecord(mMovieDetails);

                    String title = mMovieDetails.getOriginalTitle();
                } // for
            } // try
            catch (JSONException e) {
                e.printStackTrace();
            } // catch( JSONException )

            is.close();
        } // try
        catch (IOException e) {
            e.printStackTrace();
        } // catch( IOException )
        finally {
            connection.disconnect();
        } // finally
    } // query()

    private void query(int movieId, String dataType) {
        URL url = makeURL(movieId, dataType);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            String text = "";
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                text = scanner.next();
            } // if
            scanner.close();

            try {
                JSONObject object = new JSONObject(text.toString());
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject movie = array.getJSONObject(i);

                    if (dataType.equals(REVIEWS)) {
                        // fetch a review of the movie
                        String author = movie.getString("author");
                        String content = movie.getString("content");

                        Review review = new Review(author, content);
                        mMovieDetails.addReview(review);
                    } // if
                    else if (dataType.equals(VIDEOS)) {
                        // fetch a trailer or featurette
                        String name = movie.getString("name");
                        String key = movie.getString("key");
                        String type = movie.getString("type");

                        Trailer trailer = new Trailer(name, key, type);
                        mMovieDetails.addTrailer(trailer);
                    } // else if

                } // for
            } // try
            catch (JSONException e) {
                e.printStackTrace();
            } // catch( JSONException )

            is.close();
        } // try
        catch (IOException e) {
            e.printStackTrace();
        } // catch( IOException )
        finally {
            connection.disconnect();
        } // finally
    } // query( int, String )

    private final URL makeURL(SortCriterion criterion) {

        String typeOfSearch = "";
        switch (criterion) {
            case FAVORITE:
                // value should never be FAVORITE
                // there is no corresponding URL
                // let's assume user wants POPULARITY
                typeOfSearch = "popular";
                break;
            case POPULARITY:
                typeOfSearch = "popular";
                break;
            case RATING:
                typeOfSearch = "top_rated";
                break;
            default:
                typeOfSearch = "popular";
                break;
        } // switch( criterion )

        String result = "https://api.themoviedb.org/3/movie/" +
                typeOfSearch +
                "?api_key=" +
                theMovieDBKey +
                "&language=en-US&page=1";

        URL url = null;
        try {
            url = new URL(result);
        } // try
        catch (MalformedURLException e) {
            e.printStackTrace();
        } // catch( MalformedURLException )

        return url;
    } // makeURL( String )

    private final URL makeURL(int movieId, String dataType) {
        String result = "https://api.themoviedb.org/3/movie/" +
                movieId +
                "/" + dataType +
                "?api_key=" +
                theMovieDBKey +
                "&language=en-US";

        URL url = null;
        try {
            url = new URL(result);
        } // try
        catch (MalformedURLException e) {
            e.printStackTrace();
        } // catch( MalformedURLException )

        return url;
    } // makeURL( int, String )
} // MovieDescriptionsTask

