package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    DbConnection dbConnection = new DbConnection();
    private static ProductCategoryDaoJdbc instance = null;


    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }



    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO productcategories (name, department, description) VALUES(?, ?, ?)";
        String[] columnsToReturn = {"id"};

        // connection and preparedstatement are autoclosable, if instantiated in try

        try (Connection connection =  dbConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(query, columnsToReturn)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDepartment());
            ps.setString(3, category.getDescription());

            ps.executeUpdate();

            // to get id of created row
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            category.setId(generatedKeys.getInt("id"));
        }
        catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public ProductCategory find(int id) {

        String query = "SELECT * FROM productcategories WHERE id=?;";

        try {
            PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            pstmt.close();
            return (resultSet.next()) ? createProductCategory(resultSet) : null;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategories WHERE id=?;";
        try {
            PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        LinkedList<ProductCategory> productCategoryList = new LinkedList<>();
        String query = "SELECT * FROM productcategories";

        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                productCategoryList.add(createProductCategory(resultSet));
            }
            return productCategoryList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ProductCategory createProductCategory(ResultSet resultSet) throws SQLException {
        return new ProductCategory(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("department"),
                resultSet.getString("description"));
    }
}
