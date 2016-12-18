package com.nmp90.pictureminer.ui.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by joro on 18.12.16.
 */

public class BindingAdapters {

    @BindingAdapter("app:imageUrl")
    public static void bindImageUrl(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
