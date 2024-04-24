package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.DAO.MovieDAO;
import org.movieTheatre.java24groupe06.models.Movie;

import java.sql.SQLException;
import java.util.List;

public class CreateMovies2  {
    public List<Movie.MovieBuilder> getMovieList() {
        return movieList;
    }

    List<Movie.MovieBuilder> movieList;
    MovieDAO movieDAO = new MovieDAO();

           public void hello(){
               try {
                 movieList =  movieDAO.getDB();
                   for (Movie.MovieBuilder movieBuilder : movieList){
                       ActorsDAO.get(movieBuilder.getID());
                       GenreDAO.get(movieBuilder.getID());


                       movieBuilder.setActors(GenreDAO.get(movieBuilder.getID()));
                   }
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
           }
           public void addActors(Movie.MovieBuilder builder,ActorsDAO actor){
               builder.setActors(actor);
           }
           public void mainFonction(){
               hello();
           }

}

