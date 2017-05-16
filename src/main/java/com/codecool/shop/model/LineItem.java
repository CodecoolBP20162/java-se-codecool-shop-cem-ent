package com.codecool.shop.model;



public class LineItem {
    private final Product product;
    private int quantity = 1;

    public LineItem(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return product.getDefaultPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity += 1;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }

    
}
