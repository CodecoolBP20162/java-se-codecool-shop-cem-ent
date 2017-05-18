package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SupplierDaoJdbc implements SupplierDao {

    private DbConnection dbConnection = new DbConnection();
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
        String[] columnsToReturn = {"id"};

        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(INSERT_QUERY, columnsToReturn)) {

            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getDescription());
            pstmt.executeUpdate();

            // to get id of created row
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();
            supplier.setId(generatedKeys.getInt("id"));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {

        String QUERY = "SELECT * FROM suppliers WHERE id=?;";
        try {
            PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(QUERY);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            return (resultSet.next()) ? createSupplier(resultSet) : null;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String QUERY = "DELETE FROM suppliers WHERE id =?;";
        try {
            PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(QUERY);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {

        List<Supplier> suppliers = new ArrayList<>();
        String QUERY = "SELECT * FROM suppliers";

        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(QUERY);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                suppliers.add(createSupplier(resultSet));
            }
            return suppliers;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Supplier createSupplier(ResultSet resultSet) throws SQLException {
        return new Supplier(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"));

    }
}



