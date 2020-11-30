package com.buba.controller;

import com.buba.service.mall.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

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

    @GetMapping(value = "/mall/getArea/by_id")
    public HashMap getAreaByid(Integer id) {
        return mallService.getArea2(id);
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
}
