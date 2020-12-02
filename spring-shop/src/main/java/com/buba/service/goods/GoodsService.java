package com.buba.service.goods;

import java.util.HashMap;

public interface GoodsService {
    HashMap queryGoodsList(String goodsId,String goodsName,String classificId,Integer currentPage,Integer pageSize);

    HashMap deleteGoods(Integer id);
}
