package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    private static Supplier amazon = new Supplier("Amazon", "Digital content and services");
    private static Supplier lenovo = new Supplier("Lenovo", "Computers");

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
        supplierDataStore.add(amazon);
        assertEquals(amazon, supplierDataStore.getAll().get(0));
    }

    @Test
    void testFindSupplier(){
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        assertEquals(lenovo, supplierDataStore.find(2));
    }

    @Test
    void testRemoveSupplier(){
        supplierDataStore.add(amazon);
        supplierDataStore.remove(1);
        assertEquals(0, supplierDataStore.getAll().size());
    }

    @Test
    void testGetAllSuppliers(){
        List<Supplier> suppliers = new ArrayList<>();
        assertEquals(suppliers, supplierDataStore.getAll());
    }



}