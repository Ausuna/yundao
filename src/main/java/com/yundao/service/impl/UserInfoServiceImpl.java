package com.yundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import com.yundao.common.ResponseResult;
import com.yundao.common.TokenUtil;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.common.UnicomRuntimeException;
import com.yundao.dao.UserInfoDao;
import com.yundao.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    TokenUtil tokenUtil;

    /**
     * 用户登录处理服务
     * @param account 帐号
     * @param pass 密码
     * @return 登录结果
     */
    @Override
    public ResponseResult userLogin(String account, String pass) {
        UserLogin userLogin = userInfoDao.userLogin(account);
        if(userLogin == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_USER_EXIST, "输入的用户名不存在");
        } else {
            if(!userLogin.getPassword().equals(pass)) {
                throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_PASSWORD, "输入的密码错误");
            }else {
                String token = tokenUtil.getToken(userLogin.getAccount(), userLogin.getPassword());
                return new ResponseResult(true, token, UnicomResponseEnums.LOGIN_SUCCESS);
            }
        }
    }

    @Override
    public ResponseResult getUserList(int pageIndex, int pageSize) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<UserInfo> userInfos = userInfoDao.listUserInfo();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    @Override
    public ResponseResult getUserByUserId(String userId) {
        UserInfo userInfo = userInfoDao.getUserByUserId(userId);
        if(userInfo == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有记录");
        }else {
            return new ResponseResult(true, userInfo);
        }
    }

    @Override
    public ResponseResult getUserByUserIdWithLogin(String userId) {
        UserInfo userInfo = userInfoDao.getUserByUserIdWithLogin(userId);
        if(userInfo == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有记录");
        }else {
            return new ResponseResult(true, userInfo);
        }
}

    @Override
    public ResponseResult insertUserInfo(UserInfo userInfo) {
        try {
            //userInfo.setCreateDate();
            //userInfo.setModifyDate();
            int res = userInfoDao.insertUserInfo(userInfo);
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }catch (Exception e) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL);
        }
    }

    @Override
    public ResponseResult deleteUserInfoByUserId(String userId) {
        try {
            int res = userInfoDao.deleteUserByUserId(userId);
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL);
            }else {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }catch (Exception e) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL);
        }
    }

    @Override
    public ResponseResult updateUserInfo(UserInfo userInfo) {
        int res = userInfoDao.updateUserInfo(userInfo);
        if(res == 0) {
            return new ResponseResult(false, UnicomResponseEnums.UPDATE_FAIL);
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }
}
