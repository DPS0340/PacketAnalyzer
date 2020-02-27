package com.dps0340.packetAnalyzer.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class Table {

    private ExecutionManager executionManager = null;
    private String tableName = null;
    private String query = null;
    private Collection<?> values = null;

    public Table(String tableName) {
        this.tableName = tableName;
        try {
            executionManager = new ExecutionManager();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Table(String tableName, String dbName) {
        this.tableName = tableName;
        try {
            executionManager = new ExecutionManager(dbName);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getLength() {
        executionManager.promiseExec("SELECT COUNT(*) FROM " + tableName + ";");
        try {
            ResultSet resultSet = executionManager.call();
            if(resultSet.next()) {
                int result = resultSet.getInt(1);
                return result;
            }
            return -1;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int nextInt() {
        int length = getLength();
        return length + 1;
    }

    public void drop() {
        executionManager.promiseExec("DROP TABLE " + tableName + ";");
        executionManager.call();
    }

    public ResultSet exec(String query, Collection<?> values) {
        this.query = query;
        this.values = values;
        return exec();
    }

    public ResultSet exec(String query) {
        this.query = query;
        this.values = null;
        return exec();
    }
    public ResultSet exec(Collection<?> values) {
        this.values = values;
        return exec();
    }

    public ResultSet exec() {
        executionManager.promiseExec(query, values);
        ResultSet resultSet = executionManager.call();
        return resultSet;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
