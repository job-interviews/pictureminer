package com.nmp90.pictureminer.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by joro on 18.12.16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {
    private String title, description;
    private String link;
    private Media media;

    @JsonProperty("date_taken")
    private Date dateTaken;

    @JsonProperty("published")
    private Date datePublished;

    private String author;

    public Picture() {
    }

    public Picture(String title, String description, String link, Media media, Date dateTaken, Date datePublished, String author) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.media = media;
        this.dateTaken = dateTaken;
        this.datePublished = datePublished;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public Media getMedia() {
        return media;
    }
}
