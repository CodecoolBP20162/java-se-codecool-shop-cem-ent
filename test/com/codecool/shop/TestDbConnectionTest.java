package com.codecool.shop;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TestDbConnectionTest {

    public String filePath = "";


    public Connection getConnection() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));
            final String DATABASE = "jdbc:postgresql://" + br.readLine() + "/" + br.readLine();
            final String DB_USER = br.readLine();
            final String DB_PASSWORD = br.readLine();
            br.close();
            return DriverManager.getConnection(
                        DATABASE,
                        DB_USER,
                        DB_PASSWORD);
            }
        catch(IOException|SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Test
    public void testTestDbConnectionCanConnect() {
        this.filePath = "src/main/resources/test_db_connection.properties";
        assertNotNull(this.getConnection());
    }

}