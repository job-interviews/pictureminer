package com.nmp90.pictureminer.api.transformer;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by joro on 18.12.16.
 */

public class Transformer implements RxTransformer {

    public <T> ObservableTransformer<T, T> applyIoSchedulers() {
        return observable -> observable.subscribeOn(getIoScheduler())
                .observeOn(getMainScheduler());
    }

    private Scheduler getIoScheduler() {
        return Schedulers.io();
    }

    private Scheduler getMainScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
