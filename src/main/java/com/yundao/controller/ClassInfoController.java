package com.yundao.controller;

import com.yundao.bean.ClassInfo;
import com.yundao.common.ResponseResult;
import com.yundao.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassInfoController {

    @Autowired
    ClassInfoService classInfoService;


    @GetMapping("/classInfo/{pageIndex}/{pageSize}")
    public ResponseResult listClassInfo(@PathVariable("pageIndex") int pageIndex,@PathVariable("pageSize") int pageSize) {
        return classInfoService.listClassInfo(pageIndex, pageSize);
    }

    /**
     * 创建班级
     * @param classInfo 班级信息
     * @return
     */
    @PostMapping("/classInfo")
    public ResponseResult createClass(ClassInfo classInfo) {
        return classInfoService.createClass(classInfo);
    }

    /**
     * 根据班级id加入班级
     * @param classId
     * @return
     */
    @PostMapping("/userClass/{classId}")
    public ResponseResult joinClass(@PathVariable("classId") String classId) {
        return classInfoService.joinClass(classId);
    }

    @PutMapping("/classInfo")
    public ResponseResult updateClassInfo(ClassInfo classInfo) {
        return  classInfoService.updateClassInfo(classInfo);
    }

    @DeleteMapping("/classInfo/{classId}")
    public ResponseResult deleteClassInfo(@PathVariable("classId") String classId) {
        System.out.println(classId);
        return classInfoService.deleteClassInfo(classId);
    }
}
