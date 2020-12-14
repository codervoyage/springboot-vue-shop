package com.buba.service.goods;

import com.buba.dao.GoodsMapper;
import com.buba.pojo.goods.*;
import com.buba.utils.RespMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class GoodsServiceImp implements GoodsService {
    @Autowired
    private GoodsMapper gooodsMapper;

    @Override
    public HashMap getAllComments(Integer userId, Integer goodsId, Integer pageNum, Integer pageSize) {
        HashMap res = new HashMap();
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<GoodsComment> list = gooodsMapper.getAllComments(userId, goodsId);
        PageInfo info = new PageInfo(list);
        res.put("data", list);
        res.put("total", info.getTotal());
        res.put("meta", RespMsg.getStatus("获取成功", 200));
        return res;
    }

    @Override
    public HashMap delById(Integer id) {
        HashMap res = new HashMap();
        int i = gooodsMapper.delById(id);
        if (i > 0) {
            res.put("meta", RespMsg.getStatus("删除成功", 200));
        } else {
            res.put("meta", RespMsg.getStatus("删除失败", 400));
        }
        return res;
    }

    @Override
    public HashMap goodsAdd(Goods goods) {
        HashMap res = new HashMap();

        int i = gooodsMapper.goodsAdd(goods);

        int i2 = 0, i3 = 0, i4 = 0;

        List<GoodsSpec> goodsSpec = goods.getGoodsSpec();
        goodsSpec.stream().forEach(x -> x.setGoodsId(goods.getGoodsId()));
        i2 = gooodsMapper.goodsSpecAdd(goodsSpec);

        List<GoodsStock> goodsStocks = goods.getGoodsStocks();
        goodsStocks.stream().forEach(x -> x.setGoodsId(goods.getGoodsId()));
        i3 = gooodsMapper.goodsStockAdd(goodsStocks);

        if (goods.getGoodsParas().size() != 0) {
            List<GoodsPara> goodsPara = goods.getGoodsParas();
            goodsPara.stream().forEach(x -> x.setGoodsId(goods.getGoodsId()));
            i4 = gooodsMapper.goodsParaAdd(goodsPara);
        } else {
            i4 = 1;
        }


        if (i2 > 0 && i3 > 0 && i4 > 0 && i > 0) {
            res.put("meta", RespMsg.getStatus("添加成功", 200));
        } else {
            res.put("meta", RespMsg.getStatus("添加失败", 400));
        }
        return res;
    }

    @Override
    public HashMap getGoodsList(String goodsId, String num, String name, Integer currentPage, Integer pageSize) {
        HashMap res = new HashMap();
        PageHelper.startPage(currentPage, pageSize);
        List<Goods> list = gooodsMapper.getGoodsList(goodsId, num, name);
        PageInfo pageInfo = new PageInfo(list);
        res.put("data", list);
        res.put("total", pageInfo.getTotal());
        return res;
    }
}
