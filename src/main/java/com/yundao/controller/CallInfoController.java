package com.yundao.controller;

import com.yundao.common.ResponseResult;
import com.yundao.service.CallInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallInfoController {

    @Autowired
    CallInfoService callInfoService;

    /**
     * 教师创建签到信息
     * @param classId
     * @return
     */
    @PostMapping("/callInfo")
    public ResponseResult createCallInfo(@RequestParam("classId") String classId) {
        return callInfoService.createCallInfo(classId);
    }

    /**
     * 获取班级对应的点名信息表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/callInfo")
    public ResponseResult listCallInfo(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("classId") String classId) {
        return callInfoService.listCallInfo(pageIndex, pageSize, classId);
    }

    /**
     * 学生点名产生签到详细信息
     * @param callId
     * @param userId
     * @return
     */
    @PostMapping("/callDetail")
    public ResponseResult createCallDetail(@RequestParam("callId") String callId, @RequestParam("userId") String userId) {
        return callInfoService.createCallDetail(userId, callId);
    }

    /**
     * 获取点名信息对应的详细签到信息
     * @param pageIndex
     * @param pageSize
     * @param callId
     * @return
     */
    @GetMapping("/callDetail")
    public ResponseResult listCallDetail(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("callId") String callId) {
        return callInfoService.listCallDetail(pageIndex, pageSize, callId);
    }

    /**
     * 删除整个点名信息
     * @param callId
     * @return
     */
    @DeleteMapping("/callInfo")
    public ResponseResult deleteCallInfo(@RequestParam("callId") String callId) {
        return callInfoService.deleteCallInfo(callId);
    }

    /**
     * 删除某个学生的点名信息
     * @param userId
     * @return
     */
    @DeleteMapping("/callDetail")
    public ResponseResult deleteCallDetail(@RequestParam("userId") String userId, @RequestParam("callId") String callId) {
        return callInfoService.deleteCallDetail(userId, callId);
    }
}
