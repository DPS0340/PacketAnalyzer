package com.dps0340.packetAnalyzer.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class ExecutionManager implements Runnable {

    private ConnectionManager connectionManager = null;
    private String query = null;
    private Collection<?> values = null;

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
    public void run() throws NullPointerException {
        if(connectionManager == null || query == null) {
            throw new NullPointerException();
        }
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(values != null) {
                int cnt = 1;
                for(Object value : values) {
                    preparedStatement.setObject(cnt, value);
                    cnt++;
                }
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
