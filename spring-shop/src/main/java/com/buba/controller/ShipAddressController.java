package com.buba.controller;

import com.buba.service.ShipAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ShipAddressController {
    @Autowired
    private ShipAddressService shipAddressSerive;

    @GetMapping("/limitAddress")
    public HashMap limitAddress(Integer currentPage, Integer pageSize){
        HashMap listMap = shipAddressSerive.limitAddress(currentPage,pageSize);
        return listMap;
    }
    @GetMapping("/getNumber")
    public int getNumber(){
        int number = shipAddressSerive.getNomber();
        if (number!=0){
            return number;
        }else{
            return 0;
        }
    }
}
