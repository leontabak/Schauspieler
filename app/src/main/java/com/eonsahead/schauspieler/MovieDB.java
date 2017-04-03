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

    public int size() {
        return mRecords.size();
    } // size()

    public void addRecord( MovieDetails movieDetails ) {
        mRecords.add( movieDetails );
    } // addRecord( MovieDetails )

} // MovieDB
