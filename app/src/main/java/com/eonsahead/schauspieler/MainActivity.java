package com.eonsahead.schauspieler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mMovieView;
    private SourceOfImages src = SourceOfImages.NET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final APIKey api = APIKey.getInstance();

        GridView gridView = (GridView) this.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this,
                                MainActivity.this.getString(R.string.selected) + api.getKey(),
                                Toast.LENGTH_LONG).show();
                    } // onItemClick()
                } // OnItemClickListener()
        );

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

