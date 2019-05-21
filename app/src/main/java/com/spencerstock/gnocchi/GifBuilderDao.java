package com.spencerstock.gnocchi;

import android.graphics.Bitmap;

import com.spencerstock.gnocchi.GifBuilder.GifBuilder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GifBuilderDao {


    public byte[] generateGIF(ArrayList<Bitmap> images) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GifBuilder encoder = new GifBuilder();
        encoder.start(bos);
        for (Bitmap image : images) {
            encoder.addFrame(image);
        }
        encoder.finish();
        return bos.toByteArray();
    }
}
