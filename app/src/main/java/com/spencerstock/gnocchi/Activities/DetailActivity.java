package com.spencerstock.gnocchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.GifBuilder.GifBuilderDao;
import com.spencerstock.gnocchi.ImageProperties.MyLayoutInflater;
import com.spencerstock.gnocchi.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

public class DetailActivity extends AppCompatActivity {

    GridLayout        gridLayout;
    ArrayList<Bitmap> images;
    String            gnocchiTitle;
    int               screenWidth;
    int               screenHeight;
    Context           context;
    ProgressBar       progressBar;
    Button            refactorGifButton;
    SeekBar           gifSpeedSeekbar;
    SeekBar           firstFrameDelay;
    SeekBar           lastFrameDelay;
    boolean           refactor = false;
    ImageView         gifImageView;


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

        gifImageView = findViewById(R.id.gif_image_view);
        firstFrameDelay = findViewById(R.id.gif_first_frame_seekbar);
        lastFrameDelay = findViewById(R.id.gif_last_frame_seekbar);
        gifSpeedSeekbar = findViewById(R.id.gif_speed_seekbar);
        gridLayout = findViewById(R.id.parent_gridLayout);
        progressBar = findViewById(R.id.progressBar_cyclic);
        refactorGifButton = findViewById(R.id.button_refactor_gif);
        refactorGifButton.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        gnocchiTitle = intent.getStringExtra(MyLayoutInflater.TITLE);
        images = BitmapFileDao.getGnocchi(this, gnocchiTitle);


        for (final Bitmap image : images) {
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
            temp.getLayoutParams().width = screenWidth / 3;
            temp.getLayoutParams().height = (int) ((image.getHeight()) * ((double) (screenWidth / 3) / image.getWidth()));
        }
        generateGif(200, 0, 0);


        refactorGifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                gifImageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                int speed = 1000 - gifSpeedSeekbar.getProgress();
                int firstFrameDelayInt = firstFrameDelay.getProgress();
                int lastFrameDelayInt = lastFrameDelay.getProgress();

                generateGif(speed, firstFrameDelayInt, lastFrameDelayInt);
            }
        });
    }


    private void generateGif(final int delayms, final int lastFrameDelay, final int firstFrameDelay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final GifDrawable gifDrawable = GifBuilderDao.generateGIF(images, gnocchiTitle, delayms, lastFrameDelay, firstFrameDelay);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        refactorGifButton.setVisibility(View.VISIBLE);
                        gifImageView.setVisibility(View.VISIBLE);

                        gifImageView.setBackground(gifDrawable);
                        gifDrawable.setLoopCount(0);
                        gifDrawable.start();
                        gifImageView.getLayoutParams().width = screenWidth;


                        if (refactor) {
                            Toast.makeText(context, "Gif refactored", Toast.LENGTH_SHORT).show();
                        }
                        if (!refactor) refactor = true;
                    }
                });

            }
        }).start();
    }
}
