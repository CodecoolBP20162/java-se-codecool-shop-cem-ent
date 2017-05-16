package com.codecool.shop.dao.jdbc;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.List;

public class UserDaoJdbc implements UserDao {
    @Override
    public void add(User user) {
        String userid = "SELECT id FROM users ORDER BY id DESC LIMIT 1";
        //get last element from DB and increment it.
        user.setId(DATA.size() + 1);
        String query = "INSERT INTO users (name, password, rank) " +
                "VALUES ('" + user.getName() + "', '" + user.getPassword() + "', '" + user.getRank() + "');";
        DbConnection.executeQuery(query);
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
