package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.LineItem;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao{

    private List<LineItem> DATA = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {}


    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return (CartDaoMem) instance;
    }


    @Override
    public void add(LineItem lineItem) {
        DATA.add(lineItem);
    }

    @Override
    public LineItem find(int id) {
//        return DATA.stream().filter(t -> t.getId == id).findFirst().orElse(null);
        return null;
    }

    @Override
    public void remove(int id) {
//        DATA.remove(find(id));
    }

    @Override
    public List<LineItem> getAll() {
        return DATA;
    }
}
