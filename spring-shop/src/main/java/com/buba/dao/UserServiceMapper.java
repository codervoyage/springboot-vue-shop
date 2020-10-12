package com.buba.dao;

import com.buba.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserServiceMapper {
    List<User> getUserList(@Param("page") Integer currentPage,@Param("size") Integer pageSize);
}
