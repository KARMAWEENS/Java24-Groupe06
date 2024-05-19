package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.GenresDAO;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenresDAOTest {

    private GenresDAO genresDAO;

    @BeforeEach
    public void setUp() {
        genresDAO = new GenresDAO();
    }

    @Test
    public void getGenresByMovieIdReturnsExpectedGenres() throws SQLException {
        List<String> genres = genresDAO.getGenresByMovieId(2);
        assertEquals(1, genres.size());
        assertTrue(genres.contains("Drame"));

    }

    @Test
    public void getGenresByMovieIdReturnsEmptyListWhenNoGenres() throws SQLException {
        List<String> genres = genresDAO.getGenresByMovieId(14);
        assertTrue(genres.isEmpty());
    }


}