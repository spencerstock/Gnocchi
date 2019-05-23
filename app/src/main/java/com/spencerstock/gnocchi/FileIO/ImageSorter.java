package com.spencerstock.gnocchi.FileIO;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.spencerstock.gnocchi.ImageProperties.GnocchiOverview;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSorter {

    public static ArrayList<GnocchiOverview> sortImages(File[] images) {

        ArrayList<GnocchiOverview> overviews = new ArrayList<>();
        boolean newGnocchi = true;

        for (File image : images) {

            String fileTitle = "";
            String fileName = image.getName(); //creates a regex for finding the gnocchi name
            Pattern pattern = Pattern.compile("_(.*?)_");
            Matcher matcher = pattern.matcher(fileName);
            if (matcher.find()) {
                fileTitle = matcher.group(1);
            }


            for (int i = 0; i < overviews.size(); ++i) { //if a new gnocchi is found, create an overview
                if (!fileTitle.equals("") && fileTitle.equals(overviews.get(i).getTitle())) {
                    newGnocchi = false;
                    overviews.get(i).setSize(overviews.get(i).getSize()+1);
                }
            }
            if (newGnocchi) {
                GnocchiOverview temp = new GnocchiOverview();
                temp.setTitle(fileTitle);
                temp.setFirstFrame(BitmapFactory.decodeFile(image.getPath()));
                temp.setSize(1);
                //TODO: add gif preview if available
                overviews.add(temp);
            }
        }
        return overviews;
    }
}
