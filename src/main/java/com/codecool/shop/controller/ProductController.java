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

/**
 * This is the product controller
 *
 * This handles all the products.
 */
public class ProductController {

    /** Only a single version is allowed.  */
    private static ProductController instance = null;
    private ProductController() {}

    public static ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    /**
     * renders the products
     *
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return the index.html data not ordered
     */
    public ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = new ProductDaoJdbc();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    /**
     * renders the products
     *
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return the index.html data ordered by category
     */
    public ModelAndView renderProductsbyCategory(Request req, Response res) {
        int categoryID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = new ProductDaoJdbc();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryID)));
        return new ModelAndView(params, "product/index");
    }

    /**
     * renders the products
     *
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return the index.html data ordered by supplier
     */
    public ModelAndView renderProductsbySupplier(Request req, Response res) {
        int supplierID = Integer.parseInt(req.params(":id"));
        ProductDao productDataStore = new ProductDaoJdbc();
        SupplierDao productSupplierDataStore = SupplierDaoJdbc.getInstance();

        Map<String, Object> params = getCommonParams(req);
        params.put("products", productDataStore.getBy(productSupplierDataStore.find(supplierID)));
        return new ModelAndView(params, "product/index");
    }

    /**
     * get all the data for the method of the class
     *
     * @param req the request data from the client
     * @return the parameters for the rendering.
     */
    private Map<String, Object> getCommonParams(Request req) {
        CartController cartController = CartController.getInstance();
        SupplierDao productSupplierDataStore = SupplierDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
        Cart cartDataStore = cartController.getCart(req);

        Map<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", productSupplierDataStore.getAll());
        params.put("numberOfProdutsInCart", cartDataStore.getAllQuantity());
        params.put("user", req.session().attribute("user"));
        return params;
    }
}
