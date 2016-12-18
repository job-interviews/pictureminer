package com.nmp90.pictureminer.di.app;

import com.nmp90.pictureminer.di.pictures.PicturesComponent;
import com.nmp90.pictureminer.di.pictures.PicturesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joro on 18.12.16.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    PicturesComponent plus(PicturesModule picturesModule);
}
