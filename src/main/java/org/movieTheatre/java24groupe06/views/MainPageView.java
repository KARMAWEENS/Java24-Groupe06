package org.movieTheatre.java24groupe06.views;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.FileNotFoundException;
import java.util.List;

public interface MainPageView {

    void setMovieList(List<Movie> movies);
    void show()throws FileNotFoundException;

}
