package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Hardware;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    private static SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
    static ProductDao productDataStore = new ProductDaoJdbc();
    private static Supplier apple = new Supplier("apple","das");
    private static ProductCategory phones = new ProductCategory("apple","das","phone");
    private static Product phone = new Hardware("Iphone 7", 899.9f, "USD", "Latest product of Apple.", phones , apple, 12);
    private static Product phone2 = new Hardware("Iphone2 7", 899.9f, "USD", "Latest product of Apple.", phones , apple, 12);

    @BeforeEach
    public void initializeTestRequirements(){
        List<Integer> productIdList = new ArrayList<>();
        for (Product product: productDataStore.getAll()) {
            productIdList.add(product.getId());
        }
        for (Integer id: productIdList){
            productDataStore.remove(id);
        }
        List<Integer> supplierIdList = new ArrayList<>();
        for (Supplier supplier: supplierDataStore.getAll()) {
            supplierIdList.add(supplier.getId());
        }
        for (Integer id: supplierIdList){
            supplierDataStore.remove(id);
        }
        List<Integer> productCategoryIdList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryDataStore.getAll()) {
            productCategoryIdList.add(productCategory.getId());
        }
        for (Integer id: productCategoryIdList){
            productCategoryDataStore.remove(id);
        }

    }

    @Test
    public void testAddNewProduct(){
        int size = productDataStore.getAll().size();
        productCategoryDataStore.add(phones);
        supplierDataStore.add(apple);
        productDataStore.add(phone);
        assertEquals(phone.getName(), productDataStore.getAll().get(size).getName());
    }

    @Test
    public void testFindProduct(){
        productCategoryDataStore.add(phones);
        supplierDataStore.add(apple);
        productDataStore.add(phone);
        assertEquals(phone.getName(), productDataStore.find(phone.getId()).getName());
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
        productCategoryDataStore.add(phones);
        supplierDataStore.add(apple);
        productDataStore.add(phone);
        productDataStore.add(phone2);
        List<Product> products = new ArrayList<>();
        products.add(phone);
        products.add(phone2);
        assertEquals(products.size(), productDataStore.getAll().size());
    }
}