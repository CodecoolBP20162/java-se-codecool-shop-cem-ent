package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {

    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    static ProductCategory notebooks = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
    static ProductCategory parts = new ProductCategory("Parts", "Parts", "Parts for different types of products.");


    @BeforeEach
    public void initializeTestRequirements(){
        List<Integer> productIdList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryDataStore.getAll()) {
            productIdList.add(productCategory.getId());
        }
        for (Integer id: productIdList){
            productCategoryDataStore.remove(id);
        }
    }

    @Test
    public void testAddNewProductCategory(){
        int size = productCategoryDataStore.getAll().size();
        productCategoryDataStore.add(notebooks);
        assertEquals(notebooks.getName(), productCategoryDataStore.getAll().get(size).getName());
    }

    @Test
    public void testFindProductCategory(){
        productCategoryDataStore.add(parts);
        productCategoryDataStore.add(notebooks);
        assertEquals(notebooks.getName(), productCategoryDataStore.find(notebooks.getId()).getName());
    }

    @Test
    public void testRemoveProductCategory(){
        productCategoryDataStore.add(notebooks);
        productCategoryDataStore.remove(notebooks.getId());
        assertEquals(0, productCategoryDataStore.getAll().size());
    }

    @Test
    public void testGetAllProductCategory(){
        productCategoryDataStore.add(notebooks);
        productCategoryDataStore.add(parts);
        List<ProductCategory> categories= new ArrayList<>();
        categories.add(notebooks);
        categories.add(parts);
        assertEquals(categories.size(), productCategoryDataStore.getAll().size());
    }
}