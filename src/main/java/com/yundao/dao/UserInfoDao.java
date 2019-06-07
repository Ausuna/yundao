package com.yundao.dao;

import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoDao {

    UserLogin userLogin(String account);

    List<UserInfo> listUserInfo();

    UserInfo getUserByUserId(String userId);

    UserInfo getUserByUserIdWithLogin(String userId);

    int deleteUserByUserId(String userId);

    int insertUserInfo(UserInfo userInfo);

    int insertUserLogin(UserLogin userLogin);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> checkUserInfoByName(String userName);

    List<UserInfo> checkUserInfoByTel(String tel);

    List<UserInfo> checkUserInfoByEmail(String email);

    int modifyPassword(String account, String password);
}
