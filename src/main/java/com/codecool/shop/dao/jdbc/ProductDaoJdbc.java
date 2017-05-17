package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    @Override
    public void add(Product product) {
        try{
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            String query = "INSERT INTO product VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getPrice());
            ps.setString(4, product.getDefaultCurrency().toString());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getSupplier().getId());
            ps.setInt(7, product.getProductCategory().getId());

            ps.executeQuery();
        }
        catch (IOException|SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM products WHERE id=" + id + ";";
        SupplierDaoJdbc supplier = SupplierDaoJdbc.getInstance();
        ProductCategoryDaoJdbc productCategory = ProductCategoryDaoJdbc.getInstance();


        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory.find(resultSet.getInt("productcategory")),
                        supplier.find(resultSet.getInt("supplier")));
                return product;
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id=" + id + ";";
        DbConnection connection = new DbConnection();
        connection.executeQuery(query);
    }


    @Override
    public List<Product> getAll() {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products";
        SupplierDaoJdbc supplier = SupplierDaoJdbc.getInstance();
        ProductCategoryDaoJdbc productCategory = ProductCategoryDaoJdbc.getInstance();

        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory.find(resultSet.getInt("productcategory")),
                        supplier.find(resultSet.getInt("supplier")));
                productList.add(product);
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products WHERE supplier=" + supplier.getId() + ";";
        ProductCategoryDaoJdbc productCategory = ProductCategoryDaoJdbc.getInstance();

        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory.find(resultSet.getInt("productcategory")),
                        supplier);
                productList.add(product);
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products WHERE productcategory=" + productCategory.getId() + ";";
        SupplierDaoJdbc supplier = SupplierDaoJdbc.getInstance();

        try {
            DbConnection connection = new DbConnection();
            Connection db = connection.getConnection();
            Statement statement = db.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier.find(resultSet.getInt("supplier")));
                productList.add(product);
            }
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }
}
