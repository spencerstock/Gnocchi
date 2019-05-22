package com.spencerstock.gnocchi.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CODE = 123;
    Button buttonNewGnocchi;
    Button buttonAddPhoto;
    Button button_test_gnocchi;
    int    imageNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddPhoto = findViewById(R.id.button_add_photo);
        buttonNewGnocchi = findViewById(R.id.button_new);
        button_test_gnocchi = findViewById(R.id.button_test_gnocchi);


        button_test_gnocchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailView.class);
                startActivity(i);
            }
        });

        buttonNewGnocchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateGnocchi.class);
                startActivity(i);
            }
        });



        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE);
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            try {
                BitmapFileDao.saveImage(this, imageBitmap, "TestGroup", ++imageNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
