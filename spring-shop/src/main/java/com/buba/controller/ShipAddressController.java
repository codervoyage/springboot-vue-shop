package com.buba.controller;

import com.buba.service.shipaddress.ShipAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ShipAddressController {
    @Autowired
    private ShipAddressService shipAddressSerive;

    /*分页查询*/
    @GetMapping("/limitAddress")
    public HashMap limitAddress(Integer currentPage, Integer pageSize){
        HashMap listMap = shipAddressSerive.limitAddress(currentPage,pageSize);
        return listMap;
    }
    /*获取总条数*/
    @GetMapping("/getNumber")
    public int getNumber(){
        int number = shipAddressSerive.getNomber();
        if (number!=0){
            return number;
        }else{
            return 0;
        }
    }
    /*根据条件查询*/
    @GetMapping("/selectOne")
    public HashMap selectOne(Integer userID, String userName,Integer currentPage, Integer pageSize){
        HashMap listMap = shipAddressSerive.selectOne(userID,userName,currentPage,pageSize);
        return listMap;
    }

}
