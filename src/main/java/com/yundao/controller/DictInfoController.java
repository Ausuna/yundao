package com.yundao.controller;

import com.yundao.bean.DictDetail;
import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;
import com.yundao.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DictInfoController {

    @Autowired
    DictInfoService dictInfoService;

    /**
     * 获取字典分页数据
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
        System.out.println("sssss: "+dictInfo);
        return dictInfoService.insertDictInfo(dictInfo);
    }

    /**
     * 新增对应数据字典详细
     * @param dictDetail
     * @return
     */
    @PostMapping("/dictDetail")
    public ResponseResult insertDictDetail(DictDetail dictDetail) {
        System.out.println("sssss: "+dictDetail);
        return dictInfoService.insertDictDetail(dictDetail);
    }


}
