package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {


    private static SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
    static Supplier amazon = new Supplier("Amazon", "Digital content and services");
    static Supplier lenovo = new Supplier("Lenovo", "Computers");

    @BeforeEach
    void initializeTestRequirements(){

        List<Integer> supplierIdList = new ArrayList<>();
        for (Supplier supplier: supplierDataStore.getAll()) {
            supplierIdList.add(supplier.getId());
        }
        for (Integer id: supplierIdList){
            supplierDataStore.remove(id);
        }
    }

    @Test
    void testAddNewSupplier(){
        int size = supplierDataStore.getAll().size();
        supplierDataStore.add(amazon);
        assertEquals(amazon.getName(), supplierDataStore.getAll().get(size).getName());
    }

    @Test
    void testFindSupplier(){
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        assertEquals(amazon.getName(), supplierDataStore.find(amazon.getId()).getName());
    }


    @Test
    void testRemoveSupplier(){
        supplierDataStore.add(amazon);
        supplierDataStore.remove(amazon.getId());
        assertEquals(0, supplierDataStore.getAll().size());
    }

    @Test
    void testGetAllSuppliers(){
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(amazon);
        suppliers.add(lenovo);
        assertEquals(suppliers.size(), supplierDataStore.getAll().size());
    }



}