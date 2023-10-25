package com.example.atelier1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/articles";
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private DbConnection() {}

    public static void seConnecter() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connexion etablie ...");
        } catch(SQLException ex) {
            System.out.println("pb de connexion shi haja mahiash: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null || connection.isClosed())
            seConnecter();
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if(connection != null){
            connection.close();
        }
    }
}
