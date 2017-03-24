package com.eonsahead.schauspieler;

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

//    private TextView view;
    private ImageView view;

    private MovieAbbreviations mAbbreviations = MovieAbbreviations.getInstance();

    public KinoViewHolder(Context context, View itemView) {
        super(itemView);

        mContext = context;
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

//        this.view = (TextView) itemView.findViewById(R.id.view_holder);
        this.view = (ImageView) itemView.findViewById(R.id.poster);
    } // KinoViewHolder( View )

    public void bind(int index) {
//        view.setText( mAbbreviations.getAbbreviation(index));

        this.view.setOnClickListener( new ImageClickListener(mContext, index));

        mPicasso.with(this.mContext).load(mResources.getURL(index))
                .placeholder(R.drawable.arboretum04)
                .error(R.drawable.arboretum08)
                .resize(384, 384)
                .into(this.view, new PicassoErrorHandler(this.mContext));
    } // bind( int )
} // KinoViewHolder
