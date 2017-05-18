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

class SupplierDaoTest {


        private static SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
        //private static SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
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
        List<Supplier> vmi = supplierDataStore.getAll();
        assertEquals(amazon.getName(), vmi.get(size).getName());
    }

    @Test
    void testFindSupplier(){
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        int id = amazon.getId();
        assertEquals(amazon.getName(), supplierDataStore.find(id).getName());
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