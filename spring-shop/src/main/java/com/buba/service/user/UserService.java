package com.buba.service.user;


import com.buba.pojo.user.User;

import java.util.HashMap;

public interface UserService {
    HashMap getUserList(Integer currentPage, Integer pageSize,Integer userId, String username, Integer phone);

    int updateUserOne(User user);
}
