package com.codecool.shop.model;


public class Parts extends Product implements Orderable {
    public Parts(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier );
    }

    @Override
    public boolean inStock() {
        return true;
    }
}
