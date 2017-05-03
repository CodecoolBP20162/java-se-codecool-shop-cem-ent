package com.codecool.shop.model;


public class Hardware extends Product {

    private int warranty;

    public Hardware(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, int warranty) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier );
        this.warranty = warranty;
    }
}
