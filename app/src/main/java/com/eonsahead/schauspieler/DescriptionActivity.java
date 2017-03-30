package com.eonsahead.schauspieler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DescriptionActivity extends AppCompatActivity {
    private static final String INTENT_ID = "DETAILS";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_description);

        Intent intent = getIntent();
        MovieDetails details = (MovieDetails) intent.getExtras().getSerializable(INTENT_ID);

        TextView originalTitle = (TextView) this.findViewById(R.id.original_title);
        originalTitle.setText(details.getOriginalTitle());

        TextView overview = (TextView) this.findViewById(R.id.overview);
        overview.setText(details.getOverview());

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
