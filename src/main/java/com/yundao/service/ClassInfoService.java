package com.yundao.service;

import com.yundao.bean.ClassInfo;
import com.yundao.bean.UserClass;
import com.yundao.common.ResponseResult;

public interface ClassInfoService {

    ResponseResult listClassInfo(int pageIndex, int pageSize);

    ResponseResult getClassUser(String classId);

    ResponseResult getClassInfosByUserId(String userId);

    ResponseResult createClass(ClassInfo classInfo);

    ResponseResult joinClass(String classId);

    ResponseResult updateClassInfo(ClassInfo classInfo);

    ResponseResult quitClass(String classId);

    ResponseResult deleteClassInfo(String classId);
}
