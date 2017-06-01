package com.codecool.shop.model;

import java.util.ArrayList;

public class ProductCategory extends BaseModel {
    private String department;
    private ArrayList<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    /**
     * create a product category
     *
     * @param id optional
     * @param name
     * @param department
     * @param description
     */
    public ProductCategory(int id, String name, String department, String description ) {
        super(id, name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    /**
     * get the department object
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     * set the department object
      * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
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
     * add a new product to the product category
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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }

}