package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO;
import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO.RowMapper;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AbstractDAO} class.
 */
public class AbstractDAOTest {

    private TestableAbstractDAO abstractDAO;
    private RowMapper<String> testMapper;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setup() {
        abstractDAO = new TestableAbstractDAO() {
        };
        testMapper = resultSet -> resultSet.getString(2);
    }

    /**
     * Tests that the getListResult method returns correct data.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getListResultReturnsCorrectData() throws DataAccessException {
        String query = "SELECT * FROM Movies LIMIT 1";
        String result = abstractDAO.getSingleResultPublic(query, testMapper);
        assertNotNull(result);
    }

    /**
     * Tests that the getSingleResult method returns correct data.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getSingleResultReturnsCorrectData() throws DataAccessException {
        String query = "SELECT * FROM Genres LIMIT 1";
        String result = abstractDAO.getSingleResultPublic(query, testMapper);
        assertNotNull(result);
    }

    /**
     * Tests that the getSingleResult method returns null when no data is found.
     *
     * @throws DataAccessException if a data access error occurs
     */
    @Test
    public void getSingleResultReturnsNullWhenNoData() throws DataAccessException {
        String query = "SELECT * FROM MoviesCasting WHERE 1=0"; // This query should return no results
        String result = abstractDAO.getSingleResultPublic(query, testMapper);
        assertNull(result);
    }

    /**
     * Tests that the getSingleResult method throws a RuntimeException for an invalid query.
     */
    @Test
    public void getSingleResultThrowsExceptionForInvalidQuery() {
        String invalidQuery = "SELECT * FROM NonExistentTable";
        assertThrows(DataAccessException.class, () -> abstractDAO.getSingleResultPublic(invalidQuery, testMapper));
    }


    /**
     * Tests that the getSingleResult method throws a RuntimeException for an invalid column.
     */
    @Test

    public void getSingleResultThrowsExceptionForInvalidColumn() {
        String query = "SELECT * FROM Movies LIMIT 1";
        RowMapper<String> invalidMapper = resultSet -> resultSet.getString("NonExistentColumn");
        assertThrows(DataAccessException.class, () -> abstractDAO.getSingleResultPublic(query, invalidMapper));
    }

}
