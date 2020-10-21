package com.buba.service.user;

import com.buba.dao.UserServiceMapper;
import com.buba.pojo.User;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserServiceMapper userServiceMapper;
    @Override
    public HashMap getUserList(Integer currentPage, Integer pageSize,Integer userId, String username, Integer phone) {
        List<User> list =  userServiceMapper.getUserList(currentPage, pageSize,userId,username,phone);
        Integer total =  userServiceMapper.getTotal(userId,username,phone);
        HashMap res = new HashMap();
        if (list != null) {
            list.stream().forEach(user ->{
                user.setUserPassword("******");
            });
            res.put("data", list);
            res.put("total", total);
            res.put("meta", RespMsg.getStatus("获取成功", 200));
        } else {
            res.put("meta", RespMsg.getStatus("获取失败", 400));
        }
        return res;
    }

    @Override
    public int updateUserOne(User user) {
        int i = userServiceMapper.updateUserOne(user);
        return 0;
    }
}
