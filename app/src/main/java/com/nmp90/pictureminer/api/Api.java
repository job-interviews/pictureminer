package com.nmp90.pictureminer.api;

import com.nmp90.pictureminer.api.models.PicturesResponse;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by joro on 18.12.16.
 */

public interface Api {
    @GET("feeds/photos")
    Observable<PicturesResponse> getPictures(@Query("format") String format, @Query("tags")ArrayList<String> tags);
}
