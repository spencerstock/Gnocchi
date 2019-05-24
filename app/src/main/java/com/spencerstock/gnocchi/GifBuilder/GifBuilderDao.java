package com.spencerstock.gnocchi.GifBuilder;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

public class GifBuilderDao {


    public static GifDrawable generateGIF(ArrayList<Bitmap> images, String gnocchiName, int delayMs, int firstFrameDelay, int lastFrameDelay) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GifBuilder encoder = new GifBuilder();
        encoder.start(bos);
        for (int i = 0; i < images.size(); ++i) {
            if (i == 0) encoder.setDelay(delayMs+firstFrameDelay);
            else if (i == images.size()-1) encoder.setDelay(delayMs+lastFrameDelay);
            else encoder.setDelay(delayMs);
            encoder.addFrame(images.get(i));
        }
        encoder.finish();

        GifDrawable gifDrawable = byteArrayToGif(bos.toByteArray());

        return gifDrawable;
    }


    private static GifDrawable byteArrayToGif(byte[] byteArray) {


        GifDrawable gif = null;
        try {
            gif = new GifDrawable(byteArray);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return gif;
    }


}
