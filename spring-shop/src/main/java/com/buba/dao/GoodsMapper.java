package com.buba.dao;

import com.buba.pojo.goods.GoodList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface GoodsMapper {

    List<GoodList> queryGoodsList(@Param("goodsId")String goodsId,@Param("goodsName")String goodsName,@Param("classificId")String classificId);
    @Delete("delete from goods_list where goods_id=#{id}")
    Integer deleteGoods(@Param("id") Integer id);
}
