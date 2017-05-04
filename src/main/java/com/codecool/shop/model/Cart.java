package com.codecool.shop.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public List<LineItem> getAll() {
        return lineItems;
    }

    public void remove(LineItem lineItem) {
        lineItems.remove(findLineItem(lineItem));
    }

    private LineItem findLineItem(LineItem lineItem) {
        return lineItems.stream()
                .filter(l -> l.getProduct().equals(lineItem.getProduct()))
                .findFirst().orElse(null);
    }

    private boolean hasProduct(LineItem lineItem) {
        return lineItems.stream()
                .anyMatch(li -> Objects.equals(li.getProduct(), lineItem.getProduct()));
    }
}
