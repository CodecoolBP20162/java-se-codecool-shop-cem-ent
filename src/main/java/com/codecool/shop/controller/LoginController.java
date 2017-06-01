package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.dao.jdbc.UserDaoJdbc;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is handeling the user login.
 */
public class LoginController {

    /** Only a single version is allowed.  */
    private static LoginController instance = null;
    private LoginController() {}

    /**
     * <h1>constructor</h1>
     *
     * @return returns a new instance if none exists else it returns the already existing one.
     */
    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }


    /**
     * Render of the login page
     *
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return the data needed for rendering the logint.html
     */
    public ModelAndView renderLogin(Request req, Response res) {
        UserDaoMem userDataStore = UserDaoMem.getInstance();

        Map<String, Object> params = new HashMap<>();
        params.put("users", userDataStore.getAll());
        return new ModelAndView(params, "login");
    }

    /**
     * checking the post data from the render login page
     *
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return reloads the page upon wrong data.
     */
    public ModelAndView renderLoginPost(Request req, Response res){
        UserDaoJdbc userDataStore = UserDaoJdbc.getInstance();
        User user = userDataStore.find(req.queryParams("username"));
        if (req.queryParams("username").equals(user.getName())){
            if (req.queryParams("password").equals(user.getPassword())) {
                req.session(true);
                req.session().attribute("user", req.queryParams("username"));
                req.session().attribute("type", user.getRank());
                res.redirect("/");
            }
        }
        return renderLogin(req, res);
    }

    /**
     * renders the logout page
     * @param req the request data from the client
     * @param res the response data from the clent
     * @return reloads the page upon wrong data.
     */
    public ModelAndView renderLogout(Request req, Response res){
        req.session().removeAttribute("user");
        res.redirect("/");
        // this line is needed as a void return is not accepted.
        return renderLogin(req, res);
    }
}
