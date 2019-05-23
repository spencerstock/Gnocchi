package com.spencerstock.gnocchi.FileIO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
    private static final int REQUEST_IMAGE_CODE = 123;


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

    public static void dispatchTakePictureIntent(Context context, int imageNumber) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = BitmapFileDao.createImageFile(context, "TestGroup2", imageNumber);
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(context,
                        "com.spencerstock.gnocchi.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                ((Activity)context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CODE);
            }
        }
    }

}
