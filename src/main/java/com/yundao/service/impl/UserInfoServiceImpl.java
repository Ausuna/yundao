package com.yundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserLogin;
import com.yundao.bean.UserSession;
import com.yundao.common.*;
import com.yundao.dao.UserInfoDao;
import com.yundao.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    TokenUtil tokenUtil;


    /**
     *检查传入信息的合法性
     * @param userInfo
     * @return
     */
    @Override
    public boolean checkUserParameter(UserInfo userInfo) {
        if(userInfo.getUserName() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_NAME,"无效的用户名");
        }else if(userInfo.getTel() ==null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_MOBILE,"无效的手机号");
        }else if(userInfo.getEmail() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_EMAIL,"无效的邮箱地址");
        }
        return true;
    }

    /**
     * 检查信息是否已经存在
     * @param userInfo
     * @return
     */
    @Override
    public boolean checkUserExist(UserInfo userInfo) {
        if(!userInfoDao.checkUserInfoByName(userInfo.getUserName()).isEmpty()) {
            throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_NAME,"用户名已被使用");
        }else if(!userInfoDao.checkUserInfoByTel(userInfo.getTel()).isEmpty()){
            throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_MOBILE,"手机号已被使用");
        }else if(!userInfoDao.checkUserInfoByEmail(userInfo.getEmail()).isEmpty()){
            throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_EMAIL,"邮箱地址已被使用");
        }
        return true;
    }

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    @Override
    public ResponseResult userRegister(UserInfo userInfo, UserLogin userLogin) {

        if(checkUserParameter(userInfo)) {
            if(checkUserExist(userInfo)) {
                Date date = new Date();
                userInfo.setCreateDate(date);
                userInfo.setCreateBy(userInfo.getUserName());
                userInfo.setModifyDate(date);
                userInfo.setModifyBy(userInfo.getUserName());
                userInfo.setUserId(IdUtil.createID());
                userInfoDao.insertUserInfo(userInfo);

                UserLogin userLogin1 = new UserLogin();
                BeanUtils.copyProperties(userLogin, userLogin1);
                userLogin1.setUserId(userInfo.getUserId());
                userLogin1.setCreateBy(userInfo.getUserName());
                userLogin1.setModifyBy(userInfo.getUserName());
                userLogin1.setCreateDate(date);
                userLogin1.setModifyDate(date);
                userLogin1.setAccount(userInfo.getUserName());
                userLogin1.setLoginType("username");
                userInfoDao.insertUserLogin(userLogin1);

                UserLogin userLogin2 = new UserLogin();
                BeanUtils.copyProperties(userLogin, userLogin2);
                userLogin2.setUserId(userInfo.getUserId());
                userLogin2.setCreateDate(date);
                userLogin2.setModifyDate(date);
                userLogin2.setCreateBy(userInfo.getUserName());
                userLogin2.setModifyBy(userInfo.getUserName());
                userLogin2.setAccount(userInfo.getTel());
                userLogin2.setLoginType("tel");
                userInfoDao.insertUserLogin(userLogin2);

                UserLogin userLogin3 = new UserLogin();
                BeanUtils.copyProperties(userLogin, userLogin3);
                userLogin3.setUserId(userInfo.getUserId());
                userLogin3.setCreateDate(date);
                userLogin3.setCreateBy(userInfo.getUserName());
                userLogin3.setModifyDate(date);
                userLogin3.setModifyBy(userInfo.getUserName());
                userLogin3.setAccount(userInfo.getEmail());
                userLogin3.setLoginType("email");
                userInfoDao.insertUserLogin(userLogin3);
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.FAIL_REGISTER,"注册失败");
    }

    /**
     * 用户修改密码
     * @param account  账号
     * @param oldPass  旧的密码
     * @param newPass  新的密码
     * @return
     */
    public ResponseResult modifyPassword(String account, String oldPass, String newPass) {
        UserLogin userLogin = userInfoDao.userLogin(account);
        if(userLogin == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_USER_EXIST, "输入的用户名不存在");
        } else {
            if(!userLogin.getPassword().equals(oldPass)) {
                throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_PASSWORD, "输入的密码错误");
            }else {
                Date date = new Date();
                userLogin.setModifyDate(date);
                UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
                userLogin.setModifyBy(userInfo.getUserName());
                userLogin.setPassword(newPass);
                int res = userInfoDao.modifyPassword(userLogin);
                return new ResponseResult(true, UnicomResponseEnums.EDITPWD_SUCCESS);
            }
        }
    }

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

    /**
     * 获取用户列表
     * @param pageIndex  当前页面号
     * @param pageSize   每页多少记录
     * @return     分页数据
     */
    @Override
    public ResponseResult getUserList(int pageIndex, int pageSize) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<UserInfo> userInfos = userInfoDao.listUserInfo();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有用户记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 通过userId获取对应用户信息
     * @param userId
     * @return
     */
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

    /**
     * 插入用户信息
     * @param userInfo  用户基本信息
     * @return
     */
    @Override
    public ResponseResult insertUserInfo(UserInfo userInfo) {
        try {
            int res = userInfoDao.insertUserInfo(userInfo);
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }catch (Exception e) {
            e.printStackTrace();
            throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL,"添加数据失败");
        }
    }

    /**
     * 根据userId删除对应用户信息
     * @param userId
     * @return
     */
    @Override
    public ResponseResult deleteUserInfoByUserId(String userId) {
        int res = userInfoDao.deleteUserByUserId(userId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL);
        }else {
            userInfoDao.deleteUserLoginByUserId(userId);
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     * 更新用户信息
     * @param userInfo 用户基本信息
     * @return
     */
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
