package com.spencerstock.gnocchi.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.spencerstock.gnocchi.R;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {

    GridLayout gridLayout;
    ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        gridLayout = findViewById(R.id.parent_gridLayout);



        for (Bitmap image: images) {
            ImageView temp = new ImageView(this);
            temp.setImageBitmap(image);
            //temp.setMaxHeight(250);
            //temp.setMaxWidth(250);
            gridLayout.addView(temp);
        }


    }
}
