package com.buba.dao;

import com.buba.pojo.Menu;
import com.buba.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LoginMapper {
    User login(@Param("username") String username, @Param("password") String password);
    @Select("select * from menu")
    ArrayList<Menu> getMenuList();
}
