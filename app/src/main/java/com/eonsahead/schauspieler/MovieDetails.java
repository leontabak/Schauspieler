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

    private String mId;
    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;

    // https://api.themoviedb.org/3/movie/[ID-FROM-MOVIE]/videos?api-key='API-KEY'&
    // language=en-US

    public MovieDetails( JSONObject json ) {
        try {
            String id = json.getString("id");
            this.setId(id);

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

    public String getId() {
        return mId;
    } // getId()

    public void setId(String id) {
        mId = id;
    } // setId( String )

    public String getOriginalTitle() {
        return mOriginalTitle;
    } // getOriginalTitle()

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    } // setOriginalTitle( String )

    public String getOverview() {
        return mOverview;
    } // getOverview()

    public void setOverview(String overview) {
        mOverview = overview;
    } // setOverview( String )

    public double getVoteAverage() {
        return mVoteAverage;
    } // getVoteAverage()

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    } // setVoteAverage( double )

    public double getPopularity() {
        return mPopularity;
    } // getPopularity()

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    } // setPopularity( double )

    public String getReleaseDate() {
        return mReleaseDate;
    } // getReleaseDate()

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    } // setReleaseDate( String )

    public String getPosterPath() {
        return mPosterPath;
    } // getPosterPath()

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    } // setPosterPath( String )

    private String makePosterPath( String fileName, ImageSizes size ) {
        return "http://image.tmdb.org/t/p/" + size.getSizeSpecifier() + "/" + fileName;
    } // makePosterPath( String, String )
} // MovieDetails
