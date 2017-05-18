package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.jdbc.ProductDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Hardware;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    static ProductDao productDataStore = ProductDaoMem.getInstance();
    //static ProductDao productDataStore = new ProductDaoJdbc();
    private static Supplier apple = new Supplier("apple","das");
    private static ProductCategory phones = new ProductCategory("apple","das","phone");
    private static Product phone = new Hardware("Iphone 7", 899.9f, "USD", "Latest product of Apple.", phones , apple, 12);

    @BeforeEach
    public void initializeTestRequirements(){
        List<Integer> productIdList = new ArrayList<>();
        for (Product product: productDataStore.getAll()) {
            productIdList.add(product.getId());
        }
        for (Integer id: productIdList){
            productDataStore.remove(id);
        }
    }

    @Test
    public void testAddNewProduct(){
        int size = productDataStore.getAll().size();
        productDataStore.add(phone);
        assertEquals(phone.getName(), productDataStore.getAll().get(size).getName());
    }

    @Test
    public void testFindProduct(){
        Product phone = new Hardware("Iphone 7", 899.9f, "USD", "Latest product of Apple.", phones , apple, 12);
        productDataStore.add(phone);
        assertEquals(1, productDataStore.getAll().size());
    }

    @Test
    public void testRemoveProduct(){
        Product phone = new Hardware("Iphone 7", 899.9f, "USD", "Latest product of Apple.", phones , apple, 12);
        productDataStore.add(phone);
        productDataStore.remove(1);
        assertEquals(0, productDataStore.getAll().size());
    }

    @Test
    public void testGetAllProduct(){
        List<Product> products = new ArrayList<>();
        assertEquals(products, productDataStore.getAll());
    }
}