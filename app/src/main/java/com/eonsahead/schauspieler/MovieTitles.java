package com.eonsahead.schauspieler;

import java.util.ArrayList;
import java.util.List;

public class MovieTitles {
    private static MovieTitles sMovieTitles;
    private final List<String> mTitles;
    private int mCurrentIndex;

    private MovieTitles() {
        mTitles = new ArrayList<>();
        mCurrentIndex = 0;

        mTitles.add("Bridge Over the River Kwai");
        mTitles.add("North by Northwest");
        mTitles.add("Flight of the Phoenix");
        mTitles.add("Great Escape");
        mTitles.add("Where Eagles Dare");
        mTitles.add("Duck Soup");
        mTitles.add("Apollo 13");
        mTitles.add("The Right Stuff");
        mTitles.add("Goldfinger");
        mTitles.add("Silent World");
        mTitles.add("French Connection");
    } // MovieTitles()

    public static MovieTitles getInstance() {
        if (sMovieTitles == null) {
            sMovieTitles = new MovieTitles();
        } // if
        return sMovieTitles;
    } // getInstance()

    public List<String> getTitles() {
        return mTitles;
    } // getTitles()

    public String getTitle(int index) {
        return mTitles.get(index);
    } // getTitle( int )

    public String getNextTitle() {
        String result = mTitles.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mTitles.size();
        return result;
    } // getNextTitle()

    public int size() {
        return mTitles.size();
    } // size()

} // MovieTitles
