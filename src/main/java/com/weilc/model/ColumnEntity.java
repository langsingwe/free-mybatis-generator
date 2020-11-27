package com.weilc.model;

import lombok.Data;

/**
 * @Description
 * @ClassName ColumnEntity
 * @Author weilc
 * @Date 2020-11-26
 * @Version 1.0
 */
@Data
public class ColumnEntity {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名类型
     */
    private String dataType;
    /**
     * 列名备注
     */
    private String comments;
    /**
     * 大写属性名
     */
    private String attrName;
    /**
     * 小写属性名
     */
    private String attrname;
    /**
     * 属性类型
     */
    private String attrType;

    private String extra;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }
}
