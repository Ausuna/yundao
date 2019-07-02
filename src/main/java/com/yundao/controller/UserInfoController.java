package com.yundao.controller;

import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import com.yundao.common.PassToken;
import com.yundao.common.ResponseResult;
import com.yundao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 用户登录
     * @param account  帐号
     * @param pass   密码
     * @return
     */
    @PassToken
    @GetMapping("/userLogin")
    public ResponseResult userLogin(@RequestParam("account") String account, @RequestParam("pass") String pass) {
        return userInfoService.userLogin(account, pass);
    }

    /**
     * 获取用户列表分页数据
     * @param pageIndex  当前页数
     * @param pageSize   每页显示数
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult listUserInfo(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return userInfoService.getUserList(pageIndex, pageSize);
    }

    /**
     * 通过userId获取对应用户信息
     * @param userId
     * @return
     */
    @GetMapping("/userInfo/userId")
    public ResponseResult getUserByUserId(@RequestParam("userId") String userId) {
        return userInfoService.getUserByUserId(userId);
    }

    @GetMapping("/userInfoWithLogin")
    public ResponseResult getUserByUserIdWithLogin(@RequestParam("userId") String userId) {
        return userInfoService.getUserByUserIdWithLogin(userId);
    }

    /**
     * 用户注册
     * @param userInfo 用户基本信息
     * @param userLogin  用户登录信息
     * @return
     */
    @PassToken
    @PostMapping("/userRegister")
    public ResponseResult userRegister(UserInfo userInfo, UserLogin userLogin) {
        return userInfoService.userRegister(userInfo, userLogin);
    }

    /**
     * 插入用户信息
     * @param userInfo  用户基本信息
     * @return
     */
    @PostMapping("/userInfo")
    public ResponseResult insertUserInfo(UserInfo userInfo) {
        return userInfoService.insertUserInfo(userInfo);
    }

    /**
     * 根据userId删除对应用户信息
     * @param userId
     * @return
     */
    @DeleteMapping("/userInfo")
    public ResponseResult deleteUserInfoByUserId(@RequestParam("userId") String userId) {
        return userInfoService.deleteUserInfoByUserId(userId);
    }

    /**
     * 更新用户信息
     * @param userInfo 用户基本信息
     * @return
     */
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    /**
     * 用户修改密码
     * @param account  账号
     * @param oldPass  旧的密码
     * @param newPass  新的密码
     * @return
     */
    @PutMapping("/userLogin")
    public ResponseResult modifyPassword(@RequestParam("account") String account, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass) {
        return userInfoService.modifyPassword(account, oldPass, newPass);
    }
}
