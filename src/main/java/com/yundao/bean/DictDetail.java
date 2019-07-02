package com.yundao.bean;

public class DictDetail extends BaseInfo {

    private String dictId;//数据字典编号
    private String itemId;//项目编号
    private String itemName;//项目名称
    private byte isDefault;//是否默认值
    private int dictOrder;//项目序号

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(byte isDefault) {
        this.isDefault = isDefault;
    }

    public int getDictOrder() {
        return dictOrder;
    }

    public void setDictOrder(int dictOrder) {
        this.dictOrder = dictOrder;
    }
}
