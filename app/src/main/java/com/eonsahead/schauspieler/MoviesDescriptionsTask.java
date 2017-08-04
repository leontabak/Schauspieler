package com.eonsahead.schauspieler;


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
import java.util.List;
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
        SortCriterion sortCriterion = mainActivity.getSortCriterion();

        query(sortCriterion);

        for (MovieDetails details : mMovieDB.getRecords()) {
            int movieId = details.getId();
            query(movieId, REVIEWS);
            query(movieId, VIDEOS);
        } // for

        return mainActivity;
    } // doInBackground()

    @Override
    protected void onPostExecute(MainActivity mainActivity) {
        mainActivity.refresh();
    } // onPostExecute( MainActivity )

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
                mMovieDB.clear();
                JSONObject object = new JSONObject(text.toString());
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject movie = array.getJSONObject(i);
                    mMovieDetails = new MovieDetails(movie);

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
        Log.d(TAG, "url = " + url);

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

                        Log.d(TAG, "author = " + author);
                        Log.d(TAG, "content = " + content);
                    } // if
                    else if (dataType.equals(VIDEOS)) {
                        // fetch a trailer or featurette
                        String name = movie.getString("name");
                        String key = movie.getString("key");
                        String type = movie.getString("type");

                        Trailer trailer = new Trailer(name, key, type);
                        mMovieDetails.addTrailer(trailer);

                        Log.d(TAG, "name = " + name);
                        Log.d(TAG, "key = " + key);
                        Log.d(TAG, "type = " + type);
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
            case POPULARITY:
                typeOfSearch = "popular";
                break;
            case VOTES:
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

