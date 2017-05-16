package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsbyCategory(Request req, Response res) {
        int categoryID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsbySupplier(Request req, Response res) {
        int supplierID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(params, "product/index");
    }

    private static Map<String, Object> getCommonParams(Request req) {
        SupplierDao productSupplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        Cart cartDataStore = CartController.getCart(req);

        Map<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("numberOfProdutsInCart", cartDataStore.getAllQuantity());
        return params;
    }
}
