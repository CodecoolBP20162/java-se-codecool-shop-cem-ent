package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * The products are stored in this class
 */
public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    private ProductCategoryDaoMem() {
    }

    /** Only a single version is allowed.  */
    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    /**
     * add product category to the memory dao
     * @param category adds a category to the memory Array.
     */
    @Override
    public void add(ProductCategory category) {
        category.setId(DATA.size() + 1);
        DATA.add(category);
    }

    /**
     * find the product category according to the ID
     *
     * @param id the id to search for in the array
     * @return returns the product
     */
    @Override
    public ProductCategory find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * removes the product
     *
     * @param id removes the product
     */
    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    /**
     * get all of the product category
     *
     * @return
     */
    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}
