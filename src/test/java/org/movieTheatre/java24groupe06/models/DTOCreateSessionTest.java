package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.Movie;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DTOCreateSessionTest {
    private DTOCreateSession dtoCreateSession;
    private Movie movie;

    @BeforeEach
    public void setup() {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder();
        movieBuilder.setTitle("Test Movie");
        movieBuilder.setProducer("Test Producer");
        movieBuilder.setDuration(120);
        movie = movieBuilder.build();

        dtoCreateSession = new DTOCreateSession(1, movie, "10:00");
    }

    @Test
    public void constructorCreatesValidObject() {
        assertNotNull(dtoCreateSession);
        assertEquals(1, dtoCreateSession.getSessionID());
        assertEquals(movie, dtoCreateSession.getMovie());
        assertEquals("10:00", dtoCreateSession.getTime());
    }

    @Test
    public void getSessionIDReturnsCorrectID() {
        assertEquals(1, dtoCreateSession.getSessionID());
    }

    @Test
    public void getMovieReturnsCorrectMovie() {
        assertEquals(movie, dtoCreateSession.getMovie());
    }

    @Test
    public void getTimeReturnsCorrectTime() {
        assertEquals("10:00", dtoCreateSession.getTime());
    }
    @Test
    public void constructorThrowsExceptionWhenMovieIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DTOCreateSession(1, null, "10:00");
        });
        assertEquals("Movie cannot be null", exception.getMessage());
    }

    @Test
    public void constructorThrowsExceptionWhenTimeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DTOCreateSession(1, movie, null);
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }

    @Test
    public void constructorThrowsExceptionWhenTimeIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DTOCreateSession(1, movie, "");
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }
}
