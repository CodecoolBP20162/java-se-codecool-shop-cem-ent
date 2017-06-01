package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

/**
 * this class handels all the supliers
 */
public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    private SupplierDaoMem() {
    }

    /** Only a single version is allowed.  */
    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    /**
     * add the supplier to the list
     *
     * @param supplier supplier to be added
     */
    @Override
    public void add(Supplier supplier) {
        supplier.setId(DATA.size() + 1);
        DATA.add(supplier);
    }

    /**
     * find the supplier by id
     *
     * @param id to search for
     * @return returns the supplier
     */
    @Override
    public Supplier find(int id) {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    /**
     * removes the supplier
     *
     * @param id to remove
     */
    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    /**
     * get all suppliers
     *
     * @return a list of suppliers
     */
    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}
