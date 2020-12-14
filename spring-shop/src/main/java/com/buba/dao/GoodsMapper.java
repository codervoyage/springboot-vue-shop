package com.buba.dao;

import com.buba.pojo.goods.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface GoodsMapper {

    ArrayList<GoodsComment> getAllComments(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    @Delete("delete from goods_comment where c_id = #{id}")
    int delById(Integer id);

    int goodsAdd(Goods goods);

    int goodsSpecAdd(List<GoodsSpec> list);

    int goodsStockAdd(List<GoodsStock> list);

    int goodsParaAdd(List<GoodsPara> list);

    List<Goods> getGoodsList(@Param("goodsId") String goodsId,@Param("num") String num, @Param("name") String name);
}
