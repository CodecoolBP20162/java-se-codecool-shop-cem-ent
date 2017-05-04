package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.HardwareDao;
import com.codecool.shop.model.Hardware;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HardwareDaoMem implements HardwareDao {

    private List<Hardware> DATA = new ArrayList<>();
    private static HardwareDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private HardwareDaoMem() {
    }

    public static HardwareDaoMem getInstance() {
        if (instance == null) {
            instance = new HardwareDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Hardware hardware) {
        hardware.setId(DATA.size() + 1);
        DATA.add(hardware);
    }

    @Override
    public Hardware find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Hardware> getAll() {
        return DATA;
    }

    @Override
    public List<Hardware> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Hardware> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
