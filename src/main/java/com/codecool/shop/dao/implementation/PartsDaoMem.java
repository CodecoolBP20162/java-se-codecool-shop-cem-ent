package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.PartsDao;
import com.codecool.shop.model.Parts;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PartsDaoMem implements PartsDao {

    private List<Parts> DATA = new ArrayList<>();
    private static PartsDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private PartsDaoMem() {
    }

    public static PartsDaoMem getInstance() {
        if (instance == null) {
            instance = new PartsDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Parts parts) {
        parts.setId(DATA.size() + 1);
        DATA.add(parts);
    }

    @Override
    public Parts find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Parts> getAll() {
        return DATA;
    }

    @Override
    public List<Parts> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Parts> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}