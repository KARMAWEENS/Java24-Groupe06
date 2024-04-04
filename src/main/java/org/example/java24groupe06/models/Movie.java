package org.example.java24groupe06.models;


import java.util.Date;

/**
 * The Movie class represents a movie in the cinema system.
 * It contains details about the movie such as title, duration, synopsis, release date, producer, image path and showing status.
 * The Movie class uses the Builder pattern for its creation, which allows for more readable and flexible construction of the Movie object.
 */
public class Movie {
    private String title;
    private int duration;

    public String getSynopsis() {
        return synopsis;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public boolean isShowing() {
        return isShowing;
    }

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

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }
    // Getters and possibly setters if needed

    public String getPathImg() {
        return this.pathImg;
    }

    /**
     * This is a Builder class for the Movie class.
     * The Builder pattern is used to construct a complex object step by step.
     */
    public static class MovieBuilder {
        private String title;
        private int duration;
        private String synopsis;
        private Date releaseDate;
        private String producer;
        private String pathImg;
        private boolean isShowing;

        /**
         * Sets the title of the movie.
         *
         * @param title The title of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the duration of the movie.
         *
         * @param duration The duration of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the synopsis of the movie.
         *
         * @param synopsis The synopsis of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setSynopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        /**
         * Sets the release date of the movie.
         *
         * @param releaseDate The release date of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        /**
         * Sets the producer of the movie.
         *
         * @param producer The producer of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setProducer(String producer) {
            this.producer = producer;
            return this;
        }

        /**
         * Sets the path of the image of the movie.
         *
         * @param pathImg The path of the image of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setPathImg(String pathImg) {
            this.pathImg = pathImg;
            return this;
        }

        /**
         * Sets the showing status of the movie.
         *
         * @param isShowing The showing status of the movie.
         * @return The current MovieBuilder instance.
         */
        public MovieBuilder setIsShowing(boolean isShowing) {
            this.isShowing = isShowing;
            return this;
        }

        /**
         * Builds the Movie object using the set parameters.
         *
         * @return A new Movie object.
         */
        public Movie build() {
            return new Movie(this);
        }
    }
}