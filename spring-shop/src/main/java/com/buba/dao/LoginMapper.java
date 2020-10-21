package com.buba.dao;

import com.buba.pojo.GetMenuList;
import com.buba.pojo.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginMapper {
    User login(@Param("username") String username, @Param("password") String password);
    @Select("select * from menu where level = '0'")
    List<GetMenuList> findStairMenu();
    @Select("select * from menu where level = '1'")
    List<GetMenuList> findSecondMenu();
    int updatePassword(@Param("userId") Integer userId,@Param("password") String newPassword);
}
