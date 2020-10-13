package com.buba.service.user;

import com.buba.dao.FeedBackMapper;
import com.buba.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class FeedBackServiceImp implements FeedBackService {
    @Autowired
    private FeedBackMapper feedBackMapper;

    @Override
    public HashMap limitBack(Integer currentPage, Integer pageSize) {
        ArrayList limitList = feedBackMapper.limitBack(null,null,currentPage,pageSize);
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
        return feedBackMapper.getNumber();
    }

    @Override
    public HashMap selectOne(Integer feedBackID, String userName, Integer currentPage, Integer pageSize) {
        ArrayList  limitList = feedBackMapper.limitBack(userName,feedBackID,currentPage,pageSize);
        int same = feedBackMapper.same(feedBackID,userName);
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
