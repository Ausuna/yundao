package com.yundao.dao;

import com.yundao.bean.CallDetail;
import com.yundao.bean.CallInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CallInfoDao {

    int createCallInfo(CallInfo callInfo);

    List<CallInfo> listCallInfo(String classId);

    int createCallDetail(CallDetail callDetail);

    List<CallDetail> listCallDetail(String callId);

    int deleteCallInfo(String callId);

    int deleteCallDetail(String userId, String callId);
}
