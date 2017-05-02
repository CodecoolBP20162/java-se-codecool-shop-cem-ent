package com.codecool.shop.model;

public class Clothing extends Product implements Warranty {
    public Clothing(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier);
    }

    @Override
    public int warrantyMonths() {
        return 0;
    }
}
