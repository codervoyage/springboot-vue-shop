package com.buba.controller;

import com.buba.pojo.goods.GoodList;
import com.buba.service.goods.GoodsService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    /*
    * 查询商品列表
    * */
    @GetMapping("/goods/list")
    public HashMap queryGoodsList(String goodsId,String goodsName,String classificId,Integer currentPage,Integer pageSize){

       return goodsService.queryGoodsList(goodsId,goodsName,classificId,currentPage,pageSize);

    }
    @PostMapping("/goods/addPic")
    public String addPic(@RequestBody MultipartFile file){
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n=(char)(int)(Math.random()*26+97);
        String prename=n+System.currentTimeMillis()+"";
        /*获取后缀名*/
        String exname=originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url=ResourceUtils.getURL("classpath:").getFile()+"static/goods/";
            file.transferTo(new File(url+prename+exname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue="goods/"+prename+exname;
        return propvalue;

    }
    @DeleteMapping("/goods/delete")
    public HashMap deleteGoods(Integer goodsId){
        return goodsService.deleteGoods(goodsId);


    }
}
