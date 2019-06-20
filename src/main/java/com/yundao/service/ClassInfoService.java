package com.yundao.service;

import com.yundao.bean.ClassInfo;
import com.yundao.bean.UserClass;
import com.yundao.common.ResponseResult;

public interface ClassInfoService {

    ResponseResult listClassInfo(int pageIndex, int pageSize);

    ResponseResult insertClassInfo(ClassInfo classInfo);

    ResponseResult createClass(ClassInfo classInfo);

    ResponseResult joinClass(String classId);

    ResponseResult updateClassInfo(ClassInfo classInfo);

    ResponseResult deleteClassInfo(String classId);
}
