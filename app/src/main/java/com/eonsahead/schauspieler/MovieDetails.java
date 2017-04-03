package com.eonsahead.schauspieler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetails implements Serializable {
    private static final String TAG = "MovieDetails";

    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;

    public MovieDetails( JSONObject json ) {
        try {
            String originalTitle = json.getString( "original_title");
            this.setOriginalTitle( originalTitle );

            String overview = json.getString( "overview" );
            this.setOverview( overview );

            Double voteAverage = json.getDouble( "vote_average" );
            this.setVoteAverage( voteAverage );

            Double popularity = json.getDouble( "popularity" );
            this.setPopularity( popularity );

            String releaseDate = json.getString( "release_date" );
            this.setReleaseDate( releaseDate );

            String posterPath = makePosterPath(json.getString( "poster_path" ), ImageSizes.w500);
            this.setPosterPath( posterPath );
        } // try
        catch( JSONException e ) {
            e.printStackTrace();
        } // catch( JSONException )
    } // MovieDetails( URL )

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

    public String getReleaseDate() {
        return mReleaseDate;
    } // getReleaseDate()

    public String getPosterPath() {
        return mPosterPath;
    } // getPosterPath()


    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    } // setOriginalTitle( String )

    public void setOverview(String overview) {
        mOverview = overview;
    } // setOverview( String )

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    } // setVoteAverage( double )

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    } // setPopularity( double )

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    } // setReleaseDate( String )

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    } // setPosterPath( String )

    private String makePosterPath( String fileName, ImageSizes size ) {
        return "http://image.tmdb.org/t/p/" + size.getSizeSpecifier() + "/" + fileName;
    } // makePosterPath( String, String )
} // MovieDetails
