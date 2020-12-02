package com.buba.controller;

import com.buba.pojo.mall.MallCategory;
import com.buba.pojo.user.User;
import com.buba.service.mall.MallService;
import javafx.scene.chart.PieChart;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@RestController
public class MallController {
    @Autowired
    private MallService mallService;

    @PostMapping(value = "/brand/upload/{flag}")
    public HashMap upload(@PathVariable String flag, MultipartFile file, String name, String test, float low, Integer id) {
        HashMap res = null;
        if (flag.equals("add")) {
            /*获取文件名*/
            String fileName = file.getOriginalFilename();
            /*获取新文件名*/
            char c = (char) (int) (Math.random() * 26 + 97);
            String imgName = c + System.currentTimeMillis() + "";
            /*获取文件后缀名*/
            String exName = fileName.substring(fileName.lastIndexOf("."));
            /*上传==>url+新文件名字+文件后缀*/
            try {
                String url = ResourceUtils.getURL("classpath:").getPath() + "static/mall/";
                file.transferTo(new File(url + imgName + exName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String img = "mall/" + imgName + exName;
            res = mallService.addBrand(name, test, img, low);
        } else {
            /*获取文件名*/
            String fileName = file.getOriginalFilename();
            /*获取新文件名*/
            char c = (char) (int) (Math.random() * 26 + 97);
            String imgName = c + System.currentTimeMillis() + "";
            /*获取文件后缀名*/
            String exName = fileName.substring(fileName.lastIndexOf("."));
            /*上传==>url+新文件名字+文件后缀*/
            try {
                String targetUrl = ResourceUtils.getURL("classpath:").getPath() + "static/mall/";
                file.transferTo(new File(targetUrl + imgName + exName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String img = "mall/" + imgName + exName;
            res = mallService.updateBrand(id, name, test, img, low);
        }
        return res;
    }

    /**
     * 获取行政区数据
     *
     * @return
     */
    @GetMapping(value = "/mall/getArea")
    public HashMap getArea() {
        return mallService.getArea();
    }

    /**
     * 获取商品制造商数据
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping(value = "/mall/brand")
    public HashMap getBrand(Integer id, String name) {
        return mallService.getBrand(id, name);
    }

    /**
     * 根据id删除商品制造商
     *
     * @param id
     * @return
     */
    @DeleteMapping("/mall/brand/delete")
    public HashMap deleteBrand(Integer id) {
        return mallService.deleteBrand(id);
    }

    /**
     * 获取商品类目数据
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/mall/category")
    public HashMap getCategory(Integer currentPage, Integer pageSize){
        return mallService.getCategory(currentPage,pageSize);
    }

    @GetMapping("/mall/category/getCateGoryflm")
    public ArrayList<MallCategory> getCateGoryflm(){
        return mallService.getCateGoryflm();
    }

    /**
     * 类目图片上传
     * @param file
     * @return
     */
    @PostMapping("/mall/category/lcon")
    public String addLcon(@RequestBody MultipartFile file){
            /*获取文件名*/
            String fileName = file.getOriginalFilename();
            /*获取新文件名*/
            char c = (char) (int) (Math.random() * 26 + 97);
            String imgName = c + System.currentTimeMillis() + "";
            /*获取文件后缀名*/
            String exName = fileName.substring(fileName.lastIndexOf("."));
            /*上传==>url+新文件名字+文件后缀*/
            try {
                String url = ResourceUtils.getURL("classpath:").getPath() + "static/mall/";
                file.transferTo(new File(url + imgName + exName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String img = "mall/" + imgName + exName;
        return img;
    }

    /**
     * 添加类目
     * @param category
     * @return
     */
    @PostMapping("/mall/category/addCateGory")
    public int addCateGory(@RequestBody MallCategory category){
        return mallService.addCateGory(category);
    }

    /**
     * 根据id查询子类目条数
     * @param id
     * @return
     */
    @GetMapping("/mall/category/selectSon")
    public int selectSon(String id){
        int a = mallService.selectSon(id);
        return a;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/mall/category/delCate")
    public int delCate(Integer id){
        int a = mallService.delCate(id);
        return a;
    }

    /**
     * 修改
     */
    @PostMapping("/mall/category/updateCateGory")
    public int updateCateGory(@RequestBody MallCategory mallCategory){
        int a = mallService.updateCateGory(mallCategory);
        return a;
    }


















}
