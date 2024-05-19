package org.movieTheatre.java24groupe06.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO;
import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractDAOTest {

    private TestableAbstractDAO abstractDAO;
    private RowMapper<String> testMapper;

    @BeforeEach
    public void setup() {
        abstractDAO = new TestableAbstractDAO() {
        };
        testMapper = resultSet -> resultSet.getString(2);
    }

    @Test
    public void getListResultReturnsCorrectData() throws SQLException {
        String query = "SELECT * FROM Movies LIMIT 1";
        String result = abstractDAO.getSingleResultPublic(query, testMapper);
        assertNotNull(result);

    }

    @Test
    public void getSingleResultReturnsCorrectData() throws SQLException {
        String query = "SELECT * FROM Genres LIMIT 1";
        String result = abstractDAO.getSingleResultPublic(query, testMapper);

        assertNotNull(result);

    }

    @Test
    public void getSingleResultReturnsNullWhenNoData() throws SQLException {
        String query = "SELECT * FROM MoviesCasting WHERE 1=0"; // This query should return no results
        String result = abstractDAO.getSingleResultPublic(query, testMapper);

        assertNull(result);
    }
    @Test
    public void getSingleResultThrowsExceptionForInvalidQuery() {
        String invalidQuery = "SELECT * FROM NonExistentTable";
        assertThrows(RuntimeException.class, () -> abstractDAO.getSingleResultPublic(invalidQuery, testMapper));
    }
    @Test
    public void getSingleResultThrowsExceptionForInvalidColumn() {
        String query = "SELECT * FROM Movies LIMIT 1";
        RowMapper<String> invalidMapper = resultSet -> resultSet.getString("NonExistentColumn");
        assertThrows(RuntimeException.class, () -> abstractDAO.getSingleResultPublic(query, invalidMapper));
    }
}
