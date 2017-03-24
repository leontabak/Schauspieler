package com.eonsahead.schauspieler;


import java.util.ArrayList;
import java.util.List;

public class MovieQueries {
    private static MovieQueries mMovieQueries;
    public int mCurrentIndex;
    private final List<String> mIMDBnumbers;
    private final String mTheMovieDBKey;

    private MovieQueries() {
        mTheMovieDBKey = "abracabra";

        mIMDBnumbers = new ArrayList<>();
        mCurrentIndex = 0;

        // 2001: A Space Odyssey
        mIMDBnumbers.add(makeURL("tt062622"));

        // Apollo 13
        mIMDBnumbers.add(makeURL("tt0112384"));

        // Beautiful Mind
        mIMDBnumbers.add(makeURL("tt0268978"));

        // Desk Set
        mIMDBnumbers.add(makeURL("tt0050307"));

        // Forbidden Planet
        mIMDBnumbers.add(makeURL("tt0049223"));

        // Imitation Game
        mIMDBnumbers.add(makeURL("tt2084970"));

        // Live Free or Die Hard
        mIMDBnumbers.add(makeURL("tt0337978"));

        // Lives of Others
        mIMDBnumbers.add(makeURL("tt0405094"));

        // Man Who Knew Infinity
        mIMDBnumbers.add(makeURL("tt0787524"));

        // Sneakers
        mIMDBnumbers.add(makeURL("tt0105435"));

        // Star Trek IV
        mIMDBnumbers.add(makeURL("tt0092007"));

    } // MovieQueries()

    private final String makeURL(String imdbNumber) {
        String result = "https://api.themoviedb.org/3/find/" +
                imdbNumber +
                "?api_key=" +
                mTheMovieDBKey +
                "&language=en-US&external_source=imdb_id";

        return result;
    } // makeURL( String )

    public MovieQueries getInstance() {
        if (mMovieQueries == null) {
            mMovieQueries = new MovieQueries();
        } // if
        return mMovieQueries;
    } // getInstance()

    public List<String> get() {
        return mIMDBnumbers;
    } // get()

    public String get(int index) {
        return mIMDBnumbers.get(index);
    } // get( int )

    public String getNextIMDBnumber() {
        String result = mIMDBnumbers.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mIMDBnumbers.size();
        return result;
    } // getNextIMDBnumber()

    public int size() {
        return mIMDBnumbers.size();
    } // size()
} // MovieQueries
