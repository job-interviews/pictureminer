package com.nmp90.pictureminer.api;

import com.nmp90.pictureminer.api.models.PicturesResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joro on 18.12.16.
 */

public interface Api {
    @GET("feeds/photos_public.gne?nojsoncallback=1")
    Observable<PicturesResponse> getPictures(@Query("format") String format, @Query("tags")ArrayList<String> tags);
}
