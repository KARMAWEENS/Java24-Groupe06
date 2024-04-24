package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorsDAO{

 public List<String> getDB(int movieID) throws SQLException {
        List<String> actorsList = new ArrayList<>();
        int movieId = movieID;
        String query =String.format("SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = %s;", movieId);
        // ! C'est un try with ressources pas un try catch
        System.out.println("avant preparedStatement");
        try (
                ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                actorsList.add(rs.getString("fullName"));

            }
        } catch (SQLException e){
            // Besoin de rien faire
            // Renverra un list vide
            // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
            // Si on fait alert ca fait plein d alerte

        }
        // COMPRENDRE POURQUOI QUAND Y A CONN.CLOSEDATABASE CA MARCHE PAS
        // conn.closeDatabase();
        System.out.println(actorsList);
        return actorsList;
    }
}
