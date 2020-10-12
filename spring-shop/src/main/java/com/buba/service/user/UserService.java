package com.buba.service.user;

import java.util.HashMap;

public interface UserService {
    HashMap getUserList(Integer currentPage, Integer pageSize,Integer userId, String username, Integer phone);
}
