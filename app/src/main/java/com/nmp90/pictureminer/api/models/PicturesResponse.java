package com.nmp90.pictureminer.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by joro on 18.12.16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PicturesResponse {
    private String title;
    private String link;

    private List<Picture> items;

    public PicturesResponse() {
    }

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
