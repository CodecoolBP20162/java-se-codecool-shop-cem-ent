package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
//import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
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

    private static ProductController instance = null;
    private ProductController() {}

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = new ProductDaoJdbc();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public ModelAndView renderProductsbyCategory(Request req, Response res) {
        int categoryID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = new ProductDaoJdbc();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(params, "product/index");
    }

    public ModelAndView renderProductsbySupplier(Request req, Response res) {
        int supplierID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = new ProductDaoJdbc();
        SupplierDao productSupplierDataStore = SupplierDaoJdbc.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(params, "product/index");
    }

    private Map<String, Object> getCommonParams(Request req) {
        CartController cartController = CartController.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        Cart cartDataStore = cartController.getCart(req);

        Map<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("numberOfProdutsInCart", cartDataStore.getAllQuantity());
        return params;
    }
}
