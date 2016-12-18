package com.nmp90.pictureminer.mvp.main;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.mvp.base.BaseContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joro on 18.12.16.
 */

public interface MainContract {
    interface View extends BaseContract.BaseView {
        void displayPictures(List<Picture> pictures);

        void displayPictureInSystem(File file);
        void savePictureFailed();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void getPictures(ArrayList<String> tags);
    }
}
