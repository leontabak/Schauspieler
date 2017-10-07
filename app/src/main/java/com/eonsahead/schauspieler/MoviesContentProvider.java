package com.eonsahead.schauspieler;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

public class MoviesContentProvider extends ContentProvider {
    public static final String TAG = "MoviesContentProvider";
    public static final int MOVIES = 100;
    public static final int MOVIES_ID = 101;
    public static final int REVIEWS_ID = 201;
    public static final int TRAILERS_ID = 301;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MovieDatabaseHelper mMovieDatabaseHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_MOVIES + "/#", MOVIES_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_REVIEWS + "/#", REVIEWS_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_TRAILERS + "/#",
                TRAILERS_ID);
        return uriMatcher;
    } // buildUriMatcher()

    public boolean onCreate() {
        Context context = this.getContext();
        mMovieDatabaseHelper = new MovieDatabaseHelper(context);
        return true;
    } // onCreate()

    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArguments, String sortOrder) {

        final SQLiteDatabase db = mMovieDatabaseHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        Cursor returnCursor;

        String id;

        switch (match) {
            case MOVIES:
                Cursor result = db.query(
                        MoviesContract.Details.TABLE_NAME,
                        projection,
                        selection,
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                returnCursor = result;
                break;
            case REVIEWS_ID:
                id = uri.getPathSegments().get(1);
                returnCursor = db.query(
                        MoviesContract.Reviews.TABLE_NAME,
                        projection,
                        selection,
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                break;
            case TRAILERS_ID:
                id = uri.getPathSegments().get(1);
                returnCursor = db.query(
                        MoviesContract.Trailers.TABLE_NAME,
                        projection,
                        selection,
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        } // switch

        //db.close();

        boolean goodResult = (returnCursor != null);
//        while( returnCursor.moveToNext() ) {
//                String id = returnCursor.getString(0);
//                String title = returnCursor.getString(1);
//                Log.d(TAG, id + " " + title + " (4-ContentProvider");
//            } // while

        MovieCursorWrapper cursorWrapper = new MovieCursorWrapper(returnCursor);
        while (cursorWrapper.moveToNext()) {
            MovieDetails details = cursorWrapper.getMovieDetails();
            int movieId = details.getId();
            String title = details.getOriginalTitle();
            Log.d(TAG, movieId + " " + title);
        } // while

        ContentResolver contentResolver = getContext().getContentResolver();
        returnCursor.setNotificationUri(contentResolver, uri);
        return returnCursor;
    } // query( Uri, String [], String, String [], String )

    public int delete(@NonNull Uri uri, String selection, String[] selectionArguments) {
        return 0;
    } // delete( Uri, String, String [] )

    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    } // insert( Uri, ContentValues )

    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArguments) {

        final SQLiteDatabase db = mMovieDatabaseHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        String id = "";
        switch (match) {
            case MOVIES_ID:
                id = uri.getPathSegments().get(1);
                db.update(MoviesContract.Details.TABLE_NAME, values, selection, selectionArguments);
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI " + uri);
        } // switch
        return 0;
    } // update( Uri, ContentValues, String, String [] )

    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    } // getType( Uri )

} // MoviesContentProvider

