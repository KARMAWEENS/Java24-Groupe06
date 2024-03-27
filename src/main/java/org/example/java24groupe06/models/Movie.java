package org.example.java24groupe06.models;
import org.w3c.dom.ls.LSOutput;

import java.util.Date;

public class Movie {
    private String title;
    private int duration;
    private String synopsis;
    private Date releaseDate;
    private String producer;
    private String pathImg;
    private boolean isShowing;

    private Movie(MovieBuilder builder) {
        this.title = builder.title;
        this.duration = builder.duration;
        this.synopsis = builder.synopsis;
        this.releaseDate = builder.releaseDate;
        this.producer = builder.producer;
        this.pathImg = builder.pathImg;
        this.isShowing = builder.isShowing;
    }
public String getTitle(){
        return this.title;
}
public int getDuration(){
        return this.duration;
}
    // Getters and possibly setters if needed

    public static class MovieBuilder {
        private String title;
        private int duration;
        private String synopsis;
        private Date releaseDate;
        private String producer;
        private String pathImg;
        private boolean isShowing;

        public MovieBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public MovieBuilder setSynopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public MovieBuilder setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder setProducer(String producer) {
            this.producer = producer;
            return this;
        }
//a enlebver
        public MovieBuilder setPathImg(String pathImg) {
            this.pathImg = pathImg;
            return this;
        }

        public MovieBuilder setIsShowing(boolean isShowing) {
            this.isShowing = isShowing;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}