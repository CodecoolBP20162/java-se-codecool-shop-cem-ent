package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    public void testAddNewProductCategory(){
        UserDao userDataStore = UserDaoJdbc.getInstance();
        User admin = new User("admin", "admin", 1);
        userDataStore.add(admin);
        assertEquals(admin.toString(), userDataStore.getAll().get(0).toString());
    }

}