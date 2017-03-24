package com.eonsahead.schauspieler;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ImageClickListener implements OnClickListener {
    private static final String TAG = ImageClickListener.class.getSimpleName();

    private Context mContext;

    public ImageClickListener( Context context ) {
        Log.i( TAG, "here");
        mContext = context;
    } // ImageClickListener( Context )

    public void onClick( View view ) {
        Log.i( TAG, "here");
        Toast.makeText( mContext, "Alles gut", Toast.LENGTH_LONG );
    } // onClick( View )
} // ImageClickListener
