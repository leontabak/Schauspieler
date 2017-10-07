package com.eonsahead.schauspieler;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class KinoViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "KinoViewHolder";

    private Context mContext;
    private Picasso mPicasso;
    private PhotoResources mResources = PhotoResources.getInstance();
    private MovieDB mMovieDB;

    private ImageView view;

    public KinoViewHolder(Context context, View itemView, MovieDB movieDB) {
        super(itemView);

        mContext = context;
        mMovieDB = movieDB;

        if (Mode.ONLINE) {
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
        } // if

        this.view = (ImageView) itemView.findViewById(R.id.poster);
    } // KinoViewHolder( View )

    public void bind(int index) {
        this.view.setOnClickListener(new ImageClickListener(mContext, index, mMovieDB));

        if (Mode.ONLINE) {
            if (mMovieDB.size() > 0) {
                mPicasso.with(this.mContext).load(mMovieDB.getRecords(index).getPosterPath())
                        .placeholder(R.drawable.arboretum04)
                        .error(R.drawable.arboretum08)
                        .resize(384, 576)
                        .into(this.view, new PicassoErrorHandler(this.mContext));
            } // if
            else {
                mPicasso.with(this.mContext).load(mResources.getNextResource())
                        .placeholder(R.drawable.arboretum04)
                        .error(R.drawable.arboretum08)
                        .resize(384, 576)
                        .into(this.view, new PicassoErrorHandler(this.mContext));
            } // else
        } // if
        else {
            this.view.setImageResource(R.drawable.arboretum00);
        } // else
    } // bind( int )
} // KinoViewHolder
