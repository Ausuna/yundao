package com.yundao.controller;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;
import com.yundao.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DictInfoController {

    @Autowired
    DictInfoService dictInfoService;

    /**
     * 获取数据字典分页数据
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/dictInfo")
    public ResponseResult listDict(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
       return dictInfoService.listDict(pageIndex, pageSize);
    }

    /**
     * 获取对应数据字典的详细列表
     * @param pageIndex
     * @param pageSize
     * @param dictId
     * @return
     */
    @GetMapping("/dictDetail")
    public ResponseResult listDictDetail(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("dictId") String dictId) {
        return dictInfoService.listDictDetail(pageIndex, pageSize, dictId);
    }

    /**
     * 新增数据字典
     * @param dictInfo
     * @return
     */
    @PostMapping("/dictInfo")
    public ResponseResult insertDictInfo(DictInfo dictInfo) {
        return dictInfoService.insertDictInfo(dictInfo);
    }

    /**
     * 新增对应数据字典详细
     * @param dictDetail
     * @return
     */
    @PostMapping("/dictDetail")
    public ResponseResult insertDictDetail(DictDetail dictDetail) {
        return dictInfoService.insertDictDetail(dictDetail);
    }

    /**
     * 编辑数据字典信息
     * @param dictInfo
     * @return
     */
    @PutMapping("/dictInfo")
    public ResponseResult updateDictInfo(DictInfo dictInfo) {
        return dictInfoService.updateDictInfo(dictInfo);
    }

    /**
     * 编辑数据字典详细信息
     * @param dictDetail
     * @return
     */
    @PutMapping("/dictDetail")
    public ResponseResult updateDictInfo(DictDetail dictDetail) {
        return dictInfoService.updateDictDetail(dictDetail);
    }

    /**
     * 删除数据字典
     * @param dictId
     * @return
     */
    @DeleteMapping("/dictInfo")
    public  ResponseResult deleteDictInfo(@RequestParam("dictId") String dictId) {
        return dictInfoService.deleteDictInfo(dictId);
    }

    /**
     * 删除数据字典详细信息
     * @param dictDetail
     * @return
     */
    @DeleteMapping("/dictDetail")
    public  ResponseResult deleteDictDetail(DictDetail dictDetail) {
        return dictInfoService.deleteDictDetail(dictDetail);
    }
}
