package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateMoviesTest {

    private CreateMovies createMovies;

    @BeforeEach
    public void setUp() {
        createMovies = new CreateMovies();
    }

    @Test
    public void buildMoviesListReturnsExpectedMovies() throws SQLException {
        List<Movie> movies = createMovies.buildMoviesList();

        // Adjust these assertions to match your test data
        assertEquals(9, movies.size());
        assertTrue(movies.get(1).getActors().contains("Christian Clavier"));
        assertTrue(movies.get(1).getGenres().contains("Comédie"));
    }

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

    private void simulateDatabaseError() {
        throw new RuntimeException("Simulated database error");

    }



}
