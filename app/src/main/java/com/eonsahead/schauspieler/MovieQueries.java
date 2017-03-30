package com.eonsahead.schauspieler;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieQueries {
    private static MovieQueries mMovieQueries = null;
    public int mCurrentIndex;
    private final List<URL> mURLs;
    private final String mTheMovieDBKey;

    private MovieQueries() {
        // Assign your key to themoviedb.org service here.
        mTheMovieDBKey = APIKey.getInstance().getKey();

        mURLs = new ArrayList<>();
        mCurrentIndex = 0;

        // 2001: A Space Odyssey
        mURLs.add(makeURL("tt0062622"));

        // Apollo 13
        mURLs.add(makeURL("tt0112384"));

        // Arranged
        mURLs.add(makeURL("tt0848542"));

        // Beautiful Mind
        mURLs.add(makeURL("tt0268978"));

        // Desk Set
        mURLs.add(makeURL("tt0050307"));

        // Forbidden Planet
        mURLs.add(makeURL("tt0049223"));

        // Imitation Game
        mURLs.add(makeURL("tt2084970"));

        // Live Free or Die Hard
        mURLs.add(makeURL("tt0337978"));

        // Lives of Others
        mURLs.add(makeURL("tt0405094"));

        // Man Who Knew Infinity
        mURLs.add(makeURL("tt0787524"));

        // Sneakers
        mURLs.add(makeURL("tt0105435"));

        // Star Trek IV
        mURLs.add(makeURL("tt0092007"));

    } // MovieQueries()

    private final URL makeURL(String imdbNumber) {
        String result = "https://api.themoviedb.org/3/find/" +
                imdbNumber +
                "?api_key=" +
                mTheMovieDBKey +
                "&language=en-US&external_source=imdb_id";

        URL url = null;
        try {
            url = new URL(result);
        } // try
        catch( MalformedURLException e ) {
            e.printStackTrace();
        } // catch( MalformedURLException )

        return url;
    } // makeURL( String )

    public static MovieQueries getInstance() {
        if (mMovieQueries == null) {
            mMovieQueries = new MovieQueries();
        } // if
        return mMovieQueries;
    } // getInstance()

    public List<URL> getURLs() {
        return mURLs;
    } // getURLs()

    public URL getURLs(int index) {
        return mURLs.get(index);
    } // getURLs( int )

    public URL getURL() {
        URL result = mURLs.get( mCurrentIndex );
        mCurrentIndex = (mCurrentIndex + 1) % mURLs.size();
        return result;
    } // getNextURL()

    public int size() {
        return mURLs.size();
    } // size()
} // MovieQueries
