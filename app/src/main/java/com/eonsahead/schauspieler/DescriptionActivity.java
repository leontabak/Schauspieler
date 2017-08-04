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
    private MovieDetails mDetails;
    private int mMovieId;
    private String mPosterPath;

    private Picasso mPicasso;
    private Button mFavoriteButton;
    private Button mPlayButton;
    private Button mReturnButton;

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
        mDetails = (MovieDetails) intent.getExtras().getSerializable(INTENT_ID);

        mPosterPath = mDetails.getPosterPath();
        mMovieId = mDetails.getId();

        (new TrailerAndReviewsTask()).execute(this);

        ImageView imageView = (ImageView) this.findViewById(R.id.thumbnail);

        mPicasso.with(this).load(mPosterPath)
                .placeholder(R.drawable.arboretum04)
                .error(R.drawable.arboretum08)
                .resize(384, 576)
                .into(imageView, new PicassoErrorHandler(this));

        TextView movieId = (TextView) this.findViewById(R.id.movie_id);
        movieId.setText(Integer.toString(mDetails.getId()));

        TextView originalTitle = (TextView) this.findViewById(R.id.original_title);
        originalTitle.setText(mDetails.getOriginalTitle());

        TextView overview = (TextView) this.findViewById(R.id.overview);
        overview.setText(mDetails.getOverview());

        TextView releaseDate = (TextView) this.findViewById(R.id.release_date);
        releaseDate.setText("Release date: " + mDetails.getReleaseDate());

        TextView popularity = (TextView) this.findViewById(R.id.popularity);
        popularity.setText("Popularity = " + Double.toString(mDetails.getPopularity()));

        TextView rating = (TextView) this.findViewById(R.id.rating);
        rating.setText("Rating (votes) = " + Double.toString(mDetails.getVoteAverage()));

        ButtonListener buttonListener = new ButtonListener(this);
        mReturnButton = (Button) this.findViewById(R.id.return_to_catalog);
        mReturnButton.setOnClickListener(buttonListener);

        mPlayButton = (Button) this.findViewById(R.id.play_trailer);
        mPlayButton.setOnClickListener(buttonListener);

        mFavoriteButton = (Button) this.findViewById(R.id.favorite);
        mFavoriteButton.setOnClickListener(buttonListener);

    } // onCreate( Bundle )

    public MovieDetails getDetails() {
        return mDetails;
    } // getDetails()

    public int getMoveId() {
        return mMovieId;
    } // getMovieId()

    public Button getFavoriteButton() {
        return mFavoriteButton;
    } // getFavoriteButton()

    public Button getPlayButton() {
        return mPlayButton;
    } // getPlayButton()

    public Button getReturnButton() {
        return mReturnButton;
    } // getReturnButton()

} // DescriptionActivity
