package com.nmp90.pictureminer.utils.pictures;

import java.io.File;

/**
 * Created by joro on 18.12.16.
 */

public interface OnPictureSavedListener {
    void onPictureSaved(File file);

    void onPictureSaveFailed();
}
