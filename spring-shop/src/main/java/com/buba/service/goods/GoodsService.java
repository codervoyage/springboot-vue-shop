package com.buba.service.goods;

import com.buba.pojo.goods.Goods;

import java.util.HashMap;

public interface GoodsService {
    HashMap getAllComments(Integer userId, Integer goodsId, Integer pageNum, Integer pageSize);

    HashMap delById(Integer id);

    HashMap goodsAdd(Goods goods);

    HashMap getGoodsList(String goodsId, String num, String name, Integer currentPage, Integer pageSize);
}
