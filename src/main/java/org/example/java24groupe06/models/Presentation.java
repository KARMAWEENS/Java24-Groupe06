package org.example.java24groupe06.models;

import java.util.List;

public class Presentation {
    private List<Poster> posterList;
    private final int nbColumn = 4;
    private int nbRow;

    public Presentation(List<Poster> posterList){
        this.posterList = posterList;
    }

    public void setNbRow() {
        int nbMoovies = posterList.size();
        this.nbRow = (int) Math.ceil((double) nbMoovies / nbColumn);
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public int getNbRow() {
        return nbRow;
    }

    public void addPoster(Poster poster) {
        posterList.add(poster);
        setNbRow();
    }

    public int nbPosters() {
        return posterList.size();
    }

    public List<Poster> getPosterList() {
        return posterList;
    }

}
