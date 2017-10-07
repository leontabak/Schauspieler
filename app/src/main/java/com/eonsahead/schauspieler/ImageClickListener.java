package com.eonsahead.schauspieler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ImageClickListener implements View.OnClickListener {
    private static final String TAG = "ImageClickListener"; // ImageClickListener.class.getSimpleName();
    private static final String INTENT_ID = "DETAILS";

    private Context mContext;
    private int mIndex;
    private MovieDB mMovieDB;

    public ImageClickListener(Context context, int index, MovieDB movieDB) {
        mContext = context;
        mIndex = index;
        mMovieDB = movieDB;
    } // ImageClickListener( Context )

    @Override
    public void onClick(View view) {

        Class descriptionActivity = DescriptionActivity.class;

        Intent intent = new Intent(mContext, descriptionActivity);
        MovieDetails details = mMovieDB.getRecords(mIndex);
        intent.putExtra(INTENT_ID, details);

        mContext.startActivity(intent);
    } // onClick( View )
} // ImageClickListener
