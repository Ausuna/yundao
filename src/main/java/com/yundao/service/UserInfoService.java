package com.yundao.service;

import com.yundao.bean.UserInfo;
import com.yundao.common.ResponseResult;

public interface UserInfoService {

    ResponseResult userLogin(String account, String pass);

    ResponseResult getUserList(int pageIndex, int pageSize);

    ResponseResult getUserByUserId(String userId);

    ResponseResult getUserByUserIdWithLogin(String userId);

    ResponseResult insertUserInfo(UserInfo userInfo);

    ResponseResult deleteUserInfoByUserId(String userId);

    ResponseResult updateUserInfo(UserInfo userInfo);
}
