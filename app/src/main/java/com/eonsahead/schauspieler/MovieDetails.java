package com.eonsahead.schauspieler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieDetails implements Serializable {
    private static final String TAG = MovieDetails.class.getSimpleName();

    private URL mQueryURL;
    private boolean mUpdated;

    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;

    public MovieDetails( URL url ) {
        mQueryURL = url;
        mUpdated = false;

        setOriginalTitle("TITLE");
        setOverview("OVERVIEW");
        setVoteAverage(0.0);
        setPopularity(0.0);
        setReleaseDate("DATE");
        setPosterPath( null );
    } // MovieDetails( URL )

    public URL getQueryURL() {
        return mQueryURL;
    } // getQueryURL()

    public boolean isUpdated() {
        return mUpdated;
    } // isUpdated()

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

    public void update( String jsonText ) {

        try {
            JSONObject object = new JSONObject(jsonText.toString());
            JSONArray movieResults = object.getJSONArray( "movie_results" );
            JSONObject first = movieResults.getJSONObject(0);

            String originalTitle = (String) first.get( "original_title");
            this.setOriginalTitle( originalTitle );
            Log.d( TAG, originalTitle );

            String overview = (String) first.get( "overview" );
            this.setOverview( overview );
            Log.d( TAG, overview );

            Double voteAverage = (Double) first.get( "vote_average" );
            this.setVoteAverage( voteAverage );
            Log.d( TAG, voteAverage.toString() );

            Double popularity = (Double) first.get( "popularity" );
            this.setPopularity( popularity );
            Log.d( TAG, popularity.toString() );

            String releaseDate = (String) first.get( "release_date" );
            this.setReleaseDate( releaseDate );
            Log.d( TAG, releaseDate );

            String posterPath = makePosterPath((String) first.get( "poster_path" ), ImageSizes.w500);
            this.setPosterPath( posterPath );
            Log.d( TAG, posterPath.toString() );

            mUpdated = true;
        } // try
        catch( JSONException e ) {
            e.printStackTrace();
        } // catch( JSONException )
    } // update( String )

    private String makePosterPath( String fileName, ImageSizes size ) {
        return "http://image.tmdb.org/t/p/" + size.getSizeSpecifier() + "/" + fileName;
    } // makePosterPath( String, String )
} // MovieDetails
