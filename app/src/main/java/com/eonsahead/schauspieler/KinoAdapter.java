package com.eonsahead.schauspieler;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KinoAdapter extends RecyclerView.Adapter<KinoViewHolder> {
    private static final String TAG = KinoAdapter.class.getSimpleName();
    private int numberOfItems;

    public KinoAdapter(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    } // KinoAdapter( int )

    @Override
    public KinoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutID = 0;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttach = false;
        View view = inflater.inflate(layoutID, viewGroup, shouldAttach);
        KinoViewHolder viewHolder = new KinoViewHolder(view);
        return viewHolder;
    } // onCreateViewHolder( ViewGroup, int )

    @Override
    public void onBindViewHolder( KinoViewHolder holder, int position ) {
        Log.d( TAG, "#" + position );
        holder.bind( position );
    } // OnBindViewHolder( KinoViewHolder, int )

    @Override
    public int getItemCount() {
        return numberOfItems;
    } // getItemCount()

} // KinoAdapter
