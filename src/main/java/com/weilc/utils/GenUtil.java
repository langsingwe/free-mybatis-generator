package com.weilc.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.weilc.exception.GenException;
import com.weilc.model.ColumnEntity;
import com.weilc.model.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description
 * @ClassName GenUtil
 * @Author weilc
 * @Date 2020-11-26
 * @Version 1.0
 */
public class GenUtil {

    public static void generatorCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) {
        boolean hasBigDecimal = false;
        //配置信息
        Configuration genConfig = ResourceUtil.getConfig("generator.properties");
        Configuration config = ResourceUtil.getConfig("config.properties");
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        String className = DatabaseUtil.tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"), config.getString("camelCase"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        //列信息
        List<ColumnEntity> columnList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(columns)) {
            for (Map<String, String> column : columns) {
                ColumnEntity columnEntity = new ColumnEntity();
                columnEntity.setColumnName(column.get("columnName"));
                columnEntity.setDataType(column.get("dataType"));
                columnEntity.setComments(column.get("columnComment"));
                columnEntity.setExtra(column.get("extra"));
                //列名转成Java属性
                String attrName = DatabaseUtil.columnToJava(columnEntity.getColumnName(), config.getString("camelCase"));
                columnEntity.setAttrName(attrName);
                columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
                //列的数据类型，转换为Java类型
                String attrType = genConfig.getString(columnEntity.getDataType(), "unknownType");
                columnEntity.setAttrType(attrType);
                if ("BigDecimal".equals(attrType)) {
                    hasBigDecimal = true;
                }
                if ("PRI".equalsIgnoreCase(column.get("columnKey"))) {
                    tableEntity.setPk(columnEntity);
                }
                columnList.add(columnEntity);
            }
            tableEntity.setColumns(columnList);
            //没有主键，默认第一个字段为主键
            if (null == tableEntity.getPk()) {
                tableEntity.setPk(tableEntity.getColumns().get(0));
            }
            //模板引擎加载资源
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER,"classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            //模板数据
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("tableName", tableEntity.getTableName());
            dataMap.put("comments", tableEntity.getComments());
            dataMap.put("pk", tableEntity.getPk());
            dataMap.put("className", tableEntity.getClassName());
            dataMap.put("classname", tableEntity.getClassname());
            dataMap.put("pathName", tableEntity.getClassName().toLowerCase());
            dataMap.put("columns", tableEntity.getColumns());
            dataMap.put("hasBigDecimal", hasBigDecimal);
            dataMap.put("package", config.getString("package"));
            dataMap.put("author", config.getString("author"));
            dataMap.put("email", config.getString("email"));
            dataMap.put("xmlcrud", StrUtil.isBlank(config.getString("xmlcrud")) ? true : Boolean.valueOf(config.getString("xmlcrud")));
            dataMap.put("datetime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            VelocityContext ctx = new VelocityContext(dataMap);
            //获取模板列表
            List<String> templates = ResourceUtil.getTemplates();
            for (String template : templates) {
                StringWriter sw = new StringWriter();
                Template tpl = ve.getTemplate(template, "UTF-8");
                tpl.merge(ctx, sw);
                try {
                    zip.putNextEntry(new ZipEntry(getZipFileName(template, tableEntity.getClassName(),config.getString("package"))));
                    IOUtils.write(sw.toString(),zip,"UTF-8");
                    IOUtils.closeQuietly();
                    zip.closeEntry();
                } catch (IOException e) {
                    throw new GenException("生成模板失败，表名：" + tableEntity.getTableName(), e);
                }
            }
        }
    }

    /**
     * zip文件名字 main/java/com/weilc/entity/TestEntity.java
     * @param template
     * @param className
     * @param packageName
     * @return
     */
    public static String getZipFileName(String template, String className, String packageName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }
        //template/Entity.java.vm ; 取entity
        String moduleType = template.substring(template.indexOf("/") + 1, template.indexOf(".")).toLowerCase();

        String classFullName = className + template.substring(template.indexOf("/") + 1, template.lastIndexOf("."));

        if (template.contains("Dao.xml.vm" )) {
            return "main" + File.separator + "resources" + File.separator + "mapper"  + File.separator + className + "Dao.xml";
        }
        if (template.contains("ServiceImpl.java.vm" )) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }
        return packagePath + moduleType + File.separator + classFullName;
    }

}
