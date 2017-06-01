package com.codecool.shop.model;


import java.util.ArrayList;

public class User {
    protected int id;
    protected String name;
    private String password;
    private int rank;
    private ArrayList<User> users;

    /**
     * constructor of users
     * @param name
     * @param password
     * @param rank
     */
    public User(String name, String password, int rank) {
        this.name = name;
        this.password = password;
        this.rank = rank;
    }

    /**
     * add a list of users not used
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * returns all users
     * @return
     */
    public ArrayList getUser() {
        return this.users;
    }

    /**
     * add a user
     * @param user
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    /**
     * get id of the user
     * @return
     */
    public int getId() {return id;}

    /**
     * set is
     * @param id
     */
    public void setId(int id) {this.id = id;}

    /**
     * get the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the new password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get the rank
     * @return
     */
    public int getRank() {
        return rank;
    }

    /**
     * set the rank
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * represent the object as a string
     * @return
     */
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "password: %3$s, " +
                        "rank: %4$d",
                this.id,
                this.name,
                this.password,
                this.rank
        );

    }
}
