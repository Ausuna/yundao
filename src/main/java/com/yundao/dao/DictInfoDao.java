package com.yundao.dao;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictInfoDao {

    List<DictInfo> listDict();

    List<DictDetail> listDictDetail(String dictId);

    List<DictDetail> dictDetailEditList(String dictId,String itemId, int dict_order);

    int insertDictInfo(DictInfo dictInfo);

    int insertDictDetail(DictDetail dictDetail);

    int countDictDetailByDictId(String dictId);

    int updateDictInfo(DictInfo dictInfo);

    int updateDictDetail(DictDetail dictDetail);

    int updateDictDetailDefault(String dictId);

    int deleteDictInfo(String dictId);

    int deleteDictDetail(String itemId);

    List<DictInfo> checkDictInfo(String dictDesc);

    List<DictDetail> checkDictDetail(String dictId, String itemName);
}
