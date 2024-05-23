package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateMovies  {
    List<Movie.MovieBuilder> moviesBuilderList;
    MoviesDAO moviesDAO = new MoviesDAO();
    ActorsDAO actorsDAO = new ActorsDAO();
    GenresDAO genresDAO = new GenresDAO();

    public List<Movie> buildMoviesList() throws CreateMoviesException {
        List<Movie> movieList = new ArrayList<>();
        try {
            moviesBuilderList =  moviesDAO.getMoviesBuilderList();
            for (Movie.MovieBuilder movieBuilder : moviesBuilderList){
                setActors(movieBuilder);
                setGenres(movieBuilder);
                movieList.add(movieBuilder.build());
            }
        } catch (SQLException e) {
            throw new CreateMoviesException(e);
        } catch (DataAccessException e) {
            System.err.println("erreur lors de la récupération des acteurs ou des genres");
            e.printStackTrace();
        }
        return movieList;
    }

    private void setActors(Movie.MovieBuilder movieBuilder) throws DataAccessException, SQLException {
        movieBuilder.setActors(getDbActors(movieBuilder));
    }
    private void setGenres(Movie.MovieBuilder movieBuilder) throws  DataAccessException {
        movieBuilder.setGenres(getDbGenres(movieBuilder));
    }

    private List<String> getDbActors(Movie.MovieBuilder movieBuilder) throws SQLException, DataAccessException {
        return actorsDAO.getActorsByMovieId(movieBuilder.getID());
    }
    private List<String> getDbGenres(Movie.MovieBuilder movieBuilder) throws  DataAccessException {
        return genresDAO.getGenresByMovieId(movieBuilder.getID());
    }

}

