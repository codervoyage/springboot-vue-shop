package com.buba.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ShipAddressMapper {
    ArrayList limitAddress(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize);

    @Select("select count(*) from user_address")
    int getNumber();
}
