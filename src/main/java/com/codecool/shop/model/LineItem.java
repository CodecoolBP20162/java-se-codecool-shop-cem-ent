package com.codecool.shop.model;


/**
 * Line item is used to set how many are of a product
 */
public class LineItem {
    private final Product product;
    private int quantity = 1;

    /**
     * set the product
     * @param product
     */
    public LineItem(Product product) {
        this.product = product;
    }

    /**
     * get the price
     * @return
     */
    public float getPrice() {
        return product.getDefaultPrice() * quantity;
    }

    /**
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     *
     * @return how many of the products have been added
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity the quantity to be set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * add 1 to the quantity of the item.
     */
    public void incrementQuantity() {
        this.quantity += 1;
    }
    
}
