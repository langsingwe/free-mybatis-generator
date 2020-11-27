package com.weilc.config.handler;

import com.weilc.exception.DataBaseException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName DbProcessor
 * @Author weilc
 * @Date 2020-11-24
 * @Version 1.0
 */
@Component
public class DbProcessor implements ApplicationContextAware {
    private ApplicationContext context;

    public Map<String, DbHandler> chooseMap = new HashMap<>();

    public DbHandler choose(String database) {
        DbHandler handler = chooseMap.get(database);
        if (null == handler || "oracle".equalsIgnoreCase(database)) {
            throw new DataBaseException("不支持的数据库类型，请联系维护人员");
        }
        return handler;
    }

    @PostConstruct
    public void register() {
        Map<String, DbHandler> map = context.getBeansOfType(DbHandler.class);
        for (DbHandler solver : map.values()) {
            chooseMap.put(solver.database(), solver);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
