package com.buba.controller;

import com.buba.pojo.goods.Goods;
import com.buba.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /*
     * 查询商品列表
     * */
    @GetMapping("/goods/list")
    public HashMap queryGoodsList(String goodsId, String num, String name, Integer currentPage, Integer pageSize) {
        return goodsService.getGoodsList(goodsId, num, name, currentPage, pageSize);
    }

    @PostMapping("/goods_add")
    public HashMap add_goods(@RequestBody Goods goods) {
        return goodsService.goodsAdd(goods);
    }

    @PostMapping("/goods/addPic")
    public String addPic(@RequestBody MultipartFile file) {
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n = (char) (int) (Math.random() * 26 + 97);
        String prename = n + System.currentTimeMillis() + "";
        /*获取后缀名*/
        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url = ResourceUtils.getURL("classpath:").getPath() + "static/goods/images/";
            File myFile = new File(url, prename + exname);
            if (!myFile.exists()) {
                myFile.mkdirs();
            }
            file.transferTo(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue = "goods/images/" + prename + exname;
        return propvalue;
    }

    @PostMapping("/goods/addGallery")
    public String addGallery(MultipartFile file) {
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n = (char) (int) (Math.random() * 26 + 97);
        String prename = n + System.currentTimeMillis() + "";
        /*获取后缀名*/
        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url = ResourceUtils.getURL("classpath:").getPath() + "static/goods/gallery/";
            File myFile = new File(url, prename + exname);
            if (!myFile.exists()) {
                myFile.mkdirs();
            }
            file.transferTo(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue = "goods/gallery/" + prename + exname;
        return propvalue;
    }

    @PostMapping("/goods/addGuige")
    public String addGuige(@RequestBody MultipartFile file) {
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n = (char) (int) (Math.random() * 26 + 97);
        String prename = n + System.currentTimeMillis() + "";
        /*获取后缀名*/
        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url = ResourceUtils.getURL("classpath:").getPath() + "static/goods/guige/";
            File myFile = new File(url, prename + exname);
            if (!myFile.exists()) {
                myFile.mkdirs();
            }
            file.transferTo(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue = "goods/guige/" + prename + exname;
        return propvalue;
    }

    @PostMapping("/goods/addHuopin")
    public String addHuopin(@RequestBody MultipartFile file) {
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n = (char) (int) (Math.random() * 26 + 97);
        String prename = n + System.currentTimeMillis() + "";
        /*获取后缀名*/
        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url = ResourceUtils.getURL("classpath:").getPath() + "static/goods/huopin/";
            File myFile = new File(url, prename + exname);
            if (!myFile.exists()) {
                myFile.mkdirs();
            }
            file.transferTo(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue = "goods/huopin/" + prename + exname;
        return propvalue;
    }

    @PostMapping("/goods/add_fwb")
    public String add_fwb(@RequestBody MultipartFile file) {
        /*获取原文件名*/
        String originalFilename = file.getOriginalFilename();
        /*生成新的图片名称*/
        char n = (char) (int) (Math.random() * 26 + 97);
        String prename = n + System.currentTimeMillis() + "";
        /*获取后缀名*/
        String exname = originalFilename.substring(originalFilename.lastIndexOf("."));
        try {
            String url = ResourceUtils.getURL("classpath:").getPath() + "static/goods/fwb/";
            File myFile = new File(url, prename + exname);
            if (!myFile.exists()) {
                myFile.mkdirs();
            }
            file.transferTo(myFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String propvalue = "goods/fwb/" + prename + exname;
        return propvalue;
    }

    /**
     * 获取评论列表
     *
     * @param userId
     * @param goodsId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/goods/comments/getAll")
    public HashMap getAll(Integer userId, Integer goodsId, Integer pageNum, Integer pageSize) {
        return goodsService.getAllComments(userId, goodsId, pageNum, pageSize);
    }

    @DeleteMapping("/comment/delById")
    public HashMap delById(Integer id) {
        return goodsService.delById(id);
    }
}
