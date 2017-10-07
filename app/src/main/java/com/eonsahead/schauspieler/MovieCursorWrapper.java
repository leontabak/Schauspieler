package com.eonsahead.schauspieler;

import android.database.Cursor;
import android.database.CursorWrapper;

public class MovieCursorWrapper extends CursorWrapper {
    private Cursor mCursor;

    public MovieCursorWrapper(Cursor cursor) {
        super(cursor);
        mCursor = cursor;
    } // MovieCursorWrapper( Cursor )

    public MovieDetails getMovieDetails() {
        int movieIdIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_MOVIE_ID);
        int titleIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_ORIGINAL_TITLE);
        int overviewIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_OVERVIEW);
        int popularityIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_POPULARITY);
        int voteIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_VOTE_AVERAGE);
        int releaseDateIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_RELEASE_DATE);
        int posterPathIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_POSTER_PATH);
        int isFavoriteIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_IS_FAVORITE);
        int isPopularIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_IS_POPULAR);
        int isRatedIndex =
                mCursor.getColumnIndex(MoviesContract.Details.COLUMN_NAME_IS_RATED);

        int movieId = mCursor.getInt(movieIdIndex);
        String title = mCursor.getString(titleIndex);
        String overview = mCursor.getString(overviewIndex);
        String releaseDate = mCursor.getString(releaseDateIndex);
        double popularity = mCursor.getDouble(popularityIndex);
        double voteAverage = mCursor.getDouble(voteIndex);
        String posterPath = mCursor.getString(posterPathIndex);

        boolean isFavorite = false;
        if (mCursor.getInt(isFavoriteIndex) != 0) {
            isFavorite = true;
        } // if

        boolean isPopular = false;
        if (mCursor.getInt(isPopularIndex) != 0) {
            isPopular = true;
        } // if

        boolean isRated = false;
        if (mCursor.getInt(isRatedIndex) != 0) {
            isRated = true;
        } // if

        MovieDetails details = new MovieDetails(movieId);
        details.setOriginalTitle(title);
        details.setOverview(overview);
        details.setReleaseDate(releaseDate);
        details.setPopularity(popularity);
        details.setVoteAverage(voteAverage);
        details.setPosterPath(posterPath);

        if (isFavorite) {
            details.setIsFavorite();
        } // if
        else {
            details.clearIsFavorite();
        } // else

        if (isPopular) {
            details.setIsPopular();
        } // if
        else {
            details.clearIsPopular();
        } // else

        if (isRated) {
            details.setIsHighlyRated();
        } // if
        else {
            details.clearIsHighlyRated();
        } // else
        
        return details;
    } // getMovieDetails()
} // MovieCursorWrapper

