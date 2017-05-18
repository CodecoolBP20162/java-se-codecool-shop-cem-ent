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

    private DbConnection dbConnection = new DbConnection();
    private SupplierDaoJdbc supplier = SupplierDaoJdbc.getInstance();
    private ProductCategoryDaoJdbc productCategory = ProductCategoryDaoJdbc.getInstance();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products (name, price, currency, description, productcategory, supplier) VALUES(?, ?, ?, ?, ?, ?)";

        String[] columnsToReturn = {"id"};

        try (Connection db = new DbConnection().getConnection();
                PreparedStatement ps = db.prepareStatement(query, columnsToReturn)) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getDefaultPrice());
            ps.setString(3, product.getDefaultCurrency().toString());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getProductCategory().getId());
            ps.setInt(6, product.getSupplier().getId());
            ps.executeUpdate();

            // to get id of created row
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            product.setId(generatedKeys.getInt("id"));
        }
        catch (IOException|SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM products WHERE id=?;";

        try {
            PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            pstmt.close();
            return (resultSet.next()) ? createProduct(resultSet) : null;
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id=?;";
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
    public List<Product> getAll() {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products";

        try (PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {
            while (resultSet.next()) {
                productList.add(createProduct(resultSet));
            }
            return productList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products WHERE supplier=?;";

        try {PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
             pstmt.setInt(1, supplier.getId());
             ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                productList.add(createProduct(resultSet));
            }
            pstmt.close();
            return productList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        LinkedList<Product> productList = new LinkedList<>();
        String query = "SELECT * FROM products WHERE productcategory=?;";

        try {PreparedStatement pstmt = dbConnection.getConnection().prepareStatement(query);
            pstmt.setInt(1, productCategory.getId());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                productList.add(createProduct(resultSet));
            }
            pstmt.close();
            return productList;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getFloat("price"),
                    resultSet.getString("currency"),
                    resultSet.getString("description"),
                    productCategory.find(resultSet.getInt("productcategory")),
                    supplier.find(resultSet.getInt("supplier")));
        }

    }
