package com.yundao.controller;

import com.yundao.bean.DictInfo;
import com.yundao.common.ResponseResult;
import com.yundao.service.DictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictInfoController {

    @Autowired
    DictInfoService dictInfoService;

    @GetMapping("/dictInfo")
    public ResponseResult listDict() {
       return dictInfoService.listDict();
    }

    @GetMapping("/dictDetail/{dictId}")
    public ResponseResult listDictDetail(@PathVariable("dictId") String dictId) {
        return dictInfoService.listDictDetail(dictId);
    }

    @PostMapping("/dictInfo")
    public ResponseResult insertDictInfo(DictInfo dictInfo) {
        System.out.println("sssss: "+dictInfo);
        return dictInfoService.insertDictInfo(dictInfo);
    }
}
