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

    @GetMapping("/classInfo")
    public ResponseResult listClassInfo() {
        return classInfoService.listClassInfo();
    }

    @PostMapping("/classInfo")
    public ResponseResult insertClassInfo(ClassInfo classInfo) {
        return classInfoService.insertClassInfo(classInfo);
    }

    @PutMapping("/classInfo")
    public ResponseResult updateClassInfo(ClassInfo classInfo) {
        return  classInfoService.updateClassInfo(classInfo);
    }

    @DeleteMapping("/classInfo/{classId}")
    public ResponseResult deleteClassInfo(@PathVariable("classId") int classId) {
        System.out.println(classId);
        return classInfoService.deleteClassInfo(classId);
    }
}
