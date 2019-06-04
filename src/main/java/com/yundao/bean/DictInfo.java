package com.yundao.bean;

import java.util.List;

public class DictInfo extends BaseInfo {

    private String dictId;//数据字典编号
    private String dictDesc;//数据字典描述

    private List<DictDetail> dictDetails;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public List<DictDetail> getDictDetails() {
        return dictDetails;
    }

    public void setDictDetails(List<DictDetail> dictDetails) {
        this.dictDetails = dictDetails;
    }
}
