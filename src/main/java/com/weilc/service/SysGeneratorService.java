package com.weilc.service;

import com.weilc.utils.PageUtil;

import java.util.Map;

/**
 * @Description
 * @ClassName SysGeneratorService
 * @Author weilc
 * @Date 2020-11-16
 * @Version 1.0
 */
public interface SysGeneratorService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtil queryList(Map<String, Object> params);

    /**
     * 生成代码
     * @param tables
     * @return
     */
    byte[] generatorCode(String tables);
}
