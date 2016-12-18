package com.nmp90.pictureminer.di.pictures;

import android.content.Context;

import com.nmp90.pictureminer.api.Api;
import com.nmp90.pictureminer.api.transformer.Transformer;
import com.nmp90.pictureminer.mvp.main.MainContract;
import com.nmp90.pictureminer.mvp.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joro on 18.12.16.
 */

@Module
public class PicturesModule {
    MainContract.View view;
    public PicturesModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View providesView() {
        return view;
    }

    @Provides
    public MainContract.Presenter providesPresenter(Context context, Api api, Transformer transformer) {
        return new MainPresenter(view, api, context, transformer);
    }
}
