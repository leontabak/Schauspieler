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
    private static int count = 0;

    private int mId;
    private boolean mIsFavorite;
    private boolean mIsPopular;
    private boolean mIsHighlyRated;
    private String mOriginalTitle;
    private String mOverview;
    private double mVoteAverage;
    private double mPopularity;
    private String mReleaseDate;
    private String mPosterPath;
    private List<Review> mReviews;
    private List<Trailer> mTrailers;


    public MovieDetails() {
        mId = count;
        mIsFavorite = false;

        if (Math.random() < 0.5) {
            mIsPopular = true;
            mIsHighlyRated = false;
        } // if
        else {
            mIsPopular = false;
            mIsHighlyRated = true;
        } // else

        mOriginalTitle = DataGenerator.word(mId, 12);
        mOverview = DataGenerator.paragraph(mId, 5, 60);
        mVoteAverage = Math.random();
        mPopularity = Math.random();
        mReleaseDate = "20 July 1969";
        mPosterPath = "";

        mReviews = new ArrayList<>();
        Review review = new Review(DataGenerator.word(mId, 12), DataGenerator.paragraph(mId, 5, 96));
        mReviews.add(review);

        mTrailers = new ArrayList<>();
        Trailer trailer = new Trailer(DataGenerator.word(mId, 12), DataGenerator.word(mId, 8),
                DataGenerator.word(mId, 10));
        mTrailers.add(trailer);
        count++;
    } // MovieDetails()

    public MovieDetails(JSONObject json, SortCriterion sortCriterion) {
        try {
            int id = json.getInt("id");
            this.setId(id);

            this.clearIsFavorite();
            this.clearIsPopular();
            this.clearIsHighlyRated();

            if (sortCriterion == SortCriterion.POPULARITY) {
                this.setIsPopular();
            } // if
            else if (sortCriterion == SortCriterion.RATING) {
                this.setIsHighlyRated();
            } // else if

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

            Log.d(TAG, "create record for " + this.getOriginalTitle() + " " + sortCriterion.toString());

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

    public boolean getIsFavorite() {
        return mIsFavorite;
    } // getIsFavorite()

    public void clearIsFavorite() {
        mIsFavorite = false;
    } // clearIsFavorite()

    public void setIsFavorite() {
        mIsFavorite = true;
    } // setIsFavorite()

    public void toggleIsFavorite() {
        mIsFavorite = !mIsFavorite;
    } // toggleIsFavorite()

    public boolean getIsPopular() {
        return mIsPopular;
    } // getIsPopular()

    public void clearIsPopular() {
        mIsPopular = false;
    } // clearIsPopular()

    public void setIsPopular() {
        mIsPopular = true;
    } // setIsPopular()

    public boolean getIsHighlyRated() {
        return mIsHighlyRated;
    } // getIsHighlyRated()

    public void clearIsHighlyRated() {
        mIsHighlyRated = false;
    } // clearIsHighlyRated()

    public void setIsHighlyRated() {
        mIsHighlyRated = true;
    } // setIsHighlyRated()

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
