package com.yundao.service;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;

import java.util.List;

public interface DictInfoService {

    ResponseResult listDict(int pageIndex, int pageSize);

    ResponseResult listDictDetail(int pageIndex, int pageSize, String dictId);

    ResponseResult insertDictInfo(DictInfo dictInfo);

    ResponseResult insertDictDetail(DictDetail dictDetail);

    ResponseResult updateDictInfo(DictInfo dictInfo);

    ResponseResult updateDictDetail(DictDetail dictDetail);

    ResponseResult updateDictDetails(List<DictDetail> dictDetails);

    ResponseResult deleteDictInfo(String dictId);

    ResponseResult deleteDictDetail(DictDetail dictDetail);
}
