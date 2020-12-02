package com.buba.dao;

import com.buba.pojo.mall.MallArea;
import com.buba.pojo.mall.MallBrand;
import com.buba.pojo.mall.MallCategory;
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

    @Delete("delete from mall_brand where b_id = #{id}")
    int deleteBrand(@Param("id") Integer id);

    @Insert("insert into mall_brand(name,img,test,low) values (#{name},#{img},#{test},#{low})")
    int addBrand(String name, String test, String img, float low);

    int updateBrand(@Param("id") Integer id,
                    @Param("name") String name,
                    @Param("test") String test,
                    @Param("img") String img,
                    @Param("low") float low);
    ArrayList<MallCategory> getCategory();

    @Select("select * from mall_category where fid=0")
    ArrayList<MallCategory> getCateGoryflm();

    int addCateGory(@Param("category") MallCategory category);

    @Select("select count(*) from mall_category where fid=#{id}")
    int selectSon(@Param("id") Integer i);

    @Delete("delete from mall_category where id= #{id}")
    int delCate(@Param("id") Integer id);

    int updateCateGory(@Param("category")MallCategory mallCategory);

    @Select("select * from mall_area where parent_id = #{id}")
    ArrayList<MallArea> getArea2(Integer id);
}
