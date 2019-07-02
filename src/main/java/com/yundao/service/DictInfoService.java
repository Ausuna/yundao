package com.yundao.service;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;

public interface DictInfoService {

    ResponseResult listDict(int pageIndex, int pageSize);

    ResponseResult listDictDetail(int pageIndex, int pageSize, String dictId);

    ResponseResult insertDictInfo(DictInfo dictInfo);

    ResponseResult insertDictDetail(DictDetail dictDetail);
}
