package com.codecool.shop.model;


public class Software extends Product {

    private int duration;

    public Software(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, int duration) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier );
        this.duration = duration;
    }
}
