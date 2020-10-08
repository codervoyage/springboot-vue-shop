package com.buba.utils;

import java.util.HashMap;

public  class RespMsg {
    public static HashMap getStatus(String msg,int status) {
        HashMap res = new HashMap();
        res.put("msg", msg);
        res.put("status", status);
        return res;
    }
}
