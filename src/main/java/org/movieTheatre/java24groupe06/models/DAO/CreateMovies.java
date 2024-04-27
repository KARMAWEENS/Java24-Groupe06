package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateMovies {
    List<Movie.MovieBuilder> moviesBuilderList;
    public List<Movie.MovieBuilder> getMoviesBuilderList() {
        return moviesBuilderList;
    }

    MoviesDAO moviesDAO = new MoviesDAO();
    ActorsDAO actorsDAO = new ActorsDAO();
    GenresDAO genresDAO = new GenresDAO();

           public List<Movie> buildMoviesList(){
               List<Movie> movieList = new ArrayList<>();
               try {
                 moviesBuilderList =  moviesDAO.getDB();
                   for (Movie.MovieBuilder movieBuilder : moviesBuilderList){
                       setActors(movieBuilder);
                       setGenres(movieBuilder);
                       movieList.add(movieBuilder.build());
                   }
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               return movieList;
           }

    private void setActors(Movie.MovieBuilder movieBuilder) throws SQLException {
        movieBuilder.setActors(getDbActors(movieBuilder));
    }
    private void setGenres(Movie.MovieBuilder movieBuilder) throws SQLException {
        movieBuilder.setGenres(getDbGenres(movieBuilder));
    }

    private List<String> getDbActors(Movie.MovieBuilder movieBuilder) throws SQLException {
        return actorsDAO.getActorsByMovieId(movieBuilder.getID());
    }
    private List<String> getDbGenres(Movie.MovieBuilder movieBuilder) throws SQLException {
        return genresDAO.getGenresByMovieId(movieBuilder.getID());
    }

}

