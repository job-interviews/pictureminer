package com.nmp90.pictureminer.api.transformer;

import io.reactivex.ObservableTransformer;

/**
 * Created by joro on 18.12.16.
 */

public interface RxTransformer {
    public <T> ObservableTransformer<T, T> applyIoSchedulers();
}
