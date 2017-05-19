package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ObjectArrayArguments;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {


    static Stream<Arguments> arguments(){
        return Stream.of(
                ObjectArrayArguments.create(UserDaoJdbc.getInstance()),
        ObjectArrayArguments.create(UserDaoMem.getInstance())
        );
    }

    @ParameterizedTest
    @MethodSource(names = { "arguments" })
    void testAddNewUser(UserDao userDataStore){
        User admin = new User("admin", "admin", 1);
        admin.setId(1);
        userDataStore.add(admin);
        int tempsize = userDataStore.getAll().size();
        assertEquals(admin.toString(), userDataStore.getAll().get(tempsize-1).toString());
    }


    @ParameterizedTest
    @MethodSource(names = { "arguments" })
    public void testFindUserById(UserDao userDataStore){
        User user = new User("admin", "admin", 1);
        user.setId(1);
        assertEquals(user.toString(), userDataStore.find(1).toString());
    }

    @ParameterizedTest
    @MethodSource(names = { "arguments" })
    public void testFindUserByName(UserDao userDataStore){
        User user = new User("admin", "admin", 1);
        user.setId(1);
        assertEquals(user.toString(), userDataStore.find("admin").toString());
    }

    @ParameterizedTest
    @MethodSource(names = { "arguments" })
    public void testRemoveUser(UserDao userDataStore){
        int firstsize = userDataStore.getAll().size();
        userDataStore.remove((firstsize==1)?1: firstsize - 1);
        int secondsize = userDataStore.getAll().size();
        assertEquals(1, firstsize - secondsize);
    }

    @ParameterizedTest
    @MethodSource(names = { "arguments" })
    public void testGetAllUser(UserDao userDataStore){
        List<ProductCategory> user = new ArrayList<>();
        assertEquals(user, userDataStore.getAll());
    }
}