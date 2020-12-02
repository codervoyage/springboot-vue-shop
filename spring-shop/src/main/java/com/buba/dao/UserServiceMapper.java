package com.buba.dao;

import com.buba.pojo.user.User;
import org.apache.ibatis.annotations.Delete;
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

    int updateUserOne(@Param("static/user") User user);

    @Select("select * from user")
    List<User> selectAll();

    List<User> selectOnetext(@Param("id") Integer id, @Param("name") String name);


    int addUserTest(@Param("name") String name, @Param("sex") String sex, @Param("grade") String grade);

    @Delete("delete from user where user_id = #{id}")
    int delcateTest(Integer id);
}
