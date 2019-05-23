package com.spencerstock.gnocchi.ImageProperties;

import android.graphics.Bitmap;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

public class GnocchiOverview {
    private String title;
    private Bitmap firstFrame;
    private GifDrawable gifPreview;
    private int size;


    public GnocchiOverview() {
    }

    public GnocchiOverview(String title, Bitmap firstFrame, GifDrawable gifPreview) {
        this.title = title;
        this.firstFrame = firstFrame;
        this.gifPreview = gifPreview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getFirstFrame() {
        return firstFrame;
    }

    public void setFirstFrame(Bitmap firstFrame) {
        this.firstFrame = firstFrame;
    }

    public GifDrawable getGifPreview() {
        return gifPreview;
    }

    public void setGifPreview(GifDrawable gifPreview) {
        this.gifPreview = gifPreview;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
