package com.eonsahead.schauspieler;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class KinoViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = KinoViewHolder.class.getSimpleName();

    private TextView view;

    public KinoViewHolder( View itemView ) {
        super( itemView );
//        this.view = (TextView) itemView.findViewById(R.id.favorite_movies);
    } // KinoViewHolder( View )

    public void bind( int index ) {
        view.setText(String.valueOf(index));
    } // bind( int )
} // KinoViewHolder
