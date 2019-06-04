package com.yundao.service.impl;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.dao.DictInfoDao;
import com.yundao.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictInfoServiceImpl implements DictInfoService {

    @Autowired
    DictInfoDao dictInfoDao;

    @Override
    public ResponseResult listDict() {
        List<DictInfo> dictInfos = dictInfoDao.listDict();
        if(dictInfos == null) {
            return new ResponseResult(false, UnicomResponseEnums.NO_RECORD);
        } else {
            return new ResponseResult(true, dictInfos);
        }
    }

    @Override
    public ResponseResult listDictDetail(String dictId) {
        List<DictDetail> dictDetails = dictInfoDao.listDictDetail(dictId);
        if(dictDetails.isEmpty()) {
            return new ResponseResult(false, UnicomResponseEnums.NO_RECORD);
        } else {
            return new ResponseResult(true, dictDetails);
        }
    }

    @Override
    public ResponseResult insertDictInfo(DictInfo dictInfo) {
        int res = dictInfoDao.insertDictInfo(dictInfo);
        if(res == 0) {
            return new ResponseResult(false, UnicomResponseEnums.INSERT_FAIL);
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }
}
