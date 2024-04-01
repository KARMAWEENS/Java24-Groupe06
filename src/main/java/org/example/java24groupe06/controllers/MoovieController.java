package org.example.java24groupe06.controllers;


import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.models.Position;
import org.example.java24groupe06.models.Poster;

import java.util.ArrayList;
import java.util.List;

public class MoovieController {
    private List<Poster> posterList = new ArrayList<>();
    private List<Movie> mooviesList;

    public void createPosterList(List<Movie> mooviesList) {
        for (int i = 0; i < mooviesList.size(); i++) {
            // TODO le 4 a changé en une variable
            int ligne = i / 4;
            int colonne = i % 4;
            Position position = new Position(ligne, colonne);
            Poster poster = new Poster(position, mooviesList.get(i));
           this.posterList.add(poster);
        }
    }
    public List<Poster> getPosterList(){
        return this.posterList;
    }
}
