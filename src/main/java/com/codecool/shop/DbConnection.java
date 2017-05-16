package com.codecool.shop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DbConnection {

    private Connection getConnection() throws IOException, SQLException {

        BufferedReader br = new BufferedReader(new FileReader("connect.txt"));
        final String DATABASE = br.readLine();
        final String DB_USER = br.readLine();
        final String DB_PASSWORD = br.readLine();
        br.close();

        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}