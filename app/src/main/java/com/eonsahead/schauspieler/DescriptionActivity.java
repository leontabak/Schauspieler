package com.eonsahead.schauspieler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {
    private static final String INTENT_ID = "DETAILS";
    private Picasso mPicasso;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_description);

        mPicasso = new Picasso.Builder(this).listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                String message = "Effort to load image failed in Picasso.";
                Toast.makeText(DescriptionActivity.this, message, Toast.LENGTH_LONG);
                exception.printStackTrace();
            } // onImageLoadFailed( Picasso, Uri, Exception )
        }).build();

        mPicasso.with(this).setIndicatorsEnabled(true);
        mPicasso.with(this).setLoggingEnabled(true);

        Intent intent = getIntent();
        MovieDetails details = (MovieDetails) intent.getExtras().getSerializable(INTENT_ID);

        String url = details.getPosterPath();

        ImageView imageView = (ImageView) this.findViewById(R.id.thumbnail);

        mPicasso.with(this).load(url)
                .placeholder(R.drawable.arboretum04)
                .error(R.drawable.arboretum08)
                .resize(384, 576)
                .into(imageView, new PicassoErrorHandler(this));

        TextView originalTitle = (TextView) this.findViewById(R.id.original_title);
        originalTitle.setText(details.getOriginalTitle());

        TextView overview = (TextView) this.findViewById(R.id.overview);
        overview.setText(details.getOverview());

        TextView releaseDate = (TextView) this.findViewById(R.id.release_date);
        releaseDate.setText( "Release date: " + details.getReleaseDate());

        TextView popularity = (TextView) this.findViewById(R.id.popularity);
        popularity.setText("Popularity = " + Double.toString(details.getPopularity()));

        TextView rating = (TextView) this.findViewById(R.id.rating);
        rating.setText("Rating (votes) = " + Double.toString(details.getVoteAverage()));

        Button button = (Button) this.findViewById(R.id.return_to_catalog);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DescriptionActivity.this.finish();
            } // onClickView( View )
        });

    } // onCreate( Bundle )
} // DescriptionActivity
