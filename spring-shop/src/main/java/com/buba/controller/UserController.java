package com.buba.controller;

import com.buba.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取user列表数据
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getUserList")
    public HashMap getUserList
    (Integer currentPage,
     Integer pageSize,
     Integer userId,
     String username,
     Integer phone) {
        return userService.getUserList(currentPage,pageSize,userId,username,phone);
    }
}
