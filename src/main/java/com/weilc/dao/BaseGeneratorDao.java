package com.weilc.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @ClassName BaseGeneratorDao
 * @Author weilc
 * @Date 2020-11-19
 * @Version 1.0
 */
public interface BaseGeneratorDao {
    List<Map<String, Object>> queryList(Map<String, Object> param);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
