package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SoftwareDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Software;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SoftwareDaoMem implements SoftwareDao {

    private List<Software> DATA = new ArrayList<>();
    private static SoftwareDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SoftwareDaoMem() {
    }

    public static SoftwareDaoMem getInstance() {
        if (instance == null) {
            instance = new SoftwareDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Software software) {
        software.setId(DATA.size() + 1);
        DATA.add(software);
    }

    @Override
    public Software find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Software> getAll() {
        return DATA;
    }

    @Override
    public List<Software> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Software> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
