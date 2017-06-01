package com.codecool.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class handles the Database connection.
 * you need to have a file at "src/main/resources/connection.properties"
 * containing the da the host, the database name, your name and your password.
 */
public class DbConnection {

    private static final Logger logger = LoggerFactory.getLogger (DbConnection.class);

    public Connection getConnection() throws IOException, SQLException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/connection.properties"));
        final String DATABASE = "jdbc:postgresql://" + br.readLine() + "/" + br.readLine();
        final String DB_USER = br.readLine();
        final String DB_PASSWORD = br.readLine();
        br.close();

        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);
        }
        catch (SQLException e) {
            logger.warn("The database connection could not be made.");
            e.printStackTrace();
        }
        catch (IOException e) {
            logger.warn("The file 'connection.properties' does not exist.");
            e.printStackTrace();
        }

    }
}