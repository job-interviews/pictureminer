package com.nmp90.pictureminer.api;

import com.nmp90.pictureminer.api.models.Media;
import com.nmp90.pictureminer.api.models.Picture;
import com.nmp90.pictureminer.api.models.PicturesResponse;

import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by joro on 18.12.16.
 */

public class FakeApi implements Api {
    private Picture picture1 = new Picture("Test", "Test", "Test", new Media("Test"), new Date(), new Date(), "Test");
    private Picture picture2 = new Picture("Test2", "Test2", "Test2", new Media("Test2"), new Date(), new Date(), "Test2");
    public PicturesResponse picturesResponse = new PicturesResponse(null, null, new ArrayList() {{
        add(picture1);
        add(picture2);
    }});

    @SuppressWarnings("unchecked")
    @Override
    public Observable<PicturesResponse> getPictures(@Query("format") String format, @Query("tags") ArrayList<String> tags) {
        return Observable.just(picturesResponse);
    }
}
