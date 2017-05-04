package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.UserDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public static ModelAndView renderLogin(Request req, Response res) {
        UserDaoMem userDataStore = UserDaoMem.getInstance();

        Map<String, Object> params = new HashMap<>();
        params.put("users", userDataStore.getAll());
        return new ModelAndView(params, "login");
    }

    public static ModelAndView renderLoginPost(Request req, Response res){
        UserDaoMem userDataStore = UserDaoMem.getInstance();
        Object users = userDataStore.getAll();
        if (req.queryParams("username") == users.name){
            if (req.queryParams("password") == users.password) {
                req.session(true);
                req.session().attribute("user", req.queryParams("username"));
                req.session().attribute("type", users.type;
                res.redirect("/");
            }
        }
        return renderLogin(req, res);
    }
}
