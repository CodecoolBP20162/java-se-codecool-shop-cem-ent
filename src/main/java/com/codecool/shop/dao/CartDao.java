package com.codecool.shop.dao;


import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface CartDao {
    void add(LineItem lineItem);
    LineItem find(int id);
    void remove(int id);

    List<LineItem> getAll();

}
