package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.GenresDAO;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for GenresDAO.
 */
public class GenresDAOTest {

    private GenresDAO genresDAO;
    /**
     * Sets up the test environment by initializing the GenresDAO instance.
     */
    @BeforeEach
    public void setUp() {
        genresDAO = new GenresDAO();
    }
    /**
     * Tests that the getGenresByMovieId method returns the expected list of genres for a valid movie ID.
     *
     * This test verifies that the method returns a list of 1 genre for the movie with ID 2.
     * It checks if the list contains the expected genre name.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getGenresByMovieIdReturnsExpectedGenres() throws DataAccessException {
        List<String> genres = genresDAO.getGenresByMovieId(2);
        assertEquals(1, genres.size());
        assertTrue(genres.contains("Drame"));

    }
    /**
     * Tests that the getGenresByMovieId method returns an empty list for a movie ID with no genres.
     *
     * This test expects an empty list to be returned when trying to fetch genres for movie ID 14,
     * indicating that there are no genres for this movie ID.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getGenresByMovieIdReturnsEmptyListWhenNoGenres() throws DataAccessException {
        List<String> genres = genresDAO.getGenresByMovieId(14);
        assertTrue(genres.isEmpty());
    }


}