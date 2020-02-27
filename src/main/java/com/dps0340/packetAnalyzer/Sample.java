package com.dps0340.packetAnalyzer;

import com.dps0340.packetAnalyzer.database.ConnectionManager;
import com.dps0340.packetAnalyzer.database.Table;

import java.sql.SQLException;
import java.util.ArrayList;

public class Sample {

    public static void main(String[] args) {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Table table = new Table("Number");
        String tableName = table.getTableName();
        table.exec("CREATE TABLE IF NOT EXISTS " + tableName + " ( id int, ctx varchar(255) );");
        for(int i = 1; i<1024; i *= 2) {
            final int num = i;
            table.exec("INSERT INTO " + tableName + " VALUES (?, ?);", new ArrayList<Object>() {
                {
                    add(table.nextInt());
                    add(Integer.toString(num));
                }
            });
            System.out.println(table.nextInt());
        }
    }
}
