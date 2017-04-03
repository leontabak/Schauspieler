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
    // Edit the following line. Insert your key for the themoviedb.org online
    // database on the right-hand side of the assignment in place of my call
    // to APIKey.getInstance().getKey().
    private final String theMovieDBKey = APIKey.getInstance().getKey();

    @Override
    protected MainActivity doInBackground(MainActivity... params) {
        MainActivity mainActivity = params[0];
        MovieDB movieDB = mainActivity.getMovieDB();
        SortCriterion sortCriterion = mainActivity.getSortCriterion();

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
                movieDB.clear();
                JSONObject object = new JSONObject(text.toString());
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject movie = array.getJSONObject(i);
                    MovieDetails movieDetails = new MovieDetails(movie);

                    movieDB.addRecord(movieDetails);

                    String title = movieDetails.getOriginalTitle();
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

        return mainActivity;
    } // doInBackground()

    @Override
    protected void onPostExecute(MainActivity mainActivity) {
        mainActivity.refresh();
    } // onPostExecute( List<MovieDetails> )

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
} // MovieDescriptionsTask

