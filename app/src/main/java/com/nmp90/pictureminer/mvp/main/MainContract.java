package com.nmp90.pictureminer.mvp.main;

import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.mvp.base.BaseContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joro on 18.12.16.
 */

public interface MainContract {
    interface View extends BaseContract.BaseView {
        void displayPictures(List<Picture> pictures);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void getPictures(ArrayList<String> tags);
    }
}
