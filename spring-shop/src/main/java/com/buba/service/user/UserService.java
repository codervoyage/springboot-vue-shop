package com.buba.service.user;


import com.buba.pojo.user.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    HashMap getUserList(Integer currentPage, Integer pageSize,Integer userId, String username, Integer phone);

    int updateUserOne(User user);

    List<User> selectAll();

    List<User> selectOnetext(Integer id, String name);

    int addUserTest(String name, String sex, String grade);

    int delcateTest(Integer id);
}
