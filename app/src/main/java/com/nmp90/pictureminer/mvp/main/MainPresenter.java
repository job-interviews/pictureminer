package com.nmp90.pictureminer.mvp.main;

import android.content.Context;

import com.nmp90.pictureminer.api.Api;
import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.mvp.base.BasePresenter;
import com.nmp90.pictureminer.utils.Constants;
import com.nmp90.pictureminer.utils.pictures.OnPictureSavedListener;
import com.nmp90.pictureminer.utils.pictures.TargetPhoneGallery;
import com.squareup.picasso.Picasso;

import java.io.File;
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
    private final Context context;

    @Inject
    @Named(Constants.DATA_FORMAT)
    String dataFormat;

    @Inject
    public MainPresenter(MainContract.View view, Api api, Context context) {
        super(view);
        this.api = api;
        this.context = context;
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
        savePictureLocally(picture, new OnPictureSavedListener() {
            @Override
            public void onPictureSaved(File file) {
                if (viewExists()) {
                    getView().openSystemGallery(file);
                }
            }

            @Override
            public void onPictureSaveFailed() {
                if (viewExists()) {
                    getView().savePictureFailed();
                }
            }
        });
    }

    private void savePictureLocally(Picture picture, OnPictureSavedListener pictureSavedListener) {
        Picasso.with(context)
                .load(picture.getMedia().getLink())
                .into(new TargetPhoneGallery(pictureSavedListener));
    }

    public void sharePicture(Picture picture) {
        savePictureLocally(picture, new OnPictureSavedListener() {

            @Override
            public void onPictureSaved(File file) {
                if (viewExists()) {
                    getView().sharePicture(file);
                }
            }

            @Override
            public void onPictureSaveFailed() {
                if (viewExists()) {
                    getView().savePictureFailed();
                }
            }
        });
    }
}
