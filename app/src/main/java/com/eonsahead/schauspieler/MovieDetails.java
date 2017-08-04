package com.eonsahead.schauspieler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieDetails implements Serializable {
    private static final String TAG = "MovieDetails";

    private int mId;
    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;
    private List<Review> mReviews;
    private List<Trailer> mTrailers;


    public MovieDetails( JSONObject json ) {
        try {
            int id = json.getInt("id");
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

            this.mReviews = new ArrayList<>();
            this.mTrailers = new ArrayList<>();
        } // try
        catch( JSONException e ) {
            e.printStackTrace();
        } // catch( JSONException )
    } // MovieDetails( URL )

    public int getId() {
        return mId;
    } // getId()

    public void setId(int id) {
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

    public List<Review> getReviews() {
        return mReviews;
    } // getReviews()

    public void addReview(Review review) {
        mReviews.add(review);
    } // addReview( Review )

    public List<Trailer> getTrailers() {
        return mTrailers;
    } // getTrailers()

    public void addTrailer(Trailer trailer) {
        mTrailers.add(trailer);
    } // addTrailer( Trailer )

    private String makePosterPath( String fileName, ImageSizes size ) {
        return "http://image.tmdb.org/t/p/" + size.getSizeSpecifier() + "/" + fileName;
    } // makePosterPath( String, String )
} // MovieDetails
