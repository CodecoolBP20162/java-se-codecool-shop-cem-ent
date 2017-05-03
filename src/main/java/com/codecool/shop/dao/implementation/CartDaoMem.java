package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao{

    private List<Object> carts = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {}


    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return (CartDaoMem) instance.carts;
    }


    @Override
    public void add(LineItem lineItem) {
    }

    @Override
    public LineItem find(int id) {
        return carts.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }
}
