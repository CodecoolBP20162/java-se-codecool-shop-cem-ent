package com.codecool.shop.model;


public class Hardware extends Product implements Orderable {

    private int warranty;

    /**
     * creates a hardware object
     *
     * @param name of the item
     * @param defaultPrice the price
     * @param currencyString the currency
     * @param description a short description of the item
     * @param productCategory the category of the item
     * @param supplier
     * @param warranty the time how long the warrenty is valid
     */
    public Hardware(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier, int warranty) {
        super(name, defaultPrice, currencyString, description, productCategory, supplier );
        this.warranty = warranty;
    }

    @Override
    public boolean inStock() {
        return true;
    }

}
