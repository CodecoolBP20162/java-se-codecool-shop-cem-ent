package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Software;
import com.codecool.shop.model.Supplier;

import java.util.List;


public interface SoftwareDao {
    void add(Software software);
    Software find(int id);
    void remove(int id);

    List<Software> getAll();
    List<Software> getBy(Supplier supplier);
    List<Software> getBy(ProductCategory productCategory);
}