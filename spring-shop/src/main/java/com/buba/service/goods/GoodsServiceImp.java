package com.buba.service.goods;

import com.buba.dao.GoodsMapper;
import com.buba.pojo.goods.GoodList;
import com.buba.utils.RespMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    private GoodsMapper gooodsMapper;
    @Override
    public HashMap queryGoodsList(String goodsId,String goodsName,String classificId,Integer currentPage,Integer pageSize) {
        HashMap map=new HashMap();


        PageHelper.startPage(currentPage,pageSize);
        List<GoodList> list= gooodsMapper.queryGoodsList(goodsId,goodsName,classificId);

        PageInfo pageInfo=new PageInfo(list);
        map.put("data",list);
        map.put("totalData",pageInfo.getTotal());
        map.put("meta", RespMsg.getStatus("获取成功", 200));

        return map;
    }

    @Override
    public HashMap deleteGoods(Integer id) {
        HashMap map=new HashMap();
       Integer i=gooodsMapper.deleteGoods(id);
       map.put("data",i==1?true:false);
       if (i>0){
           map.put("meta",RespMsg.getStatus("删除成功",200));
       }else{
           map.put("meta",RespMsg.getStatus("删除失败",400));
       }

        return map;
    }
}
