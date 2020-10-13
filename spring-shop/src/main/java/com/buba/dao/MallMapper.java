package com.buba.dao;

import com.buba.pojo.MallArea;
import com.buba.pojo.MallBrand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MallMapper {
    @Select("select * from mall_area")
    ArrayList<MallArea> getArea();
    ArrayList<MallBrand> getBrand(@Param("id")Integer id,@Param("name") String name);
    @Delete("delete from mall_brand where id = #{id}")
    int deleteBrand(@Param("id") Integer id);
}
