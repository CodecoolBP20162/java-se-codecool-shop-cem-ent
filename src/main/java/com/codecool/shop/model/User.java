package com.codecool.shop.model;


import java.util.ArrayList;

public class User extends BaseModel {
    private ArrayList<User> users;
    private String password;
    private int type;

    public User(String name, String password, String description) {
        super(name);
        this.password = password;
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

    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "password: %3$s" +
                        "type: %4$d" +
                        "description: %5$s",
                this.id,
                this.name,
                this.password,
                this.type,
                this.description
        );

    }
}
