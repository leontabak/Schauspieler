package com.eonsahead.schauspieler;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class KinoAdapter extends RecyclerView.Adapter<KinoViewHolder> {
    private static final String TAG = KinoAdapter.class.getSimpleName();
    private int numberOfItems;
    private MovieQueries movieQueries = MovieQueries.getInstance();

    public KinoAdapter(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    } // KinoAdapter( int )

    @Override
    public KinoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutID = R.layout.one_movie;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        View view = inflater.inflate(layoutID, viewGroup, shouldAttach);
//        view.setOnClickListener(new ImageClickListener(context));
        KinoViewHolder viewHolder = new KinoViewHolder(context, view);
        return viewHolder;
    } // onCreateViewHolder( ViewGroup, int )

    @Override
    public void onBindViewHolder(KinoViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    } // OnBindViewHolder( KinoViewHolder, int )

    @Override
    public int getItemCount() {
        return numberOfItems;
    } // getItemCount()

} // KinoAdapter
