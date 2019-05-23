package com.spencerstock.gnocchi.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.FileIO.ImageSorter;
import com.spencerstock.gnocchi.GifBuilder.GifBuilderDao;
import com.spencerstock.gnocchi.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class DetailView extends AppCompatActivity {

    GridLayout gridLayout;
    ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        gridLayout = findViewById(R.id.parent_gridLayout);

        images = BitmapFileDao.getImages(this);



        for (Bitmap image: images) {
            ImageView temp = new ImageView(this);
            temp.setImageBitmap(image);
            //temp.setAdjustViewBounds(true);
            temp.setMaxHeight(250);
            temp.setMaxWidth(250);
            gridLayout.addView(temp);
        }


        GifDrawable gifDrawable = GifBuilderDao.generateGIF(images);
        GifImageView gifImageView = new GifImageView(this);
        gifImageView.setBackground(gifDrawable);
        gifDrawable.setLoopCount(0);
        gifDrawable.start();
        gridLayout.addView(gifImageView);


    }
}
