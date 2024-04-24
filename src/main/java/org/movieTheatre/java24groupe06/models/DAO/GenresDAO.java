package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenresDAO {

    public   List<String> getDB(int movieID) throws SQLException {
        List<String> genresList = new ArrayList<>();
        int movieId = movieID;
        String query =String.format("SELECT g.genre\n" +
                "FROM Genres g\n" +
                "JOIN MoviesGenres mg ON g.genreID = mg.genreID\n" +
                "WHERE mg.movieID = %s;", movieId);
        // ! C'est un try with ressources pas un try catch
        try (
                ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                genresList.add(rs.getString("genre"));

            }
        } catch (SQLException e){
            // Besoin de rien faire
            // Renverra un list vide
            // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
            // Si on fait alert ca fait plein d alerte
        }
        System.out.println(genresList);
        return genresList;
    }

}
