package com.buba.controller;

import com.buba.pojo.user.User;
import com.buba.service.user.ShipAddressService;
import com.buba.service.user.UserHistoryService;
import com.buba.service.user.UserService;
import com.buba.service.user.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShipAddressService shipAddressSerive;
    @Autowired
    private FeedBackService feedBackService;
    @Autowired
    private UserHistoryService userHistoryService;

    /**
     * 获取user列表数据
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getUserList")
    public HashMap getUserList(Integer currentPage, Integer pageSize, Integer userId, String username, Integer phone) {
        return userService.getUserList(currentPage, pageSize, userId, username, phone);
    }

    @GetMapping("/updateUserOne")
    public List updateUserOne(User user) {
        int i = userService.updateUserOne(user);
        return null;
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/limitAddress")
    public HashMap limitAddress(Integer currentPage, Integer pageSize) {
        HashMap listMap = shipAddressSerive.limitAddress(currentPage, pageSize);
        return listMap;
    }

    /**
     * 获取总条数
     *
     * @return
     */
    @GetMapping("/getNumber")
    public int getNumber() {
        int number = shipAddressSerive.getNomber();
        if (number != 0) {
            return number;
        } else {
            return 0;
        }
    }

    /**
     * 根据条件查询
     *
     * @param userID
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectOne")
    public HashMap selectOne(Integer userID, String userName, Integer currentPage, Integer pageSize) {
        HashMap listMap = shipAddressSerive.selectOne(userID, userName, currentPage, pageSize);
        return listMap;
    }

    /**
     * 意见反馈分页查询
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/limitBack")
    public HashMap limitBack(Integer currentPage, Integer pageSize) {
        HashMap listMap = feedBackService.limitBack(currentPage, pageSize);
        return listMap;
    }

    /**
     * 意见反馈查询总条数
     *
     * @return
     */
    @GetMapping("/getNumber2")
    public int getNumber2() {
        int number = feedBackService.getNomber();
        if (number != 0) {
            return number;
        } else {
            return 0;
        }
    }

    /**
<<<<<<< HEAD
     * 反馈数据精确查找
=======
     * 意见反馈精确查询
     *
>>>>>>> 62f973db9f3afd1f08fca182d4ea6f2b119c78dd
     * @param feedBackID
     * @param userName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectOne2")
    public HashMap selectOne2(Integer feedBackID, String userName, Integer currentPage, Integer pageSize) {
        HashMap listMap = feedBackService.selectOne(feedBackID, userName, currentPage, pageSize);
        return listMap;
    }

/*
    =============搜索历史=================
*/

    @GetMapping("/limitHistory")
    public HashMap limitHistory(Integer currentPage, Integer pageSize) {
        HashMap listMap = userHistoryService.limitHistory(currentPage, pageSize);
        return listMap;
    }

    /**
     * 搜索历史获取总条数
     *
     * @return
     */
    @GetMapping("/getNumber3")
    public int getNumber3() {
        int number = userHistoryService.getNomber();
            return number;
    }

    /**
     * 搜索历史单查
     * @param searchID
     * @param keyword
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/selectOne3")
    public HashMap selectOne3(Integer searchID, String keyword, Integer currentPage, Integer pageSize) {
        HashMap listMap = userHistoryService.selectOne(searchID, keyword, currentPage, pageSize);
        return listMap;
    }
}
