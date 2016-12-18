package com.nmp90.pictureminer;

import android.app.Application;

import com.nmp90.pictureminer.logger.TimberCrashReportingTree;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by joro on 18.12.16.
 */

public class FlickrApplication extends Application {
    private static AppComponent applicationComponent;
    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        refWatcher = LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new TimberCrashReportingTree());
        }
    }

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
