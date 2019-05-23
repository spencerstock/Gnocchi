package com.spencerstock.gnocchi.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.ImageProperties.GnocchiOverview;
import com.spencerstock.gnocchi.ImageProperties.MyLayoutInflater;
import com.spencerstock.gnocchi.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button  buttonNewGnocchi;
    Button  buttonAddPhoto;
    Button  button_test_gnocchi;
    ArrayList<GnocchiOverview> gnocchiOverviews;
    LinearLayout linearLayoutParent;
    int     imageNumber = 0;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddPhoto = findViewById(R.id.button_add_photo);
        buttonNewGnocchi = findViewById(R.id.button_new);
        button_test_gnocchi = findViewById(R.id.button_test_gnocchi);
        linearLayoutParent = findViewById(R.id.parent_linearLayout);

        context = this;

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





        gnocchiOverviews = BitmapFileDao.getGnocchies(this);



        MyLayoutInflater myLayoutInflater = new MyLayoutInflater(this, gnocchiOverviews);


        for (int i = 0; i < gnocchiOverviews.size(); ++i) {
            View view = myLayoutInflater.getView(i, null, null);
            linearLayoutParent.addView(view);
        }


        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFileDao.dispatchTakePictureIntent(context, imageNumber++);
            }
        });




    }




}



