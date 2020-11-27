package com.weilc.model;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @ClassName TableEntity
 * @Author weilc
 * @Date 2020-11-26
 * @Version 1.0
 */
@Data
public class TableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表备注
     */
    private String comments;
    /**
     * 主键
     */
    private ColumnEntity pk;
    /**
     * 表的列名
     */
    private List<ColumnEntity> columns;
    /**
     * 大写类名
     */
    private String className;
    /**
     * 小写类名
     */
    private String classname;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
