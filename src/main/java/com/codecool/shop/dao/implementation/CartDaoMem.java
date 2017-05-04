package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao{

    private List<Cart> DATA = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {}


    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return (CartDaoMem) instance;
    }


    @Override
    public void add(Cart cart) {
        DATA.add(cart);
    }

    @Override
    public Cart find(int id){
        return null;
    }

    /*public Cart find(int id) {
        return DATA.stream().filter(t -> t.getId == id).findFirst().orElse(null);
        return null;}*/


    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Cart> getAll() {
        return DATA;
    }
}
