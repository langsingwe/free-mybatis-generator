package com.weilc.config.handler;

import com.weilc.dao.BaseGeneratorDao;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName DbHandler
 * @Author weilc
 * @Date 2020-11-24
 * @Version 1.0
 */
public abstract class DbHandler {
    public abstract BaseGeneratorDao handle();
    public abstract String database();
}
