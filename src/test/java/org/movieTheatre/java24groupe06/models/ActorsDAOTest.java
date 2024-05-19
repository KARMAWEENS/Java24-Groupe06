package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.ActorsDAO;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActorsDAOTest {

    private ActorsDAO actorsDAO;

    @BeforeEach
    public void setup() {
        actorsDAO = new ActorsDAO();
    }

    @Test
    public void getActorsByMovieIdReturnsExpectedActors() throws SQLException {
        List<String> actors = actorsDAO.getActorsByMovieId(2);
        assertEquals(3, actors.size());
        assertTrue(actors.contains("Cillian Murphy "));
        assertTrue(actors.contains("Emily Blunt"));
        assertTrue(actors.contains("Robert Downey Jr."));
    }

    @Test
    public void getActorsByMovieIdReturnsEmptyListForNoActors() {
        assertThrows(SQLException.class, () -> actorsDAO.getActorsByMovieId(10));
    }

    @Test
    public void getActorsByMovieIdThrowsExceptionForInvalidMovieId() {
        assertThrows(SQLException.class, () -> actorsDAO.getActorsByMovieId(0));
    }
}
