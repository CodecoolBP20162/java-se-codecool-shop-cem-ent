import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            req.session(true);
            return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });
        get("/category/:id", (Request req, Response res) -> {
            int categoryID = Integer.parseInt(req.params(":id"));
            return new ThymeleafTemplateEngine().render( ProductController.renderProductsbyCategory(req, res, categoryID) );
        });
        get("/supplier/:id", (Request req, Response res) -> {
            int supplierID = Integer.parseInt(req.params(":id"));
            return new ThymeleafTemplateEngine().render( ProductController.renderProductsbySupplier(req, res, supplierID) );
        });

        get("/addtocart/:id", (Request req, Response res) -> {
            req.session(true);
            int addedProductId = Integer.parseInt(req.params(":id"));
            ProductDao productDataStore = ProductDaoMem.getInstance();
            CartDao cartDataStore = CartDaoMem.getInstance();


            LineItem lineItemCandidate = new LineItem(productDataStore.find(addedProductId));

            cartDataStore.add(lineItemCandidate);
            System.out.println(cartDataStore.getAll().size());
            System.out.println(cartDataStore.getAll().toString());
            req.session().attribute(lineItemCandidate.toString(), lineItemCandidate.toString());
            return null;

        });



        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        productCategoryDataStore.add(phone);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
        productCategoryDataStore.add(notebook);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        //test products
        productDataStore.add(new Product("AsadHD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", notebook, amazon));
        productDataStore.add(new Product("Amazon Fisadare HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", phone, amazon));


    }


}
