package com.yundao.service;

import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import com.yundao.common.ResponseResult;

public interface UserInfoService {

    boolean checkUserParameter(UserInfo userInfo);

    boolean checkUserExist(UserInfo userInfo);

    ResponseResult modifyPassword(String account, String oldPass, String newPass);

    ResponseResult userRegister(UserInfo userInfo, UserLogin userLogin);

    ResponseResult userLogin(String account, String pass);

    ResponseResult getUserList(int pageIndex, int pageSize);

    ResponseResult getUserByUserId(String userId);

    ResponseResult getUserByUserIdWithLogin(String userId);

    ResponseResult insertUserInfo(UserInfo userInfo);

    ResponseResult deleteUserInfoByUserId(String userId);

    ResponseResult updateUserInfo(UserInfo userInfo);
}
