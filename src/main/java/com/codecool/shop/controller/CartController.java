package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartController {

    public static void addItemToCart(Request req) {

        int addedProductId = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();

        Cart cartDataStore = getCart(req);

        LineItem lineItemCandidate = new LineItem(productDataStore.find(addedProductId));

        cartDataStore.add(lineItemCandidate);

        req.session().attribute("vmi", cartDataStore);

    }

    public static ModelAndView renderCart(Request req, Response res) {
        Cart cartDataStore = getCart(req);

        Map<String, List<LineItem>> params = new HashMap<>();
        params.put("lineitems", cartDataStore.getAll());

        return new ModelAndView(params, "cartview");
    }





    private static Cart getCart(Request req) {
        Cart cart = req.session().attribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.session().attribute("cart", cart);
        }
        return cart;
    }
}
