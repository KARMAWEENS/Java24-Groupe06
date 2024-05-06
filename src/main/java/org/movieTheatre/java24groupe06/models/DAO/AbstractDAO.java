package org.movieTheatre.java24groupe06.models.DAO;


import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO {

    protected void closeResources(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Méthode générique pour obtenir une liste d'éléments
    protected <T> List<T> getListResult(String query, RowMapper<T> mapper, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(mapper.mapRow(rs));
            }
        }
        return results;
    }

    // Méthode générique pour obtenir un simple élément
    protected <T> T getSingleResult(String query, RowMapper<T> mapper, Object... params) throws SQLException {
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query, params);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return mapper.mapRow(rs);
            }
            return null;
        }
    }

    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
}

