package com.buba.service;

import com.buba.dao.ShipAddressMapper;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShipAddressServiceImp implements ShipAddressService {
    @Autowired
    private ShipAddressMapper shipAddressMapper;

    @Override
    public HashMap limitAddress(Integer currentPage, Integer pageSize) {
        ArrayList  limitList = shipAddressMapper.limitAddress(currentPage,pageSize);
        HashMap listMap = new HashMap<>();
        if (limitList!=null){
            listMap.put("data",limitList);
            listMap.put("meta", RespMsg.getStatus("获取成功",200));
        }else {
            listMap.put("meta",RespMsg.getStatus("获取失败",400));
        }

        return listMap;
    }
}
