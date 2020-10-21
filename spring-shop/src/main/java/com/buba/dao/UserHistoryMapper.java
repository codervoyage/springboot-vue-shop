package com.buba.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserHistoryMapper {

    ArrayList limitHistory(@Param("id") Integer id, @Param("keyword") String keyword, @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    @Select("select count(*) from user_history")
    int getNumber();

    int same(@Param("searchID") Integer searchID, @Param("keyword") String keyword);
}
