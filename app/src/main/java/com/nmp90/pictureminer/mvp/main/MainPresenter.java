package com.nmp90.pictureminer.mvp.main;

import android.content.Context;

import com.nmp90.pictureminer.api.Api;
import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.api.models.PictureOrder;
import com.nmp90.pictureminer.api.transformer.RxTransformer;
import com.nmp90.pictureminer.mvp.base.BasePresenter;
import com.nmp90.pictureminer.utils.Constants;
import com.nmp90.pictureminer.utils.pictures.OnPictureSavedListener;
import com.nmp90.pictureminer.utils.pictures.TargetPhoneGallery;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by joro on 18.12.16.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final Api api;
    private final Context context;
    private final RxTransformer transformer;

    @Inject
    @Named(Constants.DATA_FORMAT)
    String dataFormat;

    @Inject
    public MainPresenter(MainContract.View view, Api api, Context context, RxTransformer transformer) {
        super(view);
        this.api = api;
        this.context = context;
        this.transformer = transformer;
    }

    @Override
    public void getPictures(ArrayList<String> tags, @PictureOrder int pictureOrder) {
        disposables.add(
                api.getPictures(dataFormat, tags)
                        .compose(transformer.applyIoSchedulers())
                        .subscribe(pictureResponse -> {
                            List<Picture> items = pictureResponse.getItems();
                            Collections.sort(items, new Comparator<Picture>() {
                                @Override
                                public int compare(Picture picture, Picture t1) {
                                    if (pictureOrder == PictureOrder.DATE_PUBLISHED) {
                                        return compareDates(picture.getDatePublished(), t1.getDatePublished());
                                    } else {
                                        return compareDates(picture.getDateTaken(), t1.getDateTaken());
                                    }

                                }

                                private int compareDates(Date dateTaken, Date dateTaken1) {
                                    if (dateTaken.before(dateTaken1)) {
                                        return 1;
                                    } else if (dateTaken.after(dateTaken1)) {
                                        return -1;
                                    } else {
                                        return 0;
                                    }
                                }
                            });
                            if (viewExists()) {
                                getView().displayPictures(items);
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
