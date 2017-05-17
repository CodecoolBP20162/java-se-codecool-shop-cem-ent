package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    static ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

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
    public void testAddNewSupplier(){
        ProductCategory notebooks = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
        productCategoryDataStore.add(notebooks);
        assertEquals(notebooks, productCategoryDataStore.getAll().get(0));
    }

    @Test
    public void testFindSupplier(){
        ProductCategory parts = new ProductCategory("Parts", "Parts", "Parts for different types of products.");
        productCategoryDataStore.add(parts);
        assertEquals(1, productCategoryDataStore.getAll().size());
    }

    @Test
    public void testRemoveProductCategory(){
        ProductCategory softwares = new ProductCategory("Software", "Software", "Programs and subscriptions");
        productCategoryDataStore.add(softwares);
        productCategoryDataStore.remove(1);
        assertEquals(0, productCategoryDataStore.getAll().size());
    }

    @Test
    public void testGetAllProductCategory(){
        List<ProductCategory> productCategories = new ArrayList<>();
        assertEquals(productCategories, productCategoryDataStore.getAll());
    }
}