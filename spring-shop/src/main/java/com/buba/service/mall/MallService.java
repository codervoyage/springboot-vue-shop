package com.buba.service.mall;

import com.buba.pojo.mall.MallCategory;

import java.util.ArrayList;
import java.util.HashMap;

public interface MallService {
    HashMap getArea();

    HashMap getBrand(Integer id, String name);

    HashMap deleteBrand(Integer id);

    HashMap addBrand(String name, String test, String img, float low);

    HashMap updateBrand(Integer id, String name, String test, String img, float low);

    HashMap getCategory(Integer currentPage, Integer pageSize);

    ArrayList<MallCategory> getCateGoryflm();

    int addCateGory(MallCategory category);

    int selectSon(String id);

    int delCate(Integer id);

    int updateCateGory(MallCategory mallCategory);
}
