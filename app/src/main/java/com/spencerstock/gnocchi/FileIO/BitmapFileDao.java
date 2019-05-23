package com.spencerstock.gnocchi.FileIO;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BitmapFileDao {


    //static String currentPhotoPath;


    public static File createImageFile(Context context, String groupName, int imgNumber) throws IOException {
        // Create an image file name
        String imageFileName = "JPEG_" + groupName + "_" + imgNumber;
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static ArrayList<Bitmap> getImages(Context context) {


        ArrayList<Bitmap> images = new ArrayList<>();
        //String path = context.getFilesDir().toString();
        String path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        for (
                int i = 0;
                i < files.length; i++) {
            Log.d("Files", "FileName:" + files[i].getName());
        }

        for (File file: files) {
            if (file.getName().toLowerCase().endsWith("jpg")) {
                images.add(BitmapFactory.decodeFile(file.getPath()));
            }
        }
        return images;
    }

}
