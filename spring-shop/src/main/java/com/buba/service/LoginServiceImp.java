package com.buba.service;

import com.buba.dao.LoginMapper;
import com.buba.pojo.GetMenuList;
import com.buba.pojo.user.User;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public HashMap getMenuList() {
        List<GetMenuList> stairMenu =  loginMapper.findStairMenu();
        List<GetMenuList> secondMenu =  loginMapper.findSecondMenu();
        HashMap res = new HashMap();
        if (stairMenu != null && secondMenu != null) {
            stairMenu.stream().forEach(stair ->{
                List result = new ArrayList();
                secondMenu.stream().forEach(second ->{
                    if (stair.getId().equals(second.getPid())) {
                        result.add(second);
                    }
                });
                stair.setChildren(result);
            });
            res.put("data", stairMenu);
            res.put("meta", RespMsg.getStatus("获取成功", 200));
        } else {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("查询失败，后端问题", 400));
        }
        return res;
    }

    @Override
    @Transactional
    public HashMap updatePassword(Integer userId, String newPassword) {
        int i = loginMapper.updatePassword(userId, newPassword);
        HashMap res = new HashMap();
        if (i > 0) {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("修改密码成功", 200));
        } else {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("修改密码失败", 400));
        }
        return res;
    }
}
