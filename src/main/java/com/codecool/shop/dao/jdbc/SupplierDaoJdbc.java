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
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Supplier find(int id) {

        String QUERY = "SELECT * FROM suppliers WHERE id=?;";
        try {
            PreparedStatement pstmt = connection.getConnection().prepareStatement(QUERY);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Supplier result = new Supplier(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

        @Override
        public void remove ( int id){
            String QUERY = "DELETE FROM suppliers WHERE id =?;";
            try {
                PreparedStatement pstmt = connection.getConnection().prepareStatement(QUERY);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        @Override
        public List<Supplier> getAll () {

            List<Supplier> suppliers = new ArrayList<>();
            String QUERY = "SELECT * FROM suppliers";

            try (PreparedStatement pstmt = connection.getConnection().prepareStatement(QUERY);
                 ResultSet resultSet = pstmt.executeQuery();
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



