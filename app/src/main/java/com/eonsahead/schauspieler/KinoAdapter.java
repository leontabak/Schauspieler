package com.eonsahead.schauspieler;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KinoAdapter extends RecyclerView.Adapter<KinoViewHolder> {
    private static final String TAG = "KinoAdapter";

    private int mNumberOfItems;
    private MovieDB mMovieDB;

    public KinoAdapter(MovieDB movieDB) {
        mMovieDB = new MovieDB();
        for (MovieDetails details : movieDB.getRecords()) {
            mMovieDB.addRecord(details);
        } // for

        if( mMovieDB.size() > 0 ) {
            mNumberOfItems = mMovieDB.size();
        } // if
        else {
            mNumberOfItems = 12; // mMovieDB.size();
        } // else
    } // KinoAdapter( int )

    public void changeData(MovieDB movieDB) {
        mMovieDB.clear();
        for (MovieDetails details : movieDB.getRecords()) {
            mMovieDB.addRecord(details);
        } // for

        mNumberOfItems = mMovieDB.size();
        this.notifyDataSetChanged();
    } // changeData( MovieDB )

    @Override
    public KinoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutID = R.layout.movie_poster;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        View view = inflater.inflate(layoutID, viewGroup, shouldAttach);
        KinoViewHolder viewHolder = new KinoViewHolder(context, view, mMovieDB);
        return viewHolder;
    } // onCreateViewHolder( ViewGroup, int )

    @Override
    public void onBindViewHolder(KinoViewHolder holder, int position) {
        holder.bind(position );
    } // OnBindViewHolder( KinoViewHolder, int )

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    } // getItemCount()

} // KinoAdapter
