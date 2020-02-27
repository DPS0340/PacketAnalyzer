package com.dps0340.packetAnalyzer.database;

import java.sql.*;

public class ConnectionManager {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String USER = "root";
    static final String PASS = "";

    private String DB_URL = "jdbc:mariadb://localhost:3306/";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String dbName = "";

    public ConnectionManager() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        dbName = "packetAnalyzer";
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        preparedStatement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS ?");
        preparedStatement.setString(1, dbName);
        preparedStatement.execute();
        connection.close();
        DB_URL += dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ConnectionManager(String dbName) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        this.dbName = dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        preparedStatement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS ?");
        preparedStatement.setString(1, dbName);
        preparedStatement.execute();
        connection.close();
        DB_URL += dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public Connection getConnection() {
        return connection;
    }
}
