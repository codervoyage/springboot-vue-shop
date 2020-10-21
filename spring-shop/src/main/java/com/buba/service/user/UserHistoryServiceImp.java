package com.buba.service.user;

import com.buba.dao.UserHistoryMapper;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class UserHistoryServiceImp implements UserHistoryService{
    @Autowired
    private UserHistoryMapper userHistoryMapper;
    @Override
    public HashMap limitHistory(Integer currentPage, Integer pageSize) {
        ArrayList limitList = userHistoryMapper.limitHistory(null,null,currentPage,pageSize);
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
        return userHistoryMapper.getNumber();
    }

    @Override
    public HashMap selectOne(Integer searchID, String keyword, Integer currentPage, Integer pageSize) {
        ArrayList  limitList = userHistoryMapper.limitHistory(searchID,keyword,currentPage,pageSize);
        int same = userHistoryMapper.same(searchID,keyword);
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
