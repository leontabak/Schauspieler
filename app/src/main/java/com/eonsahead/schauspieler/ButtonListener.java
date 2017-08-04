package com.eonsahead.schauspieler;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by LTabak on 7/28/2017.
 */

public class ButtonListener implements OnClickListener {
    private DescriptionActivity activity;
    private boolean favorite;

    public ButtonListener(DescriptionActivity activity) {
        this.activity = activity;
        this.favorite = true;
    } // ButtonListener( AppCompatActivity )

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.return_to_catalog:
                this.activity.finish();
                break;
            case R.id.play_trailer:
//                Toast.makeText( this.activity.getApplication(), "Hello", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("https://youtu.be/X1UmHfWCw-4"), "view/*");

//                this.activity.startActivity(intent);

                if (intent.resolveActivity(this.activity.getPackageManager()) == null) {
                    Toast.makeText(this.activity.getApplication(), "Still No activity?", Toast.LENGTH_LONG).show();
                } // if
                else {
                    Toast.makeText(this.activity.getApplication(), "Start activity?", Toast.LENGTH_LONG).show();
                    this.activity.startActivity(intent);
                } // else
                break;
            case R.id.favorite:
                Button button = this.activity.getFavoriteButton();
                if (favorite) {
                    button.setText(R.string.unfavorite);
                    button.setBackgroundResource(R.color.unfavorite);
                    favorite = false;
                } // if
                else {
                    button.setText(R.string.favorite);
                    button.setBackgroundResource(R.color.favorite);
                    favorite = true;
                }
                break;
            default:
                break;
        } // switch

    } // onClick( View )
} // ButtonListener
