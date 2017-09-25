package com.eonsahead.schauspieler;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

public class MoviesContentProvider extends ContentProvider {

    public static final int DETAILS = 100;
    public static final int DETAILS_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private MovieDatabaseHelper mMovieDatabaseHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS + "/#", DETAILS_WITH_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_FAVORITES, DETAILS);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_POPULAR, DETAILS);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_RATED, DETAILS);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_LIKE + "/#", DETAILS_WITH_ID);
        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.PATH_DETAILS_UNLIKE + "/#", DETAILS_WITH_ID);
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

        switch (match) {
            case DETAILS:
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

        return returnCursor;
    } // query( Uri, String [], String, String [], String )

    public int delete(@NonNull Uri uri, String selection, String[] selectionArguments) {
        return 0;
    } // delete( Uri, String, String [] )

    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    } // insert( Uri, ContentValues )

    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArguments) {
        throw new UnsupportedOperationException("Not yet implemented");
    } // update( Uri, ContentValues, String, String [] )

    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    } // getType( Uri )

} // MoviesContentProvider

