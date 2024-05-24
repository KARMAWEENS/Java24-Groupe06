package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 * Test class for CreateSessionDTO.
 */
public class CreateSessionDTOTest {
    private CreateSessionDTO createSessionDTO;
    private Movie movie;

    /**
     * Sets up the test environment by initializing the CreateSessionDTO instance.
     */
    @BeforeEach
    public void setup() {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder();
        movieBuilder.setTitle("Test Movie");
        movieBuilder.setProducer("Test Producer");
        movieBuilder.setDuration(120);
        movie = movieBuilder.build();

        createSessionDTO = new CreateSessionDTO(1, movie, "10:00");
    }

    /**
     * Tests that the constructor creates a valid CreateSessionDTO object.
     */
    @Test
    public void constructorCreatesValidObject() {
        assertNotNull(createSessionDTO);
        assertEquals(1, createSessionDTO.getSessionID());
        assertEquals(movie, createSessionDTO.getMovie());
        assertEquals("10:00", createSessionDTO.getTime());
    }

    /**
     * Tests that the getSessionID method returns the correct session ID.
     */
    @Test
    public void getSessionIDReturnsCorrectID() {
        assertEquals(1, createSessionDTO.getSessionID());
    }
    /**
     * Tests that the getMovie method returns the correct movie.
     */
    @Test
    public void getMovieReturnsCorrectMovie() {
        assertEquals(movie, createSessionDTO.getMovie());
    }
    /**
     * Tests that the getTime method returns the correct time.
     */
    @Test
    public void getTimeReturnsCorrectTime() {
        assertEquals("10:00", createSessionDTO.getTime());
    }

    /**
     * Tests that the constructor throws an exception when the movie is null.
     */
    @Test
    public void constructorThrowsExceptionWhenMovieIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, null, "10:00");
        });
        assertEquals("Movie cannot be null", exception.getMessage());
    }

    /**
     * Tests that the constructor throws an exception when the time is null.
     */
    @Test
    public void constructorThrowsExceptionWhenTimeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, movie, null);
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }
    /**
     * Tests that the constructor throws an exception when the time is empty.
     */
    @Test
    public void constructorThrowsExceptionWhenTimeIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, movie, "");
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }
}
