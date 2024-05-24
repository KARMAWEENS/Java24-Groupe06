package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.MoviesDAO;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for MoviesDAO.
 */
public class MoviesDAOTest {

    private MoviesDAO moviesDAO;
    /**
     * Sets up the test environment by initializing the MoviesDAO instance.
     */
    @BeforeEach
    public void setup() {
        moviesDAO = new MoviesDAO();
    }
    /**
     * Tests that the getDB method returns the expected list of movies.
     *
     * This test verifies that the method returns a list of 5 movies.
     * It checks if the list contains the expected movie titles.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getDBReturnsMoviesWithCorrectAttributes() throws  DataAccessException {
        List<Movie.MovieBuilder> movieBuilders = moviesDAO.getMoviesBuilderList();

        assertFalse(movieBuilders.isEmpty());

        Movie movie = movieBuilders.get(0).build();


        assertEquals("Oppenheimer", movie.getTitle());
        assertEquals("En 1942, convaincus que l'Allemagne nazie est en train de développer une arme nucléaire, les États-Unis initient, dans le plus grand secret, le Projet Manhattan destiné à mettre au point la première bombe atomique de l'histoire. Pour piloter ce dispositif, le gouvernement engage J. Robert Oppenheimer, brillant physicien. C'est dans le laboratoire ultra-secret de Los Alamos, au cœur du désert du Nouveau-Mexique, que le scientifique et son équipe mettent au point cette arme révolutionnaire", movie.getSynopsis());
        assertEquals("Christopher Nolan", movie.getProducer());
        assertEquals("2023-08-19", movie.getReleaseDate());
        assertEquals("src/main/resources/MoviesPosters/oppenheimer.png", movie.getPathImg());
        assertEquals(true, movie.getIsShowing());
    }
    /**
     * Tests that the getDB method returns only movies that are currently showing.
     *
     * This test verifies that the method returns a list of movies that are currently showing.
     * It checks if the list contains only movies with the isShowing attribute set to true.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getDBReturnsOnlyMoviesThatAreShowing() throws DataAccessException{
        List<Movie.MovieBuilder> movieBuilders = moviesDAO.getMoviesBuilderList();

        for (Movie.MovieBuilder movieBuilder : movieBuilders) {
            assertTrue(movieBuilder.build().getIsShowing());
        }
    }
}
