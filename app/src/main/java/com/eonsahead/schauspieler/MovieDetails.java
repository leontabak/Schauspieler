package com.eonsahead.schauspieler;

import java.util.Date;

public class MovieDetails {
    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private Date mReleaseDate;
    private String mPosterPath;

    public MovieDetails() {
        mOriginalTitle = "";
        mOverview = "";
        mVoteAverage = 0.0;
        mPopularity = 0.0;
        mReleaseDate = null;
        mPosterPath = "";
    } // MovieDetails()

    public String getOriginalTitle() {
        return mOriginalTitle;
    } // getOriginalTitle()

    public String getOverview() {
        return mOverview;
    } // getOverview()

    public double getVoteAverage() {
        return mVoteAverage;
    } // getVoteAverage()

    public double getPopularity() {
        return mPopularity;
    } // getPopularity()

    public Date getReleaseDate() {
        return mReleaseDate;
    } // getReleaseDate()

    public String getPosterPath() {
        return mPosterPath;
    } // getPosterPath()
} // MovieDetails
