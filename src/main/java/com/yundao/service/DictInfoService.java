package com.yundao.service;

import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;

public interface DictInfoService {

    ResponseResult listDict();

    ResponseResult listDictDetail(String dictId);

    ResponseResult insertDictInfo(DictInfo dictInfo);
}
