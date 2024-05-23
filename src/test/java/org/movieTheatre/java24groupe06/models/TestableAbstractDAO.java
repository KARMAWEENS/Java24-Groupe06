package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;

public class TestableAbstractDAO extends AbstractDAO {
    public <T> T getSingleResultPublic(String query, RowMapper<T> rowMapper, Object... params) throws  DataAccessException {
        return getSingleResult(query, rowMapper, params);
    }
}
