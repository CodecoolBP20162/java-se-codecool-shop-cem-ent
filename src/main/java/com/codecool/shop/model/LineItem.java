package com.codecool.shop.model;



public class LineItem {
    Product product;
    int quantity;
    float price;
    int id;

    public LineItem(Product product) {
        this.id = 99999;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getDefaultPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
