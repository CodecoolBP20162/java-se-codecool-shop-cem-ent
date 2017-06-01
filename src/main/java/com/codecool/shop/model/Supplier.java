package com.codecool.shop.model;

import java.util.ArrayList;


public class Supplier extends BaseModel {
    private ArrayList<Product> products;

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }

    /**
     * constructor of the supplier object
     * @param id optional
     * @param name
     * @param description
     */
    public Supplier(int id, String name, String description) {
        super(id, name, description);
        this.products = new ArrayList<>();
    }


    /**
     * set the product
     * @param products
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * get the product
     * @return
     */
    public ArrayList getProducts() {
        return this.products;
    }

    /**
     * add a new product
     * @param product
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * represent the object as a string
     * @return
     */
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}