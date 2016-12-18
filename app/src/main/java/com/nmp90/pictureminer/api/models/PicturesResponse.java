package com.nmp90.pictureminer.api.models;

import java.util.List;

/**
 * Created by joro on 18.12.16.
 */

public class PicturesResponse {
    private String title;
    private String link;

    private List<Picture> items;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public List<Picture> getItems() {
        return items;
    }
}
