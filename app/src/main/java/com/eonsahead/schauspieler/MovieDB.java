package com.eonsahead.schauspieler;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieDB {
    private static final String TAG = MovieDB.class.getSimpleName();
    private static MovieDB mMovieDB = null;

    private MovieQueries mMovieQueries;
    private List<MovieDetails> mRecords;

    private MovieDB(MovieQueries movieQueries) {
        mMovieQueries = movieQueries;
        mRecords = new ArrayList<>();
        for (int i = 0; i < mMovieQueries.size(); i++) {

            URL url = mMovieQueries.getURLs(i);
            Log.d(TAG, "record #" + i + " URL: " + url.toString() );

            MovieDetails details = new MovieDetails(url);
            mRecords.add(details);
        } // for
    } // MovieDB()

    public static MovieDB getInstance() {
        Log.d(TAG, "*** getInstance() ***");
        if (mMovieDB == null) {
            mMovieDB = new MovieDB(MovieQueries.getInstance());
        } // if
        return mMovieDB;
    } // getInstance()

    public void update() {
        Log.d(TAG, "*** update() ***");
        MovieDB.MovieDescriptionsTask task = new MovieDB.MovieDescriptionsTask();
        task.execute(mRecords);
    } // update()

    public List<MovieDetails> getRecords() {
        return mRecords;
    } // getRecords()

    public MovieDetails getRecords(int index) {
        return mRecords.get(index);
    } // getRecords( int )

    public int size() {
        return mRecords.size();
    } // size()

    private class MovieDescriptionsTask extends AsyncTask<List<MovieDetails>, Void, List<MovieDetails>> {
        private final String TAG = MovieDB.TAG;

        @Override
        protected List<MovieDetails> doInBackground(List<MovieDetails>... params) {
            List<MovieDetails> records = params[0];

            Log.d( TAG, "number of records = " + records.size() );

            for (MovieDetails details : records) {
                URL url = details.getQueryURL();
                MovieDetails result = new MovieDetails(url);

                Log.d(TAG, "Find record at: " + url.toString());

                HttpURLConnection connection = null;
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    InputStream is = connection.getInputStream();

                    String text = "";
                    Scanner scanner = new Scanner(is);
                    scanner.useDelimiter("\\A");

                    if(scanner.hasNext()) {
                        text = scanner.next();
                    } // if
                    scanner.close();

                    is.close();
                    details.update(text);
                } // try
                catch (IOException e) {
                    e.printStackTrace();
                } // catch( IOException )
                finally {
                    connection.disconnect();
                } // finally
            } // for

            return records;
        } // doInBackground()

        @Override
        protected void onPostExecute(List<MovieDetails> details) {

        } // onPostExecute( List<MovieDetails> )
    } // MovieDescriptionsTask
} // MovieDB
