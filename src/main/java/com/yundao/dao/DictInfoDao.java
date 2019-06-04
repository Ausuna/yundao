package com.yundao.dao;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictInfoDao {

    List<DictInfo> listDict();

    List<DictDetail> listDictDetail(String dictId);

    int insertDictInfo(DictInfo dictInfo);
}
