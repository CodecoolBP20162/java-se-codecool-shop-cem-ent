package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.*;
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Supplier find(int id) {

        return null;
    }

        //String query = "SELECT * FROM todos WHERE id ='" + id + "';";
       /* try (Connection connection = getConnection();
    Statement statement =connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
        ){
        if (resultSet.next()){
            Todo result = new Todo(resultSet.getString("title"),
                    resultSet.getString("id"),
                    Status.valueOf(resultSet.getString("status")));
            return result;
        } else {
            return null;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

        return null;*/



    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
