package com.codecool.shop.model;

public class Electronics extends Product implements Peripherals, Warranty{

    public Electronics(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier);
    }

    @Override
    public String otlet() {
        return null;
    }

    @Override
    public int deviceSize() {
        return 0;
    }

    @Override
    public int warrantyMonths() {
        return 0;
    }
}
