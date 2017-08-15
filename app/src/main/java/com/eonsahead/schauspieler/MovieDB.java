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
    //    private static final String TAG = MovieDB.class.getSimpleName();
    private static final String TAG = "MovieDB";

    private final List<MovieDetails> mRecords;

    public MovieDB() {
        mRecords = new ArrayList<>();
    } // MovieDB()

    public void clear() {
        mRecords.clear();
    } // clear()

    public List<MovieDetails> getRecords() {
        return mRecords;
    } // getRecords()

    public MovieDetails getRecords(int index) {
        return mRecords.get(index);
    } // getRecords( int )

    public MovieDB select(SortCriterion sortCriterion) {

        if (sortCriterion == null) {
            Log.d(TAG, "sortCriterion is null");
        } // if
        else {
            Log.d(TAG, "sortCriterion = " + sortCriterion.toString());
            Log.d(TAG, "sortCriterion == SortCriterion.FAVORITE? " + (sortCriterion == SortCriterion.FAVORITE));
            Log.d(TAG, "sortCriterion == SortCriterion.POPULARITY? " + (sortCriterion == SortCriterion.POPULARITY));
            Log.d(TAG, "sortCriterion == SortCriterion.RATING? " + (sortCriterion == SortCriterion.RATING));
        } // else

        MovieDB result = new MovieDB();
        for (MovieDetails details : mRecords) {
            if (details.getIsFavorite() && (sortCriterion == SortCriterion.FAVORITE)) {
                Log.d(TAG, "add favorite");
                result.addRecord(details);
            } else if (details.getIsPopular() && (sortCriterion == SortCriterion.POPULARITY)) {
                Log.d(TAG, "add popular");
                result.addRecord(details);
            } // if
            else if (details.getIsHighlyRated() && (sortCriterion == SortCriterion.RATING)) {
                Log.d(TAG, "add rated");
                result.addRecord(details);
            } // else if
            else {
                Log.d(TAG, "do not add " + details.getOriginalTitle() + " is favorite? " +
                        details.getIsFavorite() + " is popular? " +
                        details.getIsPopular() + " is highly rated? " +
                        details.getIsHighlyRated());
            } // else
        } // for

        Log.d(TAG, "return database whose size = " + result.size());
        return result;
    } // select( SortCriterion )

    public int size() {
        return mRecords.size();
    } // size()

    public void addRecord(MovieDetails movieDetails) {
        mRecords.add(movieDetails);
    } // addRecord( MovieDetails )

} // MovieDB
