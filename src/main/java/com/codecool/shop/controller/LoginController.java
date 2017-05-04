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
}
