package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for SessionDAO.
 */
public class SessionDAOTest {

    private SessionDAO sessionDAO;
    private Movie movie;
    /**
     * Sets up the test environment by initializing the SessionDAO instance.
     */
    @BeforeEach
    public void setup() {
        sessionDAO = new SessionDAO();
        movie = new Movie.MovieBuilder().setID(1).build();
    }

    /**
     * Tests that the getDTOSessionList method returns the correct list of sessions for a valid movie.
     * @throws DataAccessException
     */
    @Test
    public void getDTOSessionListReturnsCorrectList() throws  DataAccessException {
        List<CreateSessionDTO> result = sessionDAO.getDTOSessionList(movie);
        assertFalse(result.isEmpty());
    }

    /**
     * Tests that the getSeatsRoomLeftBySessionId method returns the correct seats room left for a valid session.
     * @throws DataAccessException
     */
    @Test
    public void getSeatsRoomLeftBySessionIdReturnsCorrectSeatsRoomLeft() throws  DataAccessException {
        Session session = new Session(3, movie, new SeatsRoomLeft(1, 1, 1), "12:00");
        SeatsRoomLeft result = sessionDAO.getSeatsRoomLeftBySessionId(session);
        System.out.println(result);
        assertTrue(result.getNbRegularSeats() > 0);
    }
    /**
     * Tests that the getSessionBySessionId method returns the correct session for a valid session ID.
     * @throws DataAccessException
     */
    @Test
    public void getSessionBySessionIdReturnsCorrectSession() throws  DataAccessException {
        Session session = sessionDAO.getSessionBySessionId(3, movie);
        assertEquals(3, session.getSessionID());
        assertEquals(movie, session.getMovie());
        assertEquals("12:15", session.getTime());
    }
}
