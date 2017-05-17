package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    @Override
    public void add(User user) {
        String userId = "SELECT id FROM users ORDER BY id DESC LIMIT 1";

        DbConnection.executeQuery(userId);

        //get last element from DB and increment it.
        user.setId(DATA.size() + 1);
        String query = "INSERT INTO users (name, password, rank) " +
                "VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getRank() + "');";
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
