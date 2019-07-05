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


    /**
     * 获得班级分页数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/classInfo")
    public ResponseResult listClassInfo(@RequestParam("pageIndex") int pageIndex,@RequestParam("pageSize") int pageSize) {
        return classInfoService.listClassInfo(pageIndex, pageSize);
    }

    /**
     * 获得当前用户所属的所有课程列表
     * @param userId
     * @return
     */
    @GetMapping("/classInfo/userId")
    public ResponseResult getClassInfosByUserId(@RequestParam("userId") String userId) {
        return classInfoService.getClassInfosByUserId(userId);
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
    @PostMapping("/userClass")
    public ResponseResult joinClass(@RequestParam("classId") String classId) {
        return classInfoService.joinClass(classId);
    }

    /**
     * 修改班级信息
     * @param classInfo
     * @return
     */
    @PutMapping("/classInfo")
    public ResponseResult updateClassInfo(ClassInfo classInfo) {
        return  classInfoService.updateClassInfo(classInfo);
    }

    /**
     * 根据班级id退出班级
     * @param classId
     * @return
     */
    @DeleteMapping("/userClass")
    public ResponseResult quitClass(@RequestParam("classId") String classId) {
        return classInfoService.quitClass(classId);
    }

    /**
     * 根据班级id删除班级
     * @param classId
     * @return
     */
    @DeleteMapping("/classInfo")
    public ResponseResult deleteClassInfo(@RequestParam("classId") String classId) {
        System.out.println(classId);
        return classInfoService.deleteClassInfo(classId);
    }

    /**
     * 获得班级所有的学生列表
     * @param classId
     * @return
     */
    @GetMapping("/classInfo/userInfo")
    public ResponseResult getClassUser(@RequestParam("classId") String classId) {
        return classInfoService.getClassUser(classId);
    }
}
