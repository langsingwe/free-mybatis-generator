package com.weilc.config.handler.biz;

import com.weilc.config.handler.DbHandler;
import com.weilc.dao.BaseGeneratorDao;
import com.weilc.dao.OracleGeneratorDao;
import com.weilc.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @ClassName OracleHandler
 * @Author weilc
 * @Date 2020-11-24
 * @Version 1.0
 */
@Component
public class OracleHandler extends DbHandler {

    @Autowired
    private OracleGeneratorDao oracleGeneratorDao;

    @Override
    public BaseGeneratorDao handle() {
        return oracleGeneratorDao;
    }

    @Override
    public String database() {
        return Constant.ORACLE;
    }
}
