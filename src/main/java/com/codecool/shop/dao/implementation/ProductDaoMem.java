package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class is handling all the products
 */
public class ProductDaoMem implements ProductDao {

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoMem instance = null;

    private ProductDaoMem() {
    }

    /** Only a single version is allowed.  */
    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    /**
     * adds a produte
     *
     * @param product to be added
     */
    @Override
    public void add(Product product) {
        product.setId(DATA.size() + 1);
        DATA.add(product);
    }

    /**
     * finds the product according to id
     *
     * @param id searches te product according to this
     * @return returns the found product
     */
    @Override
    public Product find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * removes the product
     *
     * @param id removes the product according to the id
     */
    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    /**
     * gets all the products
     *
     * @return returns all the products as a list
     */
    @Override
    public List<Product> getAll() {
        return DATA;
    }

    /**
     * gets the list of products by supplier
     *
     * @param supplier to search for
     * @return returns the list of products according to the supplier
     */
    @Override
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    /**
     * gets the product by category
     *
     * @param productCategory the category to search for
     * @return returns the products in that category.
     */
    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
