package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * this class handels the users
 */
public class UserDaoMem implements UserDao{

    private List<User> DATA = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {
    }

    /** Only a single version is allowed.  */
    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    /**
     * to add a user the the memory
     *
     * @param user to add
     */
    @Override
    public void add(User user) {
        user.setId(DATA.size() + 1);
        DATA.add(user);
    }

    /**
     * Find the user according to a string
     *
     * @param name to search for
     * @return returns the user object
     */
    @Override
    public User find(String name) {
        return DATA.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Find the user according to id
     *
     * @param id to search for
     * @return returns the user object
     */
    @Override
    public User find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * remove the user
     *
     * @param id to remover
     */
    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    /**
     * get all users
     *
     * @return a list of all users.
     */
    @Override
    public List<User> getAll() {
        return DATA;
    }
}
