package org.movieTheatre.java24groupe06.models.DAO;

public class PartialMovieDTO {
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


    public PartialMovieDTO(String title, int duration, int ID, String synopsis, String releaseDate, String producer, String pathImg, boolean isShowing) {
        checkNotNull(new Object[]{title, synopsis, releaseDate, producer, pathImg}, new String[]{"Title", "Synopsis", "Release date", "Producer", "Path image","duration","ID"});
        checkNotNegative(new int[]{duration, ID}, new String[]{"Duration", "ID"});
        this.title = title;
        this.duration = duration;
        this.ID = ID;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.pathImg = pathImg;
        this.isShowing = isShowing;
    }

    private void checkNotNull(Object[] objs, String[] names) {
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] == null) {
                errorMessage.append(names[i]).append("erreur ne peut pas être null");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
    private void checkNotNegative(int[] values, String[] names) {
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (values[i] < 0) {
                errorMessage.append(names[i]).append("cannot be negative. ");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }
}