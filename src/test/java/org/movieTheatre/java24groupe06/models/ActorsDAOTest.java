package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.ActorsDAO;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActorsDAOTest {

    private ActorsDAO actorsDAO;

    /**
     * Sets up the test environment by initializing the ActorsDAO instance.
     */
    @BeforeEach
    public void setup() {
        actorsDAO = new ActorsDAO();
    }

    /**
     * Tests that the getActorsByMovieId method returns the expected list of actors for a valid movie ID.
     *
     * This test verifies that the method returns a list of 3 actors for the movie with ID 2.
     * It checks if the list contains the expected actor names.
     *
     * @throws SQLException if a database access error occurs
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getActorsByMovieIdReturnsExpectedActors() throws SQLException, DataAccessException {
        List<String> actors = actorsDAO.getActorsByMovieId(2);
        assertEquals(3, actors.size());
        assertTrue(actors.contains("Cillian Murphy"));
        assertTrue(actors.contains("Emily Blunt"));
        assertTrue(actors.contains("Robert Downey Jr."));
    }

    /**
     * Tests that the getActorsByMovieId method returns an empty list for a movie ID with no actors.
     *
     * This test expects a SQLException to be thrown when trying to fetch actors for movie ID 10,
     * indicating that there are no actors for this movie ID.
     */
    @Test
    public void getActorsByMovieIdReturnsEmptyListForNoActors() {
        assertThrows(SQLException.class, () -> actorsDAO.getActorsByMovieId(10));
    }

    /**
     * Tests that the getActorsByMovieId method throws a SQLException for an invalid movie ID.
     *
     * This test expects a SQLException to be thrown when trying to fetch actors for an invalid movie ID (0),
     * indicating that the movie ID is invalid.
     */
    @Test
    public void getActorsByMovieIdThrowsExceptionForInvalidMovieId() {
        assertThrows(SQLException.class, () -> actorsDAO.getActorsByMovieId(0));
    }
}
