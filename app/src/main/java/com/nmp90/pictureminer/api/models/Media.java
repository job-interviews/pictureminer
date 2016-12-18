package com.nmp90.pictureminer.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joro on 18.12.16.
 */

public class Media {
    @JsonProperty("m")
    private String link;

    public Media(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
