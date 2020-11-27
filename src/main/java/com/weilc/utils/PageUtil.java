package com.weilc.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description 分页实体工具类
 * @ClassName PageUtil
 * @Author weilc
 * @Date 2020-11-25
 * @Version 1.0
 */
@Data
public class PageUtil {

    private List<?> list;
    private int currPage;
    private int totalPage;
    private int totalCount;

    public PageUtil(List<?> list, int currPage, int totalPage, int totalCount) {
        this.list = list;
        this.currPage = currPage;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

    public PageUtil() {
    }
}
