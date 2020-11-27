package com.weilc.utils;

import com.weilc.exception.GenException;
import com.weilc.utils.constant.Constant;
import com.weilc.utils.http.ResultCodeEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * @Description
 * @ClassName DatabaseUtil
 * @Author weilc
 * @Date 2020-11-27
 * @Version 1.0
 */
public class DatabaseUtil {

    /**
     * 表名转java类名
     * @param tableName
     * @param tablePrefix
     * @return
     */
    public static String tableToJava(String tableName, String tablePrefix, String camelCase) {
        //去前缀
        if (StringUtils.isNotBlank(tablePrefix)) {
            for (String prefix : tablePrefix.split(Constant.ENGLISH_COMMA)) {
                tableName = tableName.replace(prefix, "");
            }
        }
        return columnToJava(tableName,camelCase);
    }

    /**
     * 列名转java
     * @param columnName
     * @return
     */
    public static String columnToJava(String columnName,String camelCase) {
        //驼峰命名不配置，默认为驼峰
        if (StringUtils.isBlank(camelCase)) {
            return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
        }
        if (Constant.STRING_FALSE.equalsIgnoreCase(camelCase)) {
            return columnName;
        } else if (Constant.STRING_TRUE.equalsIgnoreCase(camelCase)) {
            return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
        } else {
            throw new GenException(ResultCodeEnum.CAMELCASE_ERROR.getMsg());
        }
    }
}
