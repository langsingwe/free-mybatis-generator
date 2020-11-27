package com.weilc.config.handler.biz;

import com.weilc.config.handler.DbHandler;
import com.weilc.dao.BaseGeneratorDao;
import com.weilc.dao.MySQLGeneratorDao;
import com.weilc.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName MysqlHandler
 * @Author weilc
 * @Date 2020-11-24
 * @Version 1.0
 */
@Component
public class MysqlHandler extends DbHandler {
    @Autowired
    private MySQLGeneratorDao mySQLGeneratorDao;

    @Override
    public BaseGeneratorDao handle() {
        return mySQLGeneratorDao;
    }

    @Override
    public String database() {
        return Constant.MYSQL;
    }
}
