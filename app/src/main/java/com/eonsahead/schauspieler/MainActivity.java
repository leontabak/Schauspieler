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

    private RecyclerView mMovies;
    private KinoAdapter mAdapter;
    private MovieDB mMovieDB;
    private SortCriterion mSortCriterion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Guten Tag!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSortCriterion = SortCriterion.POPULARITY;

        mMovieDB = new MovieDB();

        (new MoviesDescriptionsTask()).execute( this );

        mMovies = (RecyclerView) this.findViewById(R.id.selected_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovies.setLayoutManager(layoutManager);

        mMovies.setHasFixedSize(true);

        mAdapter = new KinoAdapter(mMovieDB.select(mSortCriterion));
        mMovies.setAdapter(mAdapter);
    } // onCreate( Bundle )

    public MovieDB getMovieDB() {
        Log.d(TAG, "return mMovieDB");
        return mMovieDB;
    } // getMovieDB()

    public SortCriterion getSortCriterion() {
        return mSortCriterion;
    } // getSortCriterion()

    public void setSortCriterion( SortCriterion criterion ) {
        mSortCriterion = criterion;
    } // setSortCriterion( SortCriterion )

    public void refresh() {
        mAdapter.changeData(mMovieDB.select(mSortCriterion));
//        mMovies.setAdapter(mAdapter);
    } // refresh()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } // onCreateOptions( Menu )

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.byFavorite:
                setSortCriterion(SortCriterion.FAVORITE);
                this.refresh();
                break;
            case R.id.byPopularity:
                setSortCriterion(SortCriterion.POPULARITY);
                this.refresh();
                break;
            case R.id.byRating:
                setSortCriterion(SortCriterion.RATING);
                this.refresh();
                break;
            default:
                break;
        } // switch

        return super.onOptionsItemSelected(menuItem);
    } // onOptionsItemSelected( MenuItem )

} // MainActivity

