package com.codecool.shop.model;


import java.util.ArrayList;

public class User {
    protected int id;
    protected String name;
    private String password;
    private int rank;
    private ArrayList<User> users;
    public User(String name, String password, int rank) {
        this.name = name;
        this.password = password;
        this.rank = rank;
        this.users = new ArrayList<>();
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList getUser() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "password: %3$s" +
                        "rank: %4$d",
                this.id,
                this.name,
                this.password,
                this.rank
        );

    }
}
