package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SupplierDaoJdbc implements SupplierDao {

    DbConnection connection = new DbConnection();
    private static SupplierDaoJdbc instance = null;


    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

        final String INSERT_QUERY = "INSERT INTO suppliers (name, description) VALUES (?,?);";

        try {
            PreparedStatement pstmt = connection.getConnection().prepareStatement(INSERT_QUERY);
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getDescription());
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM suppliers WHERE id ='" + id + "';";
        try (Connection conn = connection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            if (resultSet.next()) {
                Supplier result = new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

        @Override
        public void remove ( int id){
            String query = "DELETE FROM suppliers WHERE id = '" + id +"';";
            try {
                Connection conn = connection.getConnection();
                Statement statement = conn.createStatement();
                statement.executeQuery(query);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        @Override
        public List<Supplier> getAll () {

            List<Supplier> suppliers = new ArrayList<>();
            String query = "SELECT * FROM suppliers";

            try (Connection conn = connection.getConnection();
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(query);
            ) {
                while (resultSet.next()) {
                    Supplier supplier = new Supplier(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"));
                    suppliers.add(supplier);
                }
                return suppliers;

            } catch (SQLException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}



