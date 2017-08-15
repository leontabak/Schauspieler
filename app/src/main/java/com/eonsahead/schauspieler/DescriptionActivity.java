package com.eonsahead.schauspieler;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {
    private static final String TAG = "DescriptionActivity";
    private static final String INTENT_ID = "DETAILS";
    private MovieDetails mDetails;
    private int mMovieId;
    private String mPosterPath;

    private Picasso mPicasso;
    private Button mFavoriteButton;
    private Button mPlayButton;
    private Button mReturnButton;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "DescriptionActivity: onCreate (0)");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_description);

        Log.d(TAG, "DescriptionActivity: onCreate (1)");

        if (Mode.ONLINE) {
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
        } // if

        Intent intent = getIntent();
        mDetails = (MovieDetails) intent.getExtras().getSerializable(INTENT_ID);

        mPosterPath = mDetails.getPosterPath();
        mMovieId = mDetails.getId();

        ImageView imageView = (ImageView) this.findViewById(R.id.thumbnail);


        if (Mode.ONLINE) {
            mPicasso.with(this).load(mPosterPath)
                    .placeholder(R.drawable.arboretum04)
                    .error(R.drawable.arboretum08)
                    .resize(384, 576)
                    .into(imageView, new PicassoErrorHandler(this));
        } // if
        else {
            imageView.setImageResource(R.drawable.arboretum04);
        } // else

        Resources resources = getResources();

        String movieIdString = Integer.toString(mDetails.getId());
        String originalTitleString = mDetails.getOriginalTitle();
        String overviewString = mDetails.getOverview();
        String releaseDateString = String.format(resources.getString(R.string.releaseDate),
                mDetails.getReleaseDate());
        String popularityString = String.format(resources.getString(R.string.popularityReport),
                mDetails.getPopularity());
        String ratingsString = String.format(resources.getString(R.string.ratingsReport),
                mDetails.getVoteAverage());

        TextView movieId = (TextView) this.findViewById(R.id.movie_id);
        movieId.setText(movieIdString);

        TextView originalTitle = (TextView) this.findViewById(R.id.original_title);
        originalTitle.setText(originalTitleString);

        TextView overview = (TextView) this.findViewById(R.id.overview);
        overview.setText(overviewString);

        TextView releaseDate = (TextView) this.findViewById(R.id.release_date);
        releaseDate.setText(releaseDateString);

        TextView popularity = (TextView) this.findViewById(R.id.popularity);
        popularity.setText(popularityString);

        // for debugging purposes only
        TextView isPopular = (TextView) this.findViewById(R.id.is_popular);
        if (mDetails.getIsPopular()) {
            isPopular.setText("This movie is on the list of the most popular movies!");
        } // if
        else {
            isPopular.setText("This movie is not on the list of most popular movies.");
        } // else

        TextView rating = (TextView) this.findViewById(R.id.rating);
        rating.setText(ratingsString);

        // for debugging purposes only
        TextView isRated = (TextView) this.findViewById(R.id.is_rated);
        if (mDetails.getIsHighlyRated()) {
            isRated.setText("This movie is on the list of most highly rated movies!");
        } // if
        else {
            isRated.setText("This movie is not on the list of most highly rated movies.");
        } // else

        // for debugging purposes only
        TextView isFavorite = (TextView) this.findViewById(R.id.is_favorite);
        if (mDetails.getIsFavorite()) {
            isFavorite.setText("This movie is one of my favorites!");
        } // if
        else {
            isFavorite.setText("This movie is not one of my favorites.");
        } // else

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
