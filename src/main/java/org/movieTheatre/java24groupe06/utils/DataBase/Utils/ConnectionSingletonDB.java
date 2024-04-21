package org.movieTheatre.java24groupe06.utils.DataBase.Utils;

import java.io.Closeable;
import java.sql.*;

/**
 * The ConnectionSingletonDB class provides a singleton instance of a database connection.
 *
 * This class uses the Singleton design pattern to ensure that only one instance of the database connection is created.
 * The database connection is established using the JDBC API.
 * The database URL is specified as a constant.
 *
 * The class provides methods for getting the singleton instance, closing the database connection, and preparing SQL statements.
 */
public class ConnectionSingletonDB implements Closeable {

    public static final String DB_URL = "jdbc:sqlite:./src/main/resources/DataBase/DataBaseMovieT.db";
    private static ConnectionSingletonDB instance = null;
    private Connection connection = null;

    /**
     * Private constructor for the ConnectionSingletonDB class.
     *
     * This constructor is private to prevent other classes from creating new instances of the ConnectionSingletonDB class.
     * It establishes a connection to the database using the JDBC API.
     *
     * @throws RuntimeException If there is an error loading the JDBC driver or establishing the database connection.
     */
    private  ConnectionSingletonDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connexion db établie");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Retrieves the singleton instance of the ConnectionSingletonDB class.
     *
     * If the singleton instance has not been created yet, this method creates it.
     *
     * @return The singleton instance of the ConnectionSingletonDB class.
     */
    public static ConnectionSingletonDB getInstance(){
        if(instance == null){
            instance = new ConnectionSingletonDB();
        }
        return instance;
    }

    /**
     * Closes the database connection.
     *
     * @throws RuntimeException If there is an error closing the database connection.
     */
   @Override
    public void close(){
        try{
            if(this.connection != null){
                this.connection.close();
                //TODO a refaire
                instance=null;
                System.out.println("connexion ferme ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the Connection object.
     *
     * @return The Connection object.
     */
    public Connection getConnection() {
        return this.connection;
    }



/////// METHODE PREPARED STATEMENT AMENER A EVOLUER
// DONC PAS DE JAVADOC MTN

    // Ici c'est pas un override, ca porte le meme nom mais on appelle pas
    // prepareStatement dans les memes classes

    // Y A UN MONDE OU C EST MIEUX CA
/*    public ResultSet query(String sql) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            // Log ou gestion de l'erreur
            throw e; // Ou gestion plus élaborée
        }
    }*/
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return this.connection.prepareStatement(query);
    }

}
