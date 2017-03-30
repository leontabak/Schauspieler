package com.eonsahead.schauspieler;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class KinoViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = KinoViewHolder.class.getSimpleName();

    private Context mContext;
    private Picasso mPicasso;
    private PhotoURLs mResources = PhotoURLs.getInstance();
    private MovieDB mMovieDB;

    private ImageView view;

    public KinoViewHolder(Context context, View itemView, MovieDB movieDB) {
        super(itemView);

        mContext = context;
        mMovieDB = movieDB;

        mPicasso = new Picasso.Builder(this.mContext).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                String message = "Effort to load image failed in Picasso.";
                Toast.makeText(KinoViewHolder.this.mContext, message, Toast.LENGTH_LONG);
                exception.printStackTrace();
            } // onImageLoadFailed( Picasso, Uri, Exception )
        }).build();

        mPicasso.with(this.mContext).setIndicatorsEnabled(true);
        mPicasso.with(this.mContext).setLoggingEnabled(true);

        this.view = (ImageView) itemView.findViewById(R.id.poster);
    } // KinoViewHolder( View )

    public void bind(int index, MovieDB movieDB) {
        this.view.setOnClickListener(new ImageClickListener(mContext, index, mMovieDB));

        if (movieDB.isUpdated()) {
            mPicasso.with(this.mContext).load(movieDB.getRecords(index).getPosterPath())
                    .placeholder(R.drawable.arboretum04)
                    .error(R.drawable.arboretum08)
                    .resize(384, 576)
                    .into(this.view, new PicassoErrorHandler(this.mContext));
        } // if
        else {
            mPicasso.with(this.mContext).load(mResources.getNextURL())
                    .placeholder(R.drawable.arboretum04)
                    .error(R.drawable.arboretum08)
                    .resize(384, 576)
                    .into(this.view, new PicassoErrorHandler(this.mContext));
        } // else
    } // bind( int )
} // KinoViewHolder
