package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The CreateMovies class provides methods for creating a list of movies from the database.
 */
public class CreateMovies  {
    List<Movie.MovieBuilder> moviesBuilderList;
    MoviesDAO moviesDAO = new MoviesDAO();
    ActorsDAO actorsDAO = new ActorsDAO();
    GenresDAO genresDAO = new GenresDAO();

    /**
     * Builds a list of movies from the database.
     *
     * @return a list of movies.
     * @throws CreateMoviesException if an error occurs while building the list of movies.
     */
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

    /**
     * Sets the actors for a movie.
     *
     * @param movieBuilder the movie builder to set the actors for.
     * @throws DataAccessException if an error occurs while retrieving the actors.
     * @throws SQLException if an error occurs while executing the query.
     */
    private void setActors(Movie.MovieBuilder movieBuilder) throws DataAccessException, SQLException {
        movieBuilder.setActors(getDbActors(movieBuilder));
    }
    /**
     * Sets the genres for a movie.
     *
     * @param movieBuilder the movie builder to set the genres for.
     * @throws DataAccessException if an error occurs while retrieving the genres.
     */
    private void setGenres(Movie.MovieBuilder movieBuilder) throws  DataAccessException {
        movieBuilder.setGenres(getDbGenres(movieBuilder));
    }
    /**
     * Retrieves the actors for a movie from the database.
     *
     * @param movieBuilder the movie builder to retrieve the actors for.
     * @return a list of actors for the movie.
     * @throws SQLException if an error occurs while executing the query.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    private List<String> getDbActors(Movie.MovieBuilder movieBuilder) throws SQLException, DataAccessException {
        return actorsDAO.getActorsByMovieId(movieBuilder.getID());
    }
    /**
     * Retrieves the genres for a movie from the database.
     *
     * @param movieBuilder the movie builder to retrieve the genres for.
     * @return a list of genres for the movie.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    private List<String> getDbGenres(Movie.MovieBuilder movieBuilder) throws  DataAccessException {
        return genresDAO.getGenresByMovieId(movieBuilder.getID());
    }

}

