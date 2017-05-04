package com.codecool.shop.dao;

import com.codecool.shop.model.Parts;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface PartsDao {
    void add(Parts parts);
    Parts find(int id);
    void remove(int id);

    List<Parts> getAll();
    List<Parts> getBy(Supplier supplier);
    List<Parts> getBy(ProductCategory productCategory);
}