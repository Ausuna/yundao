package com.yundao.dao;

import com.yundao.bean.ClassInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassInfoDao {

    List<ClassInfo> listClassInfo();

    int insertClassInfo(ClassInfo classInfo);

    int updateClassInfo(ClassInfo classInfo);

    int deleteClassInfo(int classId);
}
