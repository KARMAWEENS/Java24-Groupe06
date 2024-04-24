package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Session;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO {
   <T> List<T> getDB() throws SQLException;
    default void update() {

    }
}
