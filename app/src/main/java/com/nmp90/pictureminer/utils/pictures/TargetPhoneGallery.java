package com.nmp90.pictureminer.utils.pictures;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.nmp90.pictureminer.utils.FileUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

/**
 * Created by joro on 18.12.16.
 */

public class TargetPhoneGallery implements Target {
    private final OnPictureSavedListener listener;

    public TargetPhoneGallery(OnPictureSavedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {
        File file = FileUtils.saveImage(bitmap);
        if (file != null) {
            listener.onPictureSaved(file);
        } else {
            listener.onPictureSaveFailed();
        }
    }

    @Override
    public void onBitmapFailed(Drawable arg0) {
    }
}
