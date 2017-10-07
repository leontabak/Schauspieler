package com.eonsahead.schauspieler;


import android.content.ContentProvider;
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
    public static final int DETAILS = 100;
    public static final int DETAILS_WITH_ID = 101;
    public static final int LIKE_WITH_ID = 102;
    public static final int UNLIKE_WITH_ID = 103;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MovieDatabaseHelper mMovieDatabaseHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS, DETAILS);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS + "/#", DETAILS_WITH_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_LIKE + "/#",
                LIKE_WITH_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_UNLIKE + "/#",
                UNLIKE_WITH_ID);
        return uriMatcher;
    } // buildUriMatcher()

    public boolean onCreate() {
        Log.d(TAG, "onCreate (0)");
        Context context = this.getContext();
        mMovieDatabaseHelper = new MovieDatabaseHelper(context);
        Log.d(TAG, "onCreate (1)");
        return true;
    } // onCreate()

    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArguments, String sortOrder) {

        Log.d(TAG, "query (2)");
        final SQLiteDatabase db = mMovieDatabaseHelper.getReadableDatabase();

        Log.d(TAG, "query (3) " + uri.toString());
        int match = sUriMatcher.match(uri);
        Cursor returnCursor;

        switch (match) {
            case DETAILS:
                Log.d(TAG, "query (4) got a match with ALL");

                Cursor result = db.query(
                        MoviesContract.Details.TABLE_NAME,
                        projection,
                        selection,
                        selectionArguments,
                        null,
                        null,
                        sortOrder
                );
                Log.d(TAG, "query (4.1) got a result");
                returnCursor = result;
                break;
            case DETAILS_WITH_ID:
                Log.d(TAG, "query (5) got a match with ALL with an id");
                String id = uri.getPathSegments().get(1);
                Log.d(TAG, "query (6) got the id = " + id);
                returnCursor = db.query(
                        MoviesContract.Details.TABLE_NAME,
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
        Log.d(TAG, "(4.2) return to caller " + goodResult);
        Log.d(TAG, "(4.3) count = ");
//        while( returnCursor.moveToNext() ) {
//                String id = returnCursor.getString(0);
//                String title = returnCursor.getString(1);
//                Log.d(TAG, id + " " + title + " (4-ContentProvider");
//            } // while
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

        Log.d(TAG, "update (2)");
        final SQLiteDatabase db = mMovieDatabaseHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        String id = "";
        switch (match) {
            case LIKE_WITH_ID:
                id = uri.getPathSegments().get(1);
                Log.d(TAG, "update (6) got the id = " + id);
                db.update(MoviesContract.Details.TABLE_NAME, values, selection, selectionArguments);
                break;
            case UNLIKE_WITH_ID:
                id = uri.getPathSegments().get(1);
                Log.d(TAG, "update (6) got the id = " + id);
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

