package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Cart handles all the selected items
 */
public class Cart{
    Date created = new Date();
    private final List<LineItem> lineItems = new ArrayList<>();


    public void add(LineItem lineItem) {
        if (hasProduct(lineItem)) {
            findLineItem(lineItem).incrementQuantity();
        }
        else {
            lineItems.add(lineItem);
        }
    }

    /**
     * get all items from the object
     *
     * @return returns all elements
     */
    public List<LineItem> getAll() {
        return lineItems;
    }

    public void remove(LineItem lineItem) {
        lineItems.remove(findLineItem(lineItem));
    }

    /**
     * find item returns a specific item
     *
     * @param lineItem the item to search for
     * @return the found item
     */
    private LineItem findLineItem(LineItem lineItem) {
        return lineItems.stream()
                .filter(l -> l.getProduct().getId() == lineItem.getProduct().getId())
                .findFirst().orElse(null);
    }

    /**
     * returns if the product is in cart
     * @param lineItem the item to search for
     * @return true or false
     */
    private boolean hasProduct(LineItem lineItem) {
        return lineItems.stream()
                .anyMatch(li -> li.getProduct().getId() == lineItem.getProduct().getId());
    }

    /**
     * get the sum of the price
     * @return returns the sum
     */
    public float getSum() {
        float sum = 0;
        for (LineItem lineItem : lineItems) {
            sum += lineItem.getPrice();
        }
        return sum;
    }

    /**
     * get the quantity off all items
     * @return the quantity of all
     */
    public int getAllQuantity(){
        int allQuantity = 0;
        for (LineItem lineItem : lineItems) {
            allQuantity += lineItem.getQuantity();
        }
        return allQuantity;
    }
}
