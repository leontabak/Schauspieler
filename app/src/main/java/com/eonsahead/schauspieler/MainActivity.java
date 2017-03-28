package com.eonsahead.schauspieler;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mMovieView;
    private SourceOfImages src = SourceOfImages.NET;

    private RecyclerView mMovies;
    private KinoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovies = (RecyclerView) this.findViewById(R.id.favorite_movies);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mMovies.setLayoutManager(layoutManager);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mMovies.setLayoutManager(layoutManager);

        mMovies.setHasFixedSize(true);
//        mAdapter = new KinoAdapter(MovieAbbreviations.getInstance().getAbbreviations().size());
        mAdapter = new KinoAdapter(MovieQueries.getInstance().size());
        mMovies.setAdapter( mAdapter );

        MovieDB movieDB = MovieDB.getInstance();
        movieDB.update();

//        final APIKey api = APIKey.getInstance();

//        GridView gridView = (GridView) this.findViewById(R.id.gridview);
//        gridView.setAdapter(new ImageAdapter(this));
//
//        gridView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(MainActivity.this,
//                                MainActivity.this.getString(R.string.selected) + api.getKey(),
//                                Toast.LENGTH_LONG).show();
//                    } // onItemClick()
//                } // OnItemClickListener()
//        );

//        mMovieView = (TextView) this.findViewById(R.id.favorite_movies);

//        for (String name : movieNames) {
//            mMovieView.append(name + "\n\n\n");
//        } // for
    } // onCreate( Bundle )

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
            } // if
            else if (menuItem.getTitle().equals(popularity)) {
                menuItem.setTitle(rating);
            } // else if
        } // if
        return super.onOptionsItemSelected(menuItem);
    } // onOptionsItemSelected( MenuItem )

} // MainActivity

