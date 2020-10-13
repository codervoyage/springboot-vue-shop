package com.buba.controller;

import com.buba.service.user.ShipAddressService;
import com.buba.service.user.UserService;
import com.buba.service.user.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShipAddressService shipAddressSerive;
    @Autowired
    private FeedBackService feedBackService;
    /**
     * 获取user列表数据
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getUserList")
    public HashMap getUserList
    (Integer currentPage,
     Integer pageSize,
     Integer userId,
     String username,
     Integer phone) {
        return userService.getUserList(currentPage,pageSize,userId,username,phone);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/limitAddress")
    public HashMap limitAddress(Integer currentPage, Integer pageSize){
        HashMap listMap = shipAddressSerive.limitAddress(currentPage,pageSize);
        return listMap;
    }

    /**
     * 获取总条数
     * @return
     */
    @GetMapping("/getNumber")
    public int getNumber(){
        int number = shipAddressSerive.getNomber();
        if (number!=0){
            return number;
        }else{
            return 0;
        }
    }

    /**
     * 根据条件查询
     * @param userID
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectOne")
    public HashMap selectOne(Integer userID, String userName,Integer currentPage, Integer pageSize){
        HashMap listMap = shipAddressSerive.selectOne(userID,userName,currentPage,pageSize);
        return listMap;
    }

    /**
     * 意见反馈分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/limitBack")
    public HashMap limitBack(Integer currentPage, Integer pageSize){
        HashMap listMap = feedBackService.limitBack(currentPage,pageSize);
        return listMap;
    }

    /**
     * 意见反馈查询总条数
     * @return
     */
    @GetMapping("/getNumber2")
    public int getNumber2(){
        int number = feedBackService.getNomber();
        if (number!=0){
            return number;
        }else{
            return 0;
        }
    }

    @GetMapping("/selectOne2")
    public HashMap selectOne2(Integer feedBackID, String userName,Integer currentPage, Integer pageSize){
        HashMap listMap = feedBackService.selectOne(feedBackID,userName,currentPage,pageSize);
        return listMap;
    }
}
