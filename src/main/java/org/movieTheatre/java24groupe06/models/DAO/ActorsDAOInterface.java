package org.movieTheatre.java24groupe06.models.DAO;

import java.util.List;

public interface ActorsDAOInterface extends InterfaceDAO {

    <T> List<T> getDB(int MovieID);
}
