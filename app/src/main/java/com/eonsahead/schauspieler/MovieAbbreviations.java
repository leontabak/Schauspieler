package com.eonsahead.schauspieler;


import java.util.ArrayList;
import java.util.List;

public class MovieAbbreviations {
    private static MovieAbbreviations sMovieAbbreviations;
    private final List<String> mAbbreviations;
    private int mCurrentIndex;

    private MovieAbbreviations() {
        mAbbreviations = new ArrayList<>();
        mCurrentIndex = 0;

        mAbbreviations.add("River Kwai");
        mAbbreviations.add("Northwest");
        mAbbreviations.add("Phoenix");
        mAbbreviations.add("Great Escape");
        mAbbreviations.add("Eagles Dare");
        mAbbreviations.add("Duck Soup");
        mAbbreviations.add("Apollo 13");
        mAbbreviations.add("Right Stuff");
        mAbbreviations.add("Goldfinger");
        mAbbreviations.add("Silent World");
        mAbbreviations.add("French Connection");
    } // MovieAbbreviations()

    public static MovieAbbreviations getInstance() {
        if (sMovieAbbreviations == null) {
            sMovieAbbreviations = new MovieAbbreviations();
        } // if

        return sMovieAbbreviations;
    } // getInstance()

    public List<String> getAbbreviations() {
        return mAbbreviations;
    } // getAbbreviations()

    public String getAbbreviation(int index) {
        return mAbbreviations.get(index);
    } // getAbbreviation( int )

    public String getNextAbbreviation() {
        String result = mAbbreviations.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mAbbreviations.size();
        return result;
    } // getNextAbbreviation()

    public int size() {
        return mAbbreviations.size();
    } // size()
} // MovieAbbreviations
