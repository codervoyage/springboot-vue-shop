package com.buba.dao;

import com.buba.pojo.mall.MallArea;
import com.buba.pojo.mall.MallBrand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MallMapper {
    @Select("select * from mall_area where level = 1")
    ArrayList<MallArea> getArea();

    ArrayList<MallBrand> getBrand(@Param("id") Integer id, @Param("name") String name);

    @Delete("delete from mall_brand where id = #{id}")
    int deleteBrand(@Param("id") Integer id);

    @Insert("insert into mall_brand(name,img,test,low) values (#{name},#{img},#{test},#{low})")
    int addBrand(String name, String test, String img, float low);

    int updateBrand(@Param("id") Integer id,
                    @Param("name") String name,
                    @Param("test") String test,
                    @Param("img") String img,
                    @Param("low") float low);
    @Select("select * from mall_area where parent_id = #{id}")
    ArrayList<MallArea> getArea2(Integer id);
}
