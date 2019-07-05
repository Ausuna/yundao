package com.yundao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.bean.UserInfo;
import com.yundao.bean.UserSession;
import com.yundao.common.IdUtil;
import com.yundao.common.ResponseResult;
import com.yundao.common.UnicomResponseEnums;
import com.yundao.common.UnicomRuntimeException;
import com.yundao.dao.DictInfoDao;
import com.yundao.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DictInfoServiceImpl implements DictInfoService {

    @Autowired
    DictInfoDao dictInfoDao;

    public boolean checkDictInfoParameter(DictInfo dictInfo) {
        if(dictInfo.getDictDesc() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_DICT,"无效的数据字典描述");
        }
        if(!dictInfoDao.checkDictInfo(dictInfo.getDictDesc()).isEmpty()) {
            throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_DICT,"无效的数据字典描述");
        }
        return true;
    }

    public boolean checkDictDetailParemter(DictDetail dictDetail) {
        if(dictDetail.getItemName() == null) {
            throw new UnicomRuntimeException(UnicomResponseEnums.INVALID_DICT,"无效的数据字典详细项目名");
        }
        if(!dictInfoDao.checkDictDetail(dictDetail.getDictId(), dictDetail.getItemName()).isEmpty()) {
            throw new UnicomRuntimeException(UnicomResponseEnums.REPEAT_DICT,"无效的数据字典详细项目名");
        }
        return true;
    }

    /**
     * 获取数据字典列表分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult listDict(int pageIndex, int pageSize) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<DictInfo> dictInfos = dictInfoDao.listDict();
        PageInfo<DictInfo> pageInfo = new PageInfo<>(dictInfos);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有字典记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 获取对应数据字典的详细列表
     * @param pageIndex
     * @param pageSize
     * @param dictId
     * @return
     */
    @Override
    public ResponseResult listDictDetail(int pageIndex, int pageSize, String dictId) {
        if(pageIndex <=0 || pageSize <=0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.BAD_REQUEST,"请求参数错误");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<DictDetail> dictDetails = dictInfoDao.listDictDetail(dictId);
        PageInfo<DictDetail> pageInfo = new PageInfo<>(dictDetails);
        if(pageInfo.getSize() == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.NO_RECORD, "数据库中没有字典详细记录");
        } else {
            return new ResponseResult(true, pageInfo);
        }
    }

    /**
     * 新增数据字典
     * @param dictInfo
     * @return
     */
    @Override
    public ResponseResult insertDictInfo(DictInfo dictInfo) {
        if(checkDictInfoParameter(dictInfo)) {
            Date date = new Date();
            UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
            dictInfo.setDictId(IdUtil.createID());
            dictInfo.setCreateBy(userInfo.getUserName());
            dictInfo.setCreateDate(date);
            dictInfo.setModifyBy(userInfo.getUserName());
            dictInfo.setModifyDate(date);
            int res = dictInfoDao.insertDictInfo(dictInfo);
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "插入数据字典失败");
            }else {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "插入数据字典失败");
    }

    /**
     * 新增对应数据字典详细
     * @param dictDetail
     * @return
     */
    @Override
    public ResponseResult insertDictDetail(DictDetail dictDetail) {
        if(checkDictDetailParemter(dictDetail)) {
            Date date = new Date();
            UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
            dictDetail.setItemId(IdUtil.createID());
            dictDetail.setModifyBy(userInfo.getUserName());
            dictDetail.setModifyDate(date);
            dictDetail.setCreateBy(userInfo.getUserName());
            dictDetail.setCreateDate(date);
            dictDetail.setIsDefault((byte)0);
            int order = dictInfoDao.countDictDetailByDictId(dictDetail.getDictId());
            dictDetail.setDictOrder(order + 1);
            int res = dictInfoDao.insertDictDetail(dictDetail);
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "插入数据字典详细失败");
            }else {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.INSERT_FAIL, "插入数据字典详细失败");
    }

    /**
     * 编辑数据字典信息
     * @param dictInfo
     * @return
     */
    @Override
    public ResponseResult updateDictInfo(DictInfo dictInfo) {
        Date date = new Date();
        UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
        dictInfo.setModifyDate(date);
        dictInfo.setModifyBy(userInfo.getUserName());
        int res = dictInfoDao.updateDictInfo(dictInfo);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.UPDATE_FAIL, "编辑数据字典失败");
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     * 单个编辑数据字典详细信息
     * @param dictDetail
     * @return
     */
    @Override
    public ResponseResult updateDictDetail(DictDetail dictDetail) {
        Date date = new Date();
        UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
        dictDetail.setModifyDate(date);
        dictDetail.setModifyBy(userInfo.getUserName());
        if(dictDetail.getIsDefault() == (byte)1) {
            dictInfoDao.updateDictDetailDefault(dictDetail.getDictId());
        }
        int res = dictInfoDao.updateDictDetail(dictDetail);
        List<DictDetail> dictDetails = dictInfoDao.dictDetailEditList(dictDetail.getDictId(),dictDetail.getItemId(), dictDetail.getDictOrder());
        for(int i = 0; i < dictDetails.size(); i++) {
            dictDetails.get(i).setDictOrder(dictDetail.getDictOrder() + i + 1);
        }
        ResponseResult responseResult = updateDictDetails(dictDetails);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.UPDATE_FAIL, "编辑数据字典详细失败");
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }

    /**
     * 批量编辑数据字典详细信息
     * @param dictDetails
     * @return
     */
    @Override
    public ResponseResult updateDictDetails(List<DictDetail> dictDetails) {
        Date date = new Date();
        UserInfo userInfo = (UserInfo)UserSession.get("currentUser");
        for(int i = 0; i < dictDetails.size(); i++) {
            DictDetail dictDetail = dictDetails.get(i);
            dictDetail.setModifyDate(date);
            dictDetail.setModifyBy(userInfo.getUserName());
            int res = dictInfoDao.updateDictDetail(dictDetail);
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.UPDATE_FAIL, "编辑数据字典详细失败");
            }
        }
        return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
    }

    /**
     * 删除数据字典
     * @param dictId
     * @return
     */
    @Override
    public ResponseResult deleteDictInfo(String dictId) {
        int res = dictInfoDao.deleteDictInfo(dictId);
        if(res == 0) {
            throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL, "删除数据字典失败");
        }else {
            return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
        }
    }

    /**
     * 删除数据字典详细信息
     * @param dictDetail
     * @return
     */
    @Override
    public ResponseResult deleteDictDetail(DictDetail dictDetail) {
        List<DictDetail> dictDetails = dictInfoDao.dictDetailEditList(dictDetail.getDictId(),dictDetail.getItemId(), dictDetail.getDictOrder());
        for(int i = 0; i < dictDetails.size(); i++) {
            dictDetails.get(i).setDictOrder(dictDetail.getDictOrder() + i);
        }
        ResponseResult responseResult = updateDictDetails(dictDetails);
        if(responseResult.isSuccess()) {
            int res = dictInfoDao.deleteDictDetail(dictDetail.getItemId());
            if(res == 0) {
                throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL, "删除数据字典详细失败");
            }else {
                return new ResponseResult(true, UnicomResponseEnums.SUCCESS_OPTION);
            }
        }
        throw new UnicomRuntimeException(UnicomResponseEnums.DELETE_FAIL, "删除数据字典详细失败");
    }
}
