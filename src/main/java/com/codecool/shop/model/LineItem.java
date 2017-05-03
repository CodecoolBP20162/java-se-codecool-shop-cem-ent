package com.codecool.shop.model;



public class LineItem {
    int quantity;
    float price;

    public LineItem(Product product) {
        quantity = 1;
        price = product.getDefaultPrice() * quantity;
    }
}
