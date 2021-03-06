package com.nmp90.pictureminer.di.app;

import android.content.Context;

import com.nmp90.pictureminer.api.transformer.RxTransformer;
import com.nmp90.pictureminer.api.transformer.Transformer;
import com.nmp90.pictureminer.utils.Constants;
import com.nmp90.pictureminer.api.Api;
import com.nmp90.pictureminer.api.FlickrApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joro on 18.12.16.
 */

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Api provideApi() {
        return FlickrApi.createApi();
    }

    @Provides
    @Singleton
    public RxTransformer providesTransformer() {
        return new Transformer();
    }

    @Provides
    @Singleton
    @Named(Constants.DATA_FORMAT)
    public String providesDataFormat() {
        return "json";
    }
}
