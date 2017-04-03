package com.eonsahead.schauspieler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mMovieView;

    private RecyclerView mMovies;
    private KinoAdapter mAdapter;
    private MovieDB mMovieDB;
    private SortCriterion mSortCriterion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieDB = new MovieDB();

        mSortCriterion = SortCriterion.VOTES;
        (new MoviesDescriptionsTask()).execute( this );

        mMovies = (RecyclerView) this.findViewById(R.id.favorite_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovies.setLayoutManager(layoutManager);

        mMovies.setHasFixedSize(true);

        refresh();
    } // onCreate( Bundle )

    public MovieDB getMovieDB() {
        return mMovieDB;
    } // getMovieDB()

    public SortCriterion getSortCriterion() {
        return mSortCriterion;
    } // getSortCriterion()

    public void setSortCriterion( SortCriterion criterion ) {
        mSortCriterion = criterion;
    } // setSortCriterion( SortCriterion )

    public void refresh() {
        mAdapter = new KinoAdapter(mMovieDB);
        mMovies.setAdapter(mAdapter);
    } // refresh()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } // onCreateOptions( Menu )

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        String popularity = getString(R.string.byPopularity);
        String rating = getString(R.string.byRating);

        if (id == R.id.sortingCriterion) {
            if (menuItem.getTitle().equals(rating)) {
                menuItem.setTitle(popularity);
                setSortCriterion(SortCriterion.POPULARITY);
                (new MoviesDescriptionsTask()).execute( this );
            } // if
            else if (menuItem.getTitle().equals(popularity)) {
                menuItem.setTitle(rating);
                setSortCriterion(SortCriterion.VOTES);
                (new MoviesDescriptionsTask()).execute( this );
            } // else if
        } // if
        return super.onOptionsItemSelected(menuItem);
    } // onOptionsItemSelected( MenuItem )

} // MainActivity

