package org.example.java24groupe06.models;

public class Poster {
private Position position;
private final int heightSize=80;
private final int withSize = 60;
    private Movie movie;

    public int getHeightSize() {
        return heightSize;
    }

    public int getWithSize() {
        return withSize;
    }
    public Poster(Position position, Movie movie){
        this.position = position;
        this.movie = movie;
    }
    public Movie getFilm(){
        return this.movie;
    }
    public String getPathImgPoster(){
       return movie.getPathImg();
    }
    public int getPositionColumn(){
        return position.getColumn();
    }
    public int getPositionRow(){
        return position.getRow();
    }
}
