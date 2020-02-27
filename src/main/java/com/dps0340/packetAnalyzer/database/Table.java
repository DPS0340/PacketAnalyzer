package com.dps0340.packetAnalyzer.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Table {

    private ExecutionManager executionManager = null;
    private String tableName = null;

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
    }

    public int getLength() {
        executionManager.promiseExec("SELECT COUNT(*) FROM ?", new ArrayList<String>() {
            {
                add(tableName);
            }
        });
        ResultSet resultSet = executionManager.call();
        try {
            int result = resultSet.getInt(0);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int nextInt() {
        int length = getLength();
        if (length == -1) {
            return -1;
        }
        return length + 1;
    }
}
