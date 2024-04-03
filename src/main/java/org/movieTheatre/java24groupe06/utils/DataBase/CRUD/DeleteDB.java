package org.movieTheatre.java24groupe06.utils.DataBase.CRUD;
import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDB {

    private ConnectionSingletonDB connectionSingletonDB ;

    private static DeleteDB instance;
    public DeleteDB(){
        this.connectionSingletonDB = ConnectionSingletonDB.getInstance();
    }

    public static DeleteDB getInstance(){
        if(instance == null){
            instance = new DeleteDB();
        }
        return instance;
    }

    public void deleteTable( String tableName) {
        Connection conn = connectionSingletonDB.getConnection();
        try {
            Statement statement = conn.createStatement();

            // Exécutez ici votre instruction SQL pour supprimer la table
            String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
            statement.executeUpdate(deleteTableQuery);

            System.out.println("Table " + tableName + " supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
