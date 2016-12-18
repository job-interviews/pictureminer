package com.nmp90.pictureminer.api.models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by joro on 18.12.16.
 */

@Retention(SOURCE)
@IntDef({ PictureOrder.DATE_PUBLISHED, PictureOrder.DATE_TAKEN })
public @interface PictureOrder {
    public static final int DATE_PUBLISHED = 0;
    public static final int DATE_TAKEN = 1;
}
