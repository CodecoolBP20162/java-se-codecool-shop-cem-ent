
package com.codecool.shop.controller;

        import com.codecool.shop.dao.ProductCategoryDao;
        import com.codecool.shop.dao.ProductDao;
        import com.codecool.shop.dao.SupplierDao;
        import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
        import com.codecool.shop.dao.implementation.ProductDaoMem;
        import com.codecool.shop.dao.implementation.SupplierDaoMem;
        import com.codecool.shop.model.Product;
        import com.codecool.shop.model.ProductCategory;
        import com.codecool.shop.model.Supplier;

        import spark.Request;
        import spark.Response;
        import spark.ModelAndView;

        import java.util.HashMap;
        import java.util.Map;

public class SupplierController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("supplier", supplierDataStore.find(1));
        params.put("products", productDataStore.getBy(supplierDataStore.find(1)));
        return new ModelAndView(params, "product/supplier");
    }

}
