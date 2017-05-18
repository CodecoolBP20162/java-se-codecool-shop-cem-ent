package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static LoginController instance = null;
    private LoginController() {}

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public ModelAndView renderLogin(Request req, Response res) {
        UserDaoMem userDataStore = UserDaoMem.getInstance();

        Map<String, Object> params = new HashMap<>();
        params.put("users", userDataStore.getAll());
        return new ModelAndView(params, "login");
    }

    public ModelAndView renderLoginPost(Request req, Response res){
        UserDaoMem userDataStore = UserDaoMem.getInstance();
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
}
