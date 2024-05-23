package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateSessionDTOTest {
    private CreateSessionDTO createSessionDTO;
    private Movie movie;

    @BeforeEach
    public void setup() {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder();
        movieBuilder.setTitle("Test Movie");
        movieBuilder.setProducer("Test Producer");
        movieBuilder.setDuration(120);
        movie = movieBuilder.build();

        createSessionDTO = new CreateSessionDTO(1, movie, "10:00");
    }

    @Test
    public void constructorCreatesValidObject() {
        assertNotNull(createSessionDTO);
        assertEquals(1, createSessionDTO.getSessionID());
        assertEquals(movie, createSessionDTO.getMovie());
        assertEquals("10:00", createSessionDTO.getTime());
    }

    @Test
    public void getSessionIDReturnsCorrectID() {
        assertEquals(1, createSessionDTO.getSessionID());
    }

    @Test
    public void getMovieReturnsCorrectMovie() {
        assertEquals(movie, createSessionDTO.getMovie());
    }

    @Test
    public void getTimeReturnsCorrectTime() {
        assertEquals("10:00", createSessionDTO.getTime());
    }
    @Test
    public void constructorThrowsExceptionWhenMovieIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, null, "10:00");
        });
        assertEquals("Movie cannot be null", exception.getMessage());
    }

    @Test
    public void constructorThrowsExceptionWhenTimeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, movie, null);
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }

    @Test
    public void constructorThrowsExceptionWhenTimeIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreateSessionDTO(1, movie, "");
        });
        assertEquals("Time cannot be null or empty", exception.getMessage());
    }
}
