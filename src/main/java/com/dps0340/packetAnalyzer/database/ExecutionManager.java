package com.dps0340.packetAnalyzer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.Callable;

public class ExecutionManager implements Callable {

    private ConnectionManager connectionManager = null;
    private String query = null;
    private ResultSet resultSet = null;
    private Collection<?> values = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;

    public ExecutionManager() throws SQLException, ClassNotFoundException {
        this.connectionManager = new ConnectionManager();
    }

    public ExecutionManager(String dbName) throws SQLException, ClassNotFoundException {
        this.connectionManager = new ConnectionManager(dbName);
    }


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void promiseExec(String query) {
        setQuery(query);
    }

    public void promiseExec(String query, Collection<?> values) {
        setQuery(query);
        this.values = values;
    }

    @Override
    public ResultSet call() throws NullPointerException {
        if(connectionManager == null || query == null) {
            throw new NullPointerException();
        }
        connection = connectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            if(values != null) {
                int cnt = 1;
                for(Object value : values) {
                    preparedStatement.setObject(cnt, value);
                    cnt++;
                }
            }
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally{
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            };
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            };
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e){
                    e.printStackTrace();
                }
            };
        }
    }
}
