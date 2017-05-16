package com.codecool.shop.dao;

import com.codecool.shop.model.Hardware;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;


public interface HardwareDao {

    void add(Hardware hardware);
    Hardware find(int id);
    void remove(int id);

    List<Hardware> getAll();
    List<Hardware> getBy(Supplier supplier);
    List<Hardware> getBy(ProductCategory productCategory);
}