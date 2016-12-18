package com.nmp90.pictureminer.mvp.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by joro on 18.12.16.
 */

public abstract class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter {
    protected  WeakReference<T> view;
    protected CompositeDisposable disposables = new CompositeDisposable();

    public BasePresenter(T view) {
        this.view = new WeakReference<T>(view);
    }

    protected boolean viewExists() {
        return view != null && view.get() != null && view.get().isActive();
    }

    protected T getView() {
        return view.get();
    }
}
