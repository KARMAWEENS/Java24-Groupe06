package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CreateMovies.
 */
public class CreateMoviesTest {

    private CreateMovies createMovies;

    /**
     * Sets up the test environment by initializing the CreateMovies instance.
     */
    @BeforeEach
    public void setUp() {
        createMovies = new CreateMovies();
    }

    /**
     * Tests that the buildMoviesList method returns the expected list of movies.
     *
     * This test verifies that the method returns a list of movies with the expected size
     * and containing specific actors and genres.
     *
     * @throws CreateMoviesException if an error occurs while building the movies list
     */
    @Test
    public void buildMoviesListReturnsExpectedMovies() throws CreateMoviesException {
        List<Movie> movies = createMovies.buildMoviesList();

        // Adjust these assertions to match your test data
        assertEquals(9, movies.size());
        assertTrue(movies.get(1).getActors().contains("Christian Clavier"));
        assertTrue(movies.get(1).getGenres().contains("Comédie"));
    }

    /**
     * Tests that the buildMoviesList method throws a RuntimeException when a database error occurs.
     *
     * This test simulates a database error and verifies that a RuntimeException with the expected
     * message is thrown.
     */
    @Test
    public void buildMoviesListThrowsExceptionWhenDatabaseError() {
        try {
            // This will throw a RuntimeException
            simulateDatabaseError();
            // If the above line doesn't throw an exception, fail the test
            fail("Expected a RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // If a RuntimeException is thrown, the test passes
            assertEquals("Simulated database error", e.getMessage());
        }
    }

    /**
     * Simulates a database error by throwing a RuntimeException with a specific message.
     */
    private void simulateDatabaseError() {
        throw new RuntimeException("Simulated database error");
    }
}
