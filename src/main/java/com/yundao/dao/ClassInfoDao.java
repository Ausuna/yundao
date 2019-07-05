package com.yundao.dao;

import com.yundao.bean.ClassInfo;
import com.yundao.bean.UserClass;
import com.yundao.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassInfoDao {

    List<ClassInfo> listClassInfo();

    List<ClassInfo> getClassInfosByUserId(String userId);

    int insertClassInfo(ClassInfo classInfo);

    int updateClassInfo(ClassInfo classInfo);

    int quitClass(String classId, String userId);

    int deleteClassInfo(String classId);

    int joinClass(UserClass userClass);

    List<UserClass> checkUserClass(UserClass userClass);

    List<UserInfo> getClassUser(String classId);
}
