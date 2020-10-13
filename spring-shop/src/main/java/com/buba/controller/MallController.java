package com.buba.controller;

import com.buba.service.mall.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MallController {
    @Autowired
    private MallService mallService;

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
