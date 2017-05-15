package com.codecool.shop.model;


public class LineItem {
    private final Product product;
    private int quantity = 1;

    public LineItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    void incrementQuantity() {
        this.quantity += 1;
    }

    float getPrice() {
        return product.getDefaultPrice() * quantity;
    }

}
