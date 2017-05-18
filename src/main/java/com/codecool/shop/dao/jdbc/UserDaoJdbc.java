package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    DbConnection connection = new DbConnection();
    private static UserDaoJdbc instance = null;


    private UserDaoJdbc() {
    }

    public static UserDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        //get the highest ID in the user DB.
        //propably not needed as the primary key is incrementing itself.
        /*
        String query = "SELECT id FROM users ORDER BY id DESC LIMIT 1";

        try (Connection conn = connection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                int result = resultSet.getInt("id");
            } else {
                int result = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //insert the new user into the DB.

        final String INSERT_QUERY = "INSERT INTO users (name, password, rank) VALUES (?,?,?);";
        String[] columnsToReturn = {"id"};
        try {
            PreparedStatement pstmt = connection.getConnection().prepareStatement(INSERT_QUERY, columnsToReturn);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRank());
            pstmt.executeUpdate();

            // to get id of created row
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User find(int id) {

        String query = "SELECT * FROM users WHERE id ='" + id + "';";
        try (Connection conn = connection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                User result = new User(resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getInt("rank"));
                result.setId(id);
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
    public User find(String name) {
        String query = "SELECT * FROM users WHERE name ='" + name + "';";
        try (Connection conn = connection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                User result = new User(name,
                        resultSet.getString("password"),
                        resultSet.getInt("rank"));
                result.setId(resultSet.getInt("id"));
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
    public void remove(int id) {
        /* previous version
        String query = "DELETE FROM users WHERE id = '" + id +"';";
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
        */
        final String DELETE_QUERY = "DELETE FROM users WHERE (id) VALUES (?)";

        try {
            PreparedStatement pstmt = connection.getConnection().prepareStatement(DELETE_QUERY);
            pstmt.setInt(1, id);
            pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users;";
        try (Connection conn = connection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
//            if (resultSet.first()){
  //              resultSet.beforeFirst();
                List<User> result = new ArrayList<>();
                while (resultSet.next()) {
                    User tempuser = new User(resultSet.getString("name"),
                            resultSet.getString("password"),
                            resultSet.getInt("rank"));
                    tempuser.setId(resultSet.getInt("id"));
                    result.add(tempuser);
                }
                return result;

     //       } else {
       //         return null;
         //   }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
