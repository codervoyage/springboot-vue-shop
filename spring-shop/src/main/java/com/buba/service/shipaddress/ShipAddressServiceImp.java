package com.buba.service.shipaddress;

import com.buba.dao.ShipAddressMapper;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ShipAddressServiceImp implements ShipAddressService {
    @Autowired
    private ShipAddressMapper shipAddressMapper;

    @Override
    public HashMap limitAddress(Integer currentPage, Integer pageSize) {
        ArrayList  limitList = shipAddressMapper.limitAddress(null,null,currentPage,pageSize);
        HashMap listMap = new HashMap<>();
        if (limitList!=null){
            listMap.put("data",limitList);
            listMap.put("meta", RespMsg.getStatus("获取成功",200));
        }else {
            listMap.put("meta",RespMsg.getStatus("获取失败",400));
        }

        return listMap;
    }

    @Override
    public int getNomber() {
        return shipAddressMapper.getNumber();
    }

    @Override
    public HashMap selectOne(Integer userID, String userName, Integer currentPage, Integer pageSize) {
        ArrayList  limitList = shipAddressMapper.limitAddress(userID,userName,currentPage,pageSize);
        int same = shipAddressMapper.same(userID,userName);
        HashMap listMap = new HashMap<>();
        if (limitList!=null){
            listMap.put("data",limitList);
            listMap.put("same",same);
            listMap.put("meta", RespMsg.getStatus("获取成功",200));
        }else {
            listMap.put("meta",RespMsg.getStatus("获取失败",400));
        }
        return listMap;
    }
}
