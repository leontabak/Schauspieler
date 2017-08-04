package com.eonsahead.schauspieler;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class TrailerAndReviewsTask
        extends AsyncTask<DescriptionActivity, Void, DescriptionActivity> {

    private final String TAG = "TrailerAndReviewsTask";
    private final String REVIEWS = "reviews";
    private final String VIDEOS = "videos";

    // Edit the following line. Insert your key for the themoviedb.org online
    // database on the right-hand side of the assignment in place of my call
    // to APIKey.getInstance().getKey().
    private final String theMovieDBKey = APIKey.getInstance().getKey();
    private MovieDetails mDetails;

    // https://api.themoviedb.org/3/movie/[ID-FROM-MOVIE]/videos?api-key='API-KEY'&
    // language=en-US

    @Override
    protected DescriptionActivity doInBackground(DescriptionActivity... params) {
        DescriptionActivity descriptionActivity = params[0];
        mDetails = descriptionActivity.getDetails();

        int movieId = descriptionActivity.getMoveId();

        query(movieId, REVIEWS);
        query(movieId, VIDEOS);

        return descriptionActivity;
    } // doInBackground()

    private void query(int movieId, String dataType) {
        URL url = makeURL(movieId, dataType);
        Log.d(TAG, "url = " + url);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            String text = "";
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                text = scanner.next();
            } // if
            scanner.close();

            try {
                JSONObject object = new JSONObject(text.toString());
                JSONArray array = object.getJSONArray("results");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject movie = array.getJSONObject(i);

                    if (dataType.equals(REVIEWS)) {
                        // fetch a review of the movie
                        String author = movie.getString("author");
                        String content = movie.getString("content");

                        Review review = new Review(author, content);
                        mDetails.addReview(review);

                        Log.d(TAG, "author = " + author);
                        Log.d(TAG, "content = " + content);
                    } // if
                    else if (dataType.equals(VIDEOS)) {
                        // fetch a trailer or featurette
                        String name = movie.getString("name");
                        String key = movie.getString("key");
                        String type = movie.getString("type");

                        Trailer trailer = new Trailer(name, key, type);
                        mDetails.addTrailer(trailer);

                        Log.d(TAG, "name = " + name);
                        Log.d(TAG, "key = " + key);
                        Log.d(TAG, "type = " + type);
                    } // else if

                } // for
            } // try
            catch (JSONException e) {
                e.printStackTrace();
            } // catch( JSONException )

            is.close();
        } // try
        catch (IOException e) {
            e.printStackTrace();
        } // catch( IOException )
        finally {
            connection.disconnect();
        } // finally
    } // query( int, String )

    @Override
    protected void onPostExecute(DescriptionActivity descriptionActivity) {
//        mainActivity.refresh();
    } // onPostExecute( DescriptionActivity )

    private final URL makeURL(int movieId, String dataType) {
        String result = "https://api.themoviedb.org/3/movie/" +
                movieId +
                "/" + dataType +
                "?api_key=" +
                theMovieDBKey +
                "&language=en-US";

        URL url = null;
        try {
            url = new URL(result);
        } // try
        catch (MalformedURLException e) {
            e.printStackTrace();
        } // catch( MalformedURLException )

        return url;
    } // makeURL( int, String )

} // TrailerAndReviewsTask
