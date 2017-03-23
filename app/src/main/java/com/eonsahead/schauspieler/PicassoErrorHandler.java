package com.eonsahead.schauspieler;

import android.content.Context;
import android.widget.Toast;

import com.squareup.picasso.Callback;

public class PicassoErrorHandler implements Callback {

    private Context context;

    public PicassoErrorHandler(Context context) {
        this.context = context;
    } // PicassoErrorHandler()

    @Override
    public void onError() {
        String message = "Effort to load image failed. Try again later.";
        Toast.makeText(this.context, message, Toast.LENGTH_LONG);
    } // onError()

    public void onSuccess() {
        // Do nothing when Picasso succeeds in loading image.
    } // onSuccess()

} // PicassoErrorHandler
