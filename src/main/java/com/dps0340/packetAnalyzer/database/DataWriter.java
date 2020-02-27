package com.dps0340.packetAnalyzer.database;

import java.util.Collection;
import java.util.concurrent.Callable;

public class DataWriter implements Callable {
    private Table table = null;
    private String query = null;
    private Collection<?> values = null;

    public DataWriter(String tableName) {
        this.table = new Table(tableName);
    }

    public DataWriter(String tableName, String dbName) {
        this.table = new Table(tableName, dbName);
    }

    public void injectQuery(String query) {
        this.query = query;
    }

    public void injectValues(Collection<?> values) {
        this.values = values;
    }

    public void changeTable(String tableName) {
        this.table = new Table(tableName);
    }

    public void changeTable(String tableName, String dbName) {
        this.table = new Table(tableName, dbName);
    }

    @Override
    public Object call() throws Exception {
        return table.exec(query, values);
    }
}