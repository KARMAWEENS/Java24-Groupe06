package org.movieTheatre.java24groupe06.utils.DataBase.CRUD;

import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDB {

    private static UpdateDB instance;
    private ConnectionSingletonDB connectionSingletonDB ;

    public UpdateDB(){
        this.connectionSingletonDB = ConnectionSingletonDB.getInstance();
    }

    public static UpdateDB getInstance(){
        if(instance == null){
            instance = new UpdateDB();
        }
        return instance;
    }

    public void updateTable( String tableName, String[] columnName, String[] value, String condition) throws SQLException {
        Connection conn = connectionSingletonDB.getConnection();
        if(columnName.length != value.length){
            throw new IllegalArgumentException("The number of columns must have the same lentgh");
        }

        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");

        for (int i = 0; i < columnName.length; i++) {
            query.append(columnName[i]).append(" = ?");

            if (i < columnName.length - 1) {
                query.append(", ");
            }
        }
        query.append(" WHERE ").append(condition);

        try(PreparedStatement preparedStatement = conn.prepareStatement(query.toString())){
            for (int i = 0; i < value.length; i++) {
                preparedStatement.setString(i + 1, value[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating table: " + e.getMessage());
            throw e;

        }
    }
}