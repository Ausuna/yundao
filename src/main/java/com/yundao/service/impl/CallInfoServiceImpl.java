package com.yundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yundao.bean.CallDetail;
import com.yundao.bean.CallInfo;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserSession;
import com.yundao.common.IdUtil;
import com.yundao.common.ResponseResult;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.common.UnicomRuntimeException;
import com.yundao.dao.CallInfoDao;
import com.yundao.service.CallInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CallInfoServiceImpl implements CallInfoService {

    @Autowired
    CallInfoDao callInfoDao;

    /**
     * 教师创建签到信息
     * @param classId
     * @return
     */
    @Override
    public ResponseResult createCallInfo(String classId) {
        UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
        Date date = new Date();
        CallInfo callInfo = new CallInfo();
        callInfo.setCallId(IdUtil.createID());
        callInfo.setClassId(classId);
        callInfo.setCreateBy(userInfo.getUserName());
        callInfo.setCreateDate(date);
        callInfo.setModifyBy(userInfo.getUserName());
        callInfo.setModifyDate(date);
        int res = callInfoDao.createCallInfo(callInfo);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL,"创建点名信息失败");
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }

    /**
     * 获取班级对应的点名信息表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult listCallInfo(int pageIndex, int pageSize, String classId) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<CallInfo> callInfos = callInfoDao.listCallInfo(classId);
        PageInfo<CallInfo> pageInfo = new PageInfo<>(callInfos);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有班级记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 学生点名产生签到详细信息
     * @param callId
     * @param userId
     * @return
     */
    @Override
    public ResponseResult createCallDetail(String userId, String callId) {
        UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
        Date date = new Date();
        CallDetail callDetail = new CallDetail();
        callDetail.setCallId(IdUtil.createID());
        callDetail.setUserId(userId);
        callDetail.setCreateBy(userInfo.getUserName());
        callDetail.setModifyBy(userInfo.getUserName());
        callDetail.setCreateDate(date);
        callDetail.setModifyDate(date);
        int res = callInfoDao.createCallDetail(callDetail);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL,"创建点名详细信息失败");
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }

    /**
     * 获取点名信息对应的详细签到信息
     * @param pageIndex
     * @param pageSize
     * @param callId
     * @return
     */
    @Override
    public ResponseResult listCallDetail(int pageIndex, int pageSize, String callId) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<CallDetail> callDetails = callInfoDao.listCallDetail(callId);
        PageInfo<CallDetail> pageInfo = new PageInfo<>(callDetails);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有班级记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 删除整个点名信息
     * @param callId
     * @return
     */
    @Override
    public ResponseResult deleteCallInfo(String callId) {
        int res = callInfoDao.deleteCallInfo(callId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL,"删除点名信息失败");
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }

    /**
     * 删除某个学生的点名信息
     * @param userId
     * @return
     */
    @Override
    public ResponseResult deleteCallDetail(String userId, String callId) {
        int res = callInfoDao.deleteCallDetail(userId, callId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL,"删除点名详细信息失败");
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }
}
