package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionDAOTest {

    private SessionDAO sessionDAO;
    private Movie movie;

    @BeforeEach
    public void setup() {
        sessionDAO = new SessionDAO();
        movie = new Movie.MovieBuilder().setID(1).build();
    }

    @Test
    public void getDTOSessionListReturnsCorrectList() throws SQLException {
        List<CreateSessionDTO> result = sessionDAO.getDTOSessionList(movie);
        assertFalse(result.isEmpty());
    }

    @Test
    public void getSeatsRoomLeftBySessionIdReturnsCorrectSeatsRoomLeft() throws SQLException {
        Session session = new Session(3, movie, new SeatsRoomLeft(1, 1, 1), "12:00");
        SeatsRoomLeft result = sessionDAO.getSeatsRoomLeftBySessionId(session);
        System.out.println(result);
        assertTrue(result.getNbRegularSeats() > 0);
    }

    @Test
    public void getSessionBySessionIdReturnsCorrectSession() throws SQLException {
        Session session = sessionDAO.getSessionBySessionId(3, movie);
        assertEquals(3, session.getSessionID());
        assertEquals(movie, session.getMovie());
        assertEquals("12:15", session.getTime());
    }
}
