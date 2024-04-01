package org.example.java24groupe06.controllers;

import org.example.java24groupe06.models.Moovies;
import org.example.java24groupe06.models.Position;
import org.example.java24groupe06.models.Poster;
import org.example.java24groupe06.models.Presentation;

import java.util.ArrayList;
import java.util.List;

public class MoovieController {
    private List<Poster> posterList = new ArrayList<>();
    private List<Moovies> mooviesList;

    public MoovieController(List<Moovies> mooviesList) {
        this.mooviesList = mooviesList;
        createPosterList(mooviesList);
    }

    public void createPosterList(List<Moovies> mooviesList) {
        for (int i = 0; i < mooviesList.size(); i++) {
            int ligne = i / 4;
            int colonne = i % 4;
            Position position = new Position(ligne, colonne);
            Poster poster = new Poster(position, mooviesList.get(i));
           posterList.add(poster);
        }
    }
    public List<Poster> getPosterList(){
        return posterList;
    }
}
