package com.eonsahead.schauspieler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mMovieView;
    private SourceOfImages src = SourceOfImages.NET;

    private RecyclerView mMovies;
    private KinoAdapter mAdapter;
    private MovieDB mMovieDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieDB = new MovieDB(MovieQueries.getInstance(), this);

        mMovies = (RecyclerView) this.findViewById(R.id.favorite_movies);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovies.setLayoutManager(layoutManager);

        mMovies.setHasFixedSize(true);

        mMovieDB.update();
        mMovieDB.sort(SortCriterion.VOTES);
        refresh();
    } // onCreate( Bundle )

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

        String popularity = this.getString(R.string.byPopularity);
        String rating = this.getString(R.string.byRating);

        if (id == R.id.sortingCriterion) {
            if (menuItem.getTitle().equals(rating)) {
                menuItem.setTitle(popularity);
                mMovieDB.sort(SortCriterion.POPULARITY);
                refresh();
            } // if
            else if (menuItem.getTitle().equals(popularity)) {
                menuItem.setTitle(rating);
                mMovieDB.sort(SortCriterion.VOTES);
                refresh();
            } // else if
        } // if
        return super.onOptionsItemSelected(menuItem);
    } // onOptionsItemSelected( MenuItem )

} // MainActivity

