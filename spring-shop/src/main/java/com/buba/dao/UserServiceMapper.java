package com.buba.dao;

import com.buba.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceMapper {
    List<User> getUserList(@Param("page") Integer currentPage,
                           @Param("size") Integer pageSize,
                           @Param("userId") Integer userId,
                           @Param("username") String username,
                           @Param("phone") Integer phone);

    Integer getTotal(Integer userId, String username, Integer phone);

    int updateUserOne(@Param("user") User user);
}
