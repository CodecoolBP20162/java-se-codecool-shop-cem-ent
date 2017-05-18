package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    static UserDao userDataStore = UserDaoMem.getInstance();

    @BeforeEach
    public void initializeTestRequirements(){
        List<Integer> productIdList = new ArrayList<>();
        for (User user : userDataStore.getAll()) {
            productIdList.add(user.getId());
        }
        for (Integer id: productIdList){
            userDataStore.remove(id);
        }
    }

    @Test
    public void testAddNewUser(){
        UserDao userDataStore = UserDaoJdbc.getInstance();
        User admin = new User("admin", "admin", 1);
        admin.setId(1);
        userDataStore.add(admin);
        assertEquals(admin.toString(), userDataStore.getAll().get(0).toString());
    }

    //needs to be looked at
    @Test
    public void testFindProductCategory(){
        User user = new User("user", "pass", 3);
        userDataStore.add(user);
        assertEquals(1, userDataStore.getAll().size());
    }

    @Test
    public void testRemoveProductCategory(){
        User user = new User("admin2", "admin2", 1);
        userDataStore.add(user);
        userDataStore.remove(1);
        assertEquals(0, userDataStore.getAll().size());
    }

    @Test
    public void testGetAllProductCategory(){
        List<ProductCategory> productCategories = new ArrayList<>();
        assertEquals(productCategories, userDataStore.getAll());
    }
}