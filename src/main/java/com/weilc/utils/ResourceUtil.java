package com.weilc.utils;

import com.weilc.exception.GenException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName ResourceUtil
 * @Author weilc
 * @Date 2020-11-27
 * @Version 1.0
 */
public class ResourceUtil {

    /**
     * 获取配置信息
     * @return
     */
    public static Configuration getConfig(String filename) {
        try {
            return new PropertiesConfiguration(filename);
        } catch (ConfigurationException e) {
            throw new GenException("获取配置文件失败，",e);
        }
    }

    /**
     * 获取所有模板列表
     * @return
     */
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Dao.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        return templates;
    }
}
