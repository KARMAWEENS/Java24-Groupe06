package org.example.java24groupe06.models;

public class Poster {
private Position position;
private final int heightSize=80;
private final int withSize = 60;
private Moovies moovie;

    public int getHeightSize() {
        return heightSize;
    }

    public int getWithSize() {
        return withSize;
    }
    public Poster(Position position, Moovies moovie){
        this.position = position;
        this.moovie = moovie;
    }
    public Moovies getFilm(){
        return moovie;
    }
    public String getPathImgPoster(){
       return moovie.getPathImg();
    }
    public int getPositionColumn(){
        return position.getColumn();
    }
    public int getPositionRow(){
        return position.getRow();
    }
}
