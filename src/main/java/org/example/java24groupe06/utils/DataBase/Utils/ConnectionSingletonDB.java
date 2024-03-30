package org.example.java24groupe06.utils.DataBase.Utils;

import java.sql.*;

public class ConnectionSingletonDB {

    public static final String DB_URL = "jdbc:sqlite:./src/main/resources/DataBase/DataBaseCinema.db";
    private static ConnectionSingletonDB instance = null;
    private Connection connection = null;

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

    public static ConnectionSingletonDB getInstance(){
        if(instance == null){
            instance = new ConnectionSingletonDB();
        }
        return instance;
    }

    public void closeDatabase(){
        try{
            if(this.connection != null){
                this.connection.close();
                System.out.println("connexion ferme asoule");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

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
