package com.buba.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FeedBackMapper {

    ArrayList limitBack(@Param("userName") String userName, @Param("feedBackID") Integer feedBackID, @Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    @Select("select count(*) from user_feedback")
    int getNumber();

    int same(@Param("feedBackID") Integer feedBackID,@Param("userName") String userName);
}

