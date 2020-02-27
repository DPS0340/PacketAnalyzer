package com.dps0340.packetAnalyzer.database;

import java.sql.*;

public class ConnectionManager {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String USER = "root";
    static final String PASS = "";

    private String DB_URL = "jdbc:mariadb://localhost:3306/";
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    private String dbName = "";

    public ConnectionManager() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        dbName = "packetAnalyzer";
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        preparedStatement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + dbName + ";");
        preparedStatement.executeUpdate();
        connection.close();
        DB_URL += dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public ConnectionManager(String dbName) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        this.dbName = dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        preparedStatement = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS "  + dbName + ";");
        preparedStatement.executeUpdate();
        connection.close();
        DB_URL += dbName;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void drop() {
        try {
            ExecutionManager executionManager = new ExecutionManager(dbName);
            executionManager.promiseExec("DROP DATABASE " + dbName + ";");
            executionManager.call();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
