package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.models.DAO.AbstractDAO;

import java.sql.SQLException;

public class TestableAbstractDAO extends AbstractDAO {
    public <T> T getSingleResultPublic(String query, RowMapper<T> rowMapper, Object... params) throws SQLException, SQLException {
        return getSingleResult(query, rowMapper, params);
    }
}
