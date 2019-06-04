package com.yundao.service;

import com.yundao.bean.ClassInfo;
import com.yundao.common.ResponseResult;

public interface ClassInfoService {

    ResponseResult listClassInfo();

    ResponseResult insertClassInfo(ClassInfo classInfo);

    ResponseResult updateClassInfo(ClassInfo classInfo);

    ResponseResult deleteClassInfo(int classId);
}
