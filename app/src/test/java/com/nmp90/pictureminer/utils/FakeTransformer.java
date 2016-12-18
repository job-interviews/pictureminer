package com.nmp90.pictureminer.utils;

import com.nmp90.pictureminer.api.transformer.RxTransformer;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by joro on 18.12.16.
 */

public class FakeTransformer implements RxTransformer {

    public <T> ObservableTransformer<T, T> applyIoSchedulers() {
        return observable -> observable.subscribeOn(getIoScheduler())
                .observeOn(getMainScheduler());
    }

    private Scheduler getIoScheduler() {
        return Schedulers.single() ;
    }

    private Scheduler getMainScheduler() {
        return Schedulers.single() ;
    }
}
