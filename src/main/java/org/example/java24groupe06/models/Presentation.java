package org.example.java24groupe06.models;

import java.util.List;

public class Presentation {
    private List<Movie> moviesList;
    private final int nbColumn = 4;
    private int nbRow;

    public Presentation(List<Movie> moviesList){
        this.moviesList = moviesList;
        // Faut surement  faire ailleurs le setRow
        setNbRow();
    }

    public void setNbRow() {
        int nbMoovies = moviesList.size();
        this.nbRow = (int) Math.ceil((double) nbMoovies / nbColumn);
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public int getNbRow() {
        return nbRow;
    }

}
