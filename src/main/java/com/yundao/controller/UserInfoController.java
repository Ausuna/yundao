package com.yundao.controller;

import com.yundao.bean.UserInfo;
import com.yundao.common.PassToken;
import com.yundao.common.ResponseResult;
import com.yundao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @PassToken
    @GetMapping("/userLogin/{account}/{pass}")
    public ResponseResult userLogin(@PathVariable("account") String account, @PathVariable("pass") String pass) {
        return userInfoService.userLogin(account, pass);
    }

    @GetMapping("/userInfo/{pageIndex}/{pageSize}")
    public ResponseResult listUserInfo(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        return userInfoService.getUserList(pageIndex, pageSize);
    }

    @GetMapping("/userInfo/{userId}")
    public ResponseResult getUserByUserId(@PathVariable("userId") String userId) {
        return userInfoService.getUserByUserId(userId);
    }

    @GetMapping("/userInfoWithLogin/{userId}")
    public ResponseResult getUserByUserIdWithLogin(@PathVariable("userId") String userId) {
        return userInfoService.getUserByUserIdWithLogin(userId);
    }

    @PostMapping("/userInfo")
    public ResponseResult insertUserInfo(UserInfo userInfo) {
        return userInfoService.insertUserInfo(userInfo);
    }

    @DeleteMapping("/userInfo/{userId}")
    public ResponseResult deleteUserInfoByUserId(@PathVariable("userId") String userId) {
        return userInfoService.deleteUserInfoByUserId(userId);
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }
}
