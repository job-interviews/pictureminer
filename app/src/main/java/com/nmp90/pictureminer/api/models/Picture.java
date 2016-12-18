package com.nmp90.pictureminer.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by joro on 18.12.16.
 */

public class Picture {
    private String title, description;
    private String link;

    @JsonProperty("date_taken")
    private Date dateTaken;

    private String author;

    public Picture(String title, String description, String link, Date dateTaken, String author) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.dateTaken = dateTaken;
        this.author = author;
    }
}
