package com.yundao.service;

import com.yundao.common.ResponseResult;

public interface CallInfoService {

    ResponseResult createCallInfo(String classId);

    ResponseResult listCallInfo(int pageIndex, int pageSize, String classId);

    ResponseResult createCallDetail(String userId, String callId);

    ResponseResult listCallDetail(int pageIndex, int pageSize, String callId);

    ResponseResult deleteCallInfo(String callId);

    ResponseResult deleteCallDetail(String userId, String callId);
}
