package org.movieTheatre.java24groupe06.models.DAO;

import java.util.List;

public class DTO {

    public DTO(String title, int duration, int ID, String synopsis, String releaseDate, String producer, String pathImg, boolean isShowing) {
        this.title = title;
        this.duration = duration;
        this.ID = ID;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.pathImg = pathImg;
        this.isShowing = isShowing;
    }

    private String title;
    private int duration;
    private int ID;
    private String synopsis;
    private String releaseDate;
    private String producer;
    private String pathImg;

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getID() {
        return ID;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public String getPathImg() {
        return pathImg;
    }

    public boolean isShowing() {
        return isShowing;
    }

    private boolean isShowing;

}