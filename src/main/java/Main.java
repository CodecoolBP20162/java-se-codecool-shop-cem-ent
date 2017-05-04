import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
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
            CartController.addItemToCart(req);

            //test print
            Cart vmi = req.session().attribute("vmi");
            vmi.getAll().forEach(lineItem -> {
                System.out.println(lineItem.getProduct().getName());
                System.out.println(lineItem.getQuantity());
                System.out.println(lineItem.getPrice());
            });
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
        Supplier apple = new Supplier("Apple","Luxury products");
        supplierDataStore.add(apple);
        Supplier microsoft = new Supplier("Microsoft", "IT products");
        supplierDataStore.add(microsoft);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "Moblie phones. Your mother can ask you what you eaten for lunch through these devices.");
        productCategoryDataStore.add(phone);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "Like a tablet but with keyboard");
        productCategoryDataStore.add(notebook);
        ProductCategory softwares = new ProductCategory("Software", "Software", "Programs and subscriptions");
        productCategoryDataStore.add(softwares);
        ProductCategory parts = new ProductCategory("Parts", "Parts", "Parts for different types of products.");
        productCategoryDataStore.add(parts);


        //setting up products and printing it
        productDataStore.add(new Hardware("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, 12));
        productDataStore.add(new Hardware("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, 12));
        productDataStore.add(new Hardware("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, 12));
        productDataStore.add(new Hardware("Macbook Pro 2017", 2999.99f, "USD", "Apple's latest notebook is a great value for media consumption.", notebook, apple, 24));
        productDataStore.add(new Hardware("Iphone 7", 899.9f, "USD", "Latest product of Apple.", phone, apple, 12));
        productDataStore.add(new Software("Microsoft Office subscription", 99.9f, "USD", "Microsoft Office is an office suite of applications, servers, and services developed by Microsoft.", softwares, microsoft, 12));
        productDataStore.add(new Parts("Battery for Iphone 7", 69.9f, "USD", "New battery to replace Iphone 7's old battery.", parts, apple));


    }

}
