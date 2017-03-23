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


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] pictureIds = {
            R.drawable.arboretum00,
            R.drawable.arboretum01,
            R.drawable.arboretum02,
            R.drawable.arboretum03,
            R.drawable.arboretum04,
            R.drawable.arboretum05,
            R.drawable.arboretum06,
            R.drawable.arboretum07,
            R.drawable.arboretum08
    };

    private String[] photoURLs = {
            "http://www.countingfromzero.com/images/arboretum00.jpg",
            "http://www.countingfromzero.com/images/arboretum01.jpg",
            "http://www.countingfromzero.com/images/arboretum02.jpg",
            "http://www.countingfromzero.com/images/arboretum03.jpg",
            "http://www.countingfromzero.com/images/arboretum04.jpg",
            "http://www.countingfromzero.com/images/arboretum05.jpg",
            "http://www.countingfromzero.com/images/arboretum06.jpg",
            "http://www.countingfromzero.com/images/arboretum07.jpg",
            "http://www.countingfromzero.com/images/arboretum08.jpg"
    };

    public ImageAdapter(Context context) {
        this.mContext = context;
    } // ImageAdapter( Context )

    @Override
    public int getCount() {
        return this.photoURLs.length;
    } // getCount()
//    public int getCount() {
//        return this.pictureIds.length;
//    } // getCount()

    @Override
    public String getItem(int index) {
        return this.photoURLs[index];
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

