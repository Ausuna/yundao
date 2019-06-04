package com.yundao.service.impl;

import com.yundao.bean.ClassInfo;
import com.yundao.common.ResponseResult;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.dao.ClassInfoDao;
import com.yundao.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    ClassInfoDao classInfoDao;

    @Override
    public ResponseResult listClassInfo() {
        List<ClassInfo> classInfos = classInfoDao.listClassInfo();
        if(classInfos.isEmpty()) {
            return new ResponseResult(false, UnicomResponseEnums.NO_RECORD);
        }else {
            return new ResponseResult(true, classInfos);
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

    @Override
    public ResponseResult updateClassInfo(ClassInfo classInfo) {
        int res = classInfoDao.updateClassInfo(classInfo);
        if(res == 0) {
            return new ResponseResult(false, UnicomResponseEnums.UPDATE_FAIL);
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    @Override
    public ResponseResult deleteClassInfo(int classId) {
        int res = classInfoDao.deleteClassInfo(classId);
        if(res == 0) {
            return new ResponseResult(false, UnicomResponseEnums.DELETE_FAIL);
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }
}
