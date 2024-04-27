package org.movieTheatre.java24groupe06.DataBase.Utils;

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

// TODO gerer les exceptions
public class ConnectionSingletonDB implements Closeable {

    public static final String DB_URL = "jdbc:sqlite:./src/main/resources/DataBase/DataBaseMovieT.db";
    private static ConnectionSingletonDB instance = null;
    private Connection connection = null;
    public Connection getConnection() {
        return this.connection;
    }
    private void setConnection(Connection connection) throws SQLException {
        this.connection=connection;
    }
    private static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

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
            setConnection(establishConnection());
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

    public static ConnectionSingletonDB getCurrent(){
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
                System.out.println("connexion ferme ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the Connection object.
     *
     * @return The Connection object.
     */
    public PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            setConnection(establishConnection());
        }

        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

}
/*
private PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
    PreparedStatement stmt = conn.prepareStatement(query);
    for (int i = 0; i < params.length; i++) {
        stmt.setObject(i + 1, params[i]);
    }
    return stmt;
}*/
