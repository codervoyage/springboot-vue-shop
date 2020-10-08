package com.buba.controller;

import com.buba.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public HashMap Login(@RequestBody Map<String,String> map) {
        return loginService.login(map.get("username"),map.get("password"));
    }

    @GetMapping("/getMenuList")
    public HashMap getMenuList() {
        return loginService.getMenuList();
    }
}
