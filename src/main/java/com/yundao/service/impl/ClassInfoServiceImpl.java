package com.yundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yundao.bean.ClassInfo;
import com.yundao.bean.UserClass;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserSession;
import com.yundao.common.IdUtil;
import com.yundao.common.ResponseResult;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.common.UnicomRuntimeException;
import com.yundao.dao.ClassInfoDao;
import com.yundao.service.ClassInfoService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    ClassInfoDao classInfoDao;

    public boolean checkClassParameter(ClassInfo classInfo) {
        if(classInfo.getClassName() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_CLASS,"无效的班级名称");
        }else if(classInfo.getClassCourse() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_COURSE,"无效的课程名称");
        }else if(classInfo.getClassTime() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_TIME,"无效的课时");
        }else if(classInfo.getClassCollege() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_COLLEGE,"无效的学院名称");
        }else if(classInfo.getClassTerm() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_TERM,"无效的学期名称");
        }
        return true;
    }

    /**
     * 获取所有班级列表分页
     * @param pageIndex  当前分页
     * @param pageSize  每页多少数据
     * @return  分页数据
     */
    @Override
    public ResponseResult listClassInfo(int pageIndex, int pageSize) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ClassInfo> classInfos = classInfoDao.listClassInfo();
        PageInfo<ClassInfo> pageInfo = new PageInfo<>(classInfos);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有班级记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 获得当前用户所属的所有课程列表
     * @param userId
     * @return
     */
    @Override
    public ResponseResult getClassInfosByUserId(String userId) {
        List<ClassInfo> classInfos = classInfoDao.getClassInfosByUserId(userId);
        if(!classInfos.isEmpty()) {
            return new ResponseResult(true, classInfos);
        }else {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "还没有加入或创建任何班级");
        }
    }

    @Override
    public ResponseResult insertClassInfo(ClassInfo classInfo) {
        int res = classInfoDao.insertClassInfo(classInfo);
        if(res == 0) {
            return new ResponseResult(false, UnicomResponseEnums.INSERT_FAIL);
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     *创建班级
     * @param classInfo 班级信息
     * @return
     */
    @Override
    public ResponseResult createClass(ClassInfo classInfo) {
        if(checkClassParameter(classInfo)) {
            UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
            Date date = new Date();
            classInfo.setClassId(IdUtil.createID());
            classInfo.setClassTeacher(userInfo.getUserId());
            classInfo.setClassStudent(0);
            classInfo.setClassStatus((byte) 1);
            classInfo.setCreateBy(userInfo.getUserName());
            classInfo.setCreateDate(date);
            classInfo.setModifyBy(userInfo.getUserName());
            classInfo.setModifyDate(date);
            int res = classInfoDao.insertClassInfo(classInfo);
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "创建班级失败");
            }else {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "创建班级失败");
    }

    /**
     * 根据班级id加入班级
     * @param classId 班级ID
     * @return
     */
    @Override
    public ResponseResult joinClass(String classId) {
        Date date = new Date();
        UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
        UserClass userClass = new UserClass();
        userClass.setClassId(classId);
        userClass.setUserId(userInfo.getUserId());
        userClass.setGrade(0);
        userClass.setCreateBy(userInfo.getUserName());
        userClass.setCreateDate(date);
        userClass.setModifyBy(userInfo.getUserName());
        userClass.setModifyDate(date);
        if(classInfoDao.checkUserClass(userClass).isEmpty()) {
            int res = classInfoDao.joinClass(userClass);
            if(res == 1) {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }else {
                throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL,"加入班级失败");
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_JOINCLASS, "重复加入班级");
    }

    /**
     * 修改班级信息
     * @param classInfo
     * @return
     */
    @Override
    public ResponseResult updateClassInfo(ClassInfo classInfo) {
        Date date = new Date();
        UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
        classInfo.setModifyBy(userInfo.getUserName());
        classInfo.setModifyDate(date);
        int res = classInfoDao.updateClassInfo(classInfo);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.UPDATE_FAIL, "更新班级失败");
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     * 根据班级id退出班级
     * @param classId
     * @return
     */
    @Override
    public ResponseResult quitClass(String classId) {
        UserInfo userInfo = (UserInfo) UserSession.get("currentUser");
        String userId = userInfo.getUserId();
        int res = classInfoDao.quitClass(classId, userId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL, "退出班级失败");
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     * 根据班级id删除班级
     * @param classId
     * @return
     */
    @Override
    public ResponseResult deleteClassInfo(String classId) {
        int res = classInfoDao.deleteClassInfo(classId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL, "删除班级失败");
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }
}
