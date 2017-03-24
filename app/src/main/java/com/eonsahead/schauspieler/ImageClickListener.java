package com.eonsahead.schauspieler;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ImageClickListener implements View.OnClickListener {
    private static final String TAG = ImageClickListener.class.getSimpleName();

    private Context mContext;
    private int mIndex;

    public ImageClickListener( Context context, int index ) {
        Log.i( TAG, "Construct!");
        mContext = context;
        mIndex = index;
    } // ImageClickListener( Context )

    @Override
    public void onClick( View view ) {
        Log.i( TAG, "Respond to click on image #" + mIndex);
        Toast.makeText( mContext, "Alles gut", Toast.LENGTH_LONG ).show();
    } // onClick( View )
} // ImageClickListener
