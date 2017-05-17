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

    DbConnection connection = new DbConnection();
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
        try{
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            String query = "INSERT INTO product VALUES(?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDepartment());
            ps.setString(3, category.getDescription());

            ps.executeQuery();
        }
        catch (IOException |SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public ProductCategory find(int id) {

        String query = "SELECT * FROM productcategories WHERE id=" + id + ";";

        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                ProductCategory category = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                return category;
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategories WHERE id=" + id + ";";
        DbConnection connection = new DbConnection();
        connection.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        LinkedList<ProductCategory> productCategoryList = new LinkedList<>();
        String query = "SELECT * FROM productcategories";

        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                ProductCategory category = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                productCategoryList.add(category);
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return productCategoryList;
    }
}
