package com.spencerstock.gnocchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.GifBuilder.GifBuilderDao;
import com.spencerstock.gnocchi.ImageProperties.MyLayoutInflater;
import com.spencerstock.gnocchi.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class DetailActivity extends AppCompatActivity {

    GridLayout        gridLayout;
    ArrayList<Bitmap> images;
    String            gnocchiTitle;
    int               screenWidth;
    int               screenHeight;
    Context           context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        context = this;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        gridLayout = findViewById(R.id.parent_gridLayout);

        Intent intent = getIntent();
        gnocchiTitle = intent.getStringExtra(MyLayoutInflater.TITLE);
        images = BitmapFileDao.getGnocchi(this, gnocchiTitle);



        for (final Bitmap image: images) {
            ImageView temp = new ImageView(this);
            temp.setImageBitmap(image);

            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/*                    ByteArrayOutputStream bStream = new ByteArrayOutputStream(); //crashes app, byte array too large to be passed as intent
                    image.compress(Bitmap.CompressFormat.PNG, 10, bStream);
                    byte[] byteArray = bStream.toByteArray();

                    Intent anotherIntent = new Intent(context, EnlargedViewSingleImage.class);
                    anotherIntent.putExtra("imageName", byteArray);
                    startActivity(anotherIntent);
                    finish();*/
                }
            });

            gridLayout.addView(temp);
            temp.getLayoutParams().width = screenWidth/3;
            temp.getLayoutParams().height = (int)((image.getHeight()) * ((double)(screenWidth/3)/image.getWidth()));
        }


        GifDrawable gifDrawable = GifBuilderDao.generateGIF(images);
        ImageView gifImageView = findViewById(R.id.gif_image_view);
        gifImageView.setBackground(gifDrawable);
        gifDrawable.setLoopCount(0);
        gifDrawable.start();
        gifImageView.getLayoutParams().width = screenWidth;

    }
}
