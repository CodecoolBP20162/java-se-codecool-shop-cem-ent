package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.LineItem;
import org.json.simple.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CartController {

    public static JSONObject addItemToCart(Request req, Response res) {
        int addedProductId = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Cart cartDataStore = getCart(req);

        LineItem lineItemCandidate = new LineItem(productDataStore.find(addedProductId));
        cartDataStore.add(lineItemCandidate);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("numberOfProductsInCart", cartDataStore.getAllQuantity());
        res.type("application/json");
        return jsonObj;
    }

    public static ModelAndView renderCart(Request req, Response res) {
        Cart cartDataStore = getCart(req);
        Map<String, Object> params = new HashMap<>();
        params.put("lineitems", cartDataStore.getAll());
        params.put("sum", cartDataStore.getSum());
        return new ModelAndView(params, "cartview");
    }

    public static Cart getCart(Request req) {
        Cart cart = req.session().attribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.session().attribute("cart", cart);
        }
        return cart;
    }
}
