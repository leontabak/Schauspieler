package com.eonsahead.schauspieler;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private PhotoURLs photoURLs = PhotoURLs.getInstance();


    public ImageAdapter(Context context) {
        this.mContext = context;
    } // ImageAdapter( Context )

    @Override
    public int getCount() {
        return this.photoURLs.getURLs().size();
    } // getCount()
//    public int getCount() {
//        return this.pictureIds.length;
//    } // getCount()

    @Override
    public String getItem(int index) {
        return this.photoURLs.getURL(index);
    } // getItem( int )

    @Override
    public long getItemId(int index) {
        return 0;
    } // getItemId( int )

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView(this.mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        } // if

        String url = this.getItem(position);

//        imageView.setImageResource(this.pictureIds[position]);

        Picasso picasso = new Picasso.Builder(this.mContext).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        }).build();

        picasso.with(this.mContext).setIndicatorsEnabled(true);
        picasso.with(this.mContext).setLoggingEnabled(true);
        picasso.with(this.mContext).load(url)
                .placeholder(R.drawable.arboretum04)
                .error(R.drawable.arboretum08)
                .resize( 384, 384 )
                .into(imageView, new PicassoErrorHandler(this.mContext));
        return imageView;
    } // getView( int, View, ViewGroup )

} // ImageAdapter

