package com.nmp90.pictureminer.di.pictures;

import com.nmp90.pictureminer.MainActivity;

import dagger.Subcomponent;

/**
 * Created by joro on 18.12.16.
 */
@Subcomponent(modules = {PicturesModule.class})
public interface PicturesComponent {
    void inject(MainActivity mainActivity);
}
