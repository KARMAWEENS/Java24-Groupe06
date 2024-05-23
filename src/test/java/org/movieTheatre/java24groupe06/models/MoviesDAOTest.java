package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.MoviesDAO;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoviesDAOTest {

    private MoviesDAO moviesDAO;

    @BeforeEach
    public void setup() {
        moviesDAO = new MoviesDAO();
    }

    @Test
    public void getDBReturnsMoviesWithCorrectAttributes() throws SQLException {
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

    @Test
    public void getDBReturnsOnlyMoviesThatAreShowing() throws SQLException {
        List<Movie.MovieBuilder> movieBuilders = moviesDAO.getMoviesBuilderList();

        for (Movie.MovieBuilder movieBuilder : movieBuilders) {
            assertTrue(movieBuilder.build().getIsShowing());
        }
    }
}
