package com.eonsahead.schauspieler;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MovieDB {
    private static final String TAG = MovieDB.class.getSimpleName();

    private MovieQueries mMovieQueries;
    private final MainActivity mMainActivity;
    private final List<MovieDetails> mRecords;
    private boolean mUpdated = false;
    private final PopularityComparator mPopularityComparator = new PopularityComparator();
    private final VoteComparator mVoteComparator = new VoteComparator();

    public MovieDB( MovieQueries movieQueries, MainActivity mainActivity ) {
        mMovieQueries = movieQueries;
        mMainActivity = mainActivity;
        mRecords = new ArrayList<>();
        for (int i = 0; i < mMovieQueries.size(); i++) {

            URL url = mMovieQueries.getURLs(i);
            Log.d(TAG, "record #" + i + " URL: " + url.toString());

            MovieDetails details = new MovieDetails(url);
            mRecords.add(details);
        } // for
    } // MovieDB()

    public boolean isUpdated() {
        return mUpdated;
    } // isUpdated()

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

            Log.d(TAG, "number of records = " + records.size());

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

                    if (scanner.hasNext()) {
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
        protected void onPostExecute(List<MovieDetails> records) {
            boolean check = true;
            for (MovieDetails record : records) {
                check = check && record.isUpdated();
            } // for
            MovieDB.this.mUpdated = check;

            if( MovieDB.this.isUpdated() ) {
                MovieDB.this.sort( SortCriterion.POPULARITY );
                mMainActivity.refresh();
            } // if
        } // onPostExecute( List<MovieDetails> )
    } // MovieDescriptionsTask

    public void sort(SortCriterion criterion) {
        switch (criterion) {
            case POPULARITY:
                Collections.sort(mRecords, mPopularityComparator);
                break;
            case VOTES:
                Collections.sort(mRecords, mVoteComparator);
                break;
            default:
                break;
        } // switch
    } // sort( SortCriterion )

    private class PopularityComparator implements Comparator<MovieDetails> {

        public PopularityComparator() {
        } // PopularityComparator()

        public int compare(MovieDetails a, MovieDetails b) {
            if (a.getPopularity() < b.getPopularity()) {
                return -1;
            } // if
            else if (a.getPopularity() > b.getPopularity()) {
                return +1;
            } // else if
            else {
                return 0;
            } // else
        } // compare( MovieDetails, MovieDetails )

        public boolean equals(Object object) {
            return this.equals(object);
        } // equals( Objecct )
    } // PopularityComparator

    private class VoteComparator implements Comparator<MovieDetails> {

        public VoteComparator() {
        } // VoteComparator()

        public int compare(MovieDetails a, MovieDetails b) {
            if (a.getVoteAverage() < b.getVoteAverage()) {
                return -1;
            } // if
            else if (a.getVoteAverage() > b.getVoteAverage()) {
                return +1;
            } // else if
            else {
                return 0;
            } // else
        } // compare( MovieDetails, MovieDetails )

        public boolean equals(Object object) {
            return this.equals(object);
        } // equals( Objecct )
    } // VoteComparator

} // MovieDB
