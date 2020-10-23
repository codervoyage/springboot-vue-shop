package com.buba.service.mall;

import com.buba.dao.MallMapper;
import com.buba.pojo.mall.MallArea;
import com.buba.pojo.mall.MallBrand;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MallServiceImp implements MallService {
    @Autowired
    private MallMapper mallMapper;


    @Override
    public HashMap getArea() {
        HashMap res = new HashMap();
        ArrayList<MallArea> list = mallMapper.getArea();
        List<MallArea> threeLevel = list.stream().filter(x -> x.getLevel().equals(3)).collect(Collectors.toList());
        List<MallArea> twoLevel = list.stream().filter(x -> x.getLevel().equals(2)).collect(Collectors.toList());
        List<MallArea> oneLevel = list.stream().filter(x -> x.getLevel().equals(1)).collect(Collectors.toList());
        ArrayList<MallArea> result = null;
        for (MallArea two : twoLevel) {
            result = new ArrayList<>();
            for (MallArea three : threeLevel) {
                if (two.getAreaId().equals(three.getParentId())) {
                    result.add(three);
                }
            }
            two.setChildren(result);
        }
        for (MallArea one : oneLevel) {
            result = new ArrayList<>();
            for (MallArea two : twoLevel) {
                if (one.getAreaId().equals(two.getParentId())) {
                    result.add(two);
                }
            }
            one.setChildren(result);
        }
        res.put("data", oneLevel);
        res.put("meta", RespMsg.getStatus("获取成功", 200));
        return res;
    }

    @Override
    public HashMap getBrand(Integer id, String name) {
        HashMap res = new HashMap();
        ArrayList<MallBrand> list = mallMapper.getBrand(id, name);
        res.put("data", list);
        res.put("meta", RespMsg.getStatus("获取成功", 200));
        return res;
    }

    @Override
    public HashMap deleteBrand(Integer id) {
        HashMap res = new HashMap();
        int i = mallMapper.deleteBrand(id);
        if (i > 0) {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("删除成功", 200));
        } else {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("删除失败", 400));
        }
        return res;
    }

    @Override
    public HashMap addBrand(String name, String test, String img, float low) {
        HashMap res = new HashMap();
        int i = mallMapper.addBrand(name, test, img, low);
        if (i > 0) {
            res.put("img", img);
            res.put("meta", RespMsg.getStatus("添加成功", 200));
        } else {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("添加失败", 400));
        }
        return res;
    }

    @Override
    public HashMap updateBrand(Integer id, String name, String test, String img, float low) {
        HashMap res = new HashMap();
        int i = mallMapper.updateBrand(id, name, test, img, low);
        if (i > 0) {
            res.put("img", img);
            res.put("meta", RespMsg.getStatus("修改成功", 200));
        } else {
            res.put("data", null);
            res.put("meta", RespMsg.getStatus("修改失败", 400));
        }
        return res;
    }
}
