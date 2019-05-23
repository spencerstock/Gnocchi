package com.spencerstock.gnocchi.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spencerstock.gnocchi.FileIO.BitmapFileDao;
import com.spencerstock.gnocchi.R;

public class CreateGnocchi extends AppCompatActivity {

    EditText gnocchiNameEditText;
    Button   completeButton;
    Context  context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_gnocchi);
        context = this;

        gnocchiNameEditText = findViewById(R.id.editText_gnocchi_name);
        completeButton = findViewById(R.id.buttom_complete_new_gnocchi);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapFileDao.dispatchTakePictureIntent(context, 0, gnocchiNameEditText.getText().toString());
                finish();
            }
        });


    }
}
