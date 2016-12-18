package com.nmp90.pictureminer.mvp.base;

/**
 * Created by joro on 18.12.16.
 */

public interface BaseContract {
    interface BasePresenter {
        void start();
        void stop();
    }

    interface BaseView {
        boolean isActive();
    }
}
