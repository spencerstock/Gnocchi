package com.spencerstock.gnocchi.FileIO;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.spencerstock.gnocchi.ImageProperties.GnocchiOverview;

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
        File image = new File(storageDir, groupName + "_" + imgNumber + ".jpg");
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
        for (File file : files) {
            Log.d("Files", "FilesName:" + file.getName());
            if (file.getName().toLowerCase().endsWith("jpg")) {
                images.add(BitmapFactory.decodeFile(file.getPath()));
            }
        }
        return images;
    }

    public static ArrayList<GnocchiOverview> getGnocchies(Context context) {
        ArrayList<GnocchiOverview> overviews;

        String path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files", "Size: " + files.length);
        return ImageSorter.sortImages(files);

    }

}
