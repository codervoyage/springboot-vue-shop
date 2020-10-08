package com.buba.dao;

import com.buba.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    User login(@Param("username") String username, @Param("password") String password);
}
