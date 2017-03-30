package com.eonsahead.schauspieler;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KinoAdapter extends RecyclerView.Adapter<KinoViewHolder> {
    private static final String TAG = KinoAdapter.class.getSimpleName();
    private int mNumberOfItems;
    private MovieQueries movieQueries = MovieQueries.getInstance();
    private MovieDB mMovieDB;

    public KinoAdapter(MovieDB movieDB) {
        mMovieDB = movieDB;
        mNumberOfItems = mMovieDB.size();
    } // KinoAdapter( int )

    @Override
    public KinoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutID = R.layout.movie_poster;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        View view = inflater.inflate(layoutID, viewGroup, shouldAttach);
//        view.setOnClickListener(new ImageClickListener(context));
        KinoViewHolder viewHolder = new KinoViewHolder(context, view, mMovieDB);
        return viewHolder;
    } // onCreateViewHolder( ViewGroup, int )

    @Override
    public void onBindViewHolder(KinoViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position, mMovieDB);
    } // OnBindViewHolder( KinoViewHolder, int )

    @Override
    public int getItemCount() {
        return mNumberOfItems;
    } // getItemCount()

} // KinoAdapter
