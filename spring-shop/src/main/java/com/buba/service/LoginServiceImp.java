package com.buba.service;

import com.buba.dao.LoginMapper;
import com.buba.pojo.User;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImp implements LoginService{
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public HashMap login(String username, String password) {
        User user =  loginMapper.login(username, password);
        HashMap res = new HashMap();
        if (user != null) {
            res.put("data", user);
            res.put("meta", RespMsg.getStatus("登录成功", 200));
        } else {
            res.put("meta", RespMsg.getStatus("账号或者密码错误", 400));
        }
        return res;
    }
}
