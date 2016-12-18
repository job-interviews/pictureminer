package com.nmp90.pictureminer.mvp.main;

import com.nmp90.pictureminer.Constants;
import com.nmp90.pictureminer.api.Api;
import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.mvp.base.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by joro on 18.12.16.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final Api api;

    @Inject
    @Named(Constants.DATA_FORMAT)
    String dataFormat;

    @Inject
    public MainPresenter(MainContract.View view, Api api) {
        super(view);
        this.api = api;
    }

    @Override
    public void getPictures(ArrayList<String> tags) {
        disposables.add(
                api.getPictures(dataFormat, tags)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(pictureResponse -> {
                            if (viewExists()) {
                                getView().displayPictures(pictureResponse.getItems());
                            }
                        }, error -> {
                            Timber.e(error);
                        }));
    }

    @Override
    public void start() {
        disposables = new CompositeDisposable();
    }

    @Override
    public void stop() {
        disposables.dispose();
    }

    public void savePicture(Picture picture) {

    }

    public void sharePicture(Picture picture) {


    }
}
