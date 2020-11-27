package com.weilc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weilc.dao.BaseGeneratorDao;
import com.weilc.service.SysGeneratorService;
import com.weilc.utils.GenUtil;
import com.weilc.utils.PageUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Description
 * @ClassName SysGeneratorService
 * @Author weilc
 * @Date 2020-11-16
 * @Version 1.0
 */
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {

    @Autowired
    private BaseGeneratorDao baseGeneratorDao;

    @Override
    public PageUtil queryList(Map<String, Object> params) {
        int pageNum = Integer.valueOf(params.get("page").toString()) ;
        int pageSize = Integer.valueOf(params.get("limit").toString()) ;
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = baseGeneratorDao.queryList(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        PageUtil pageUtil = new PageUtil(list, pageNum, pageInfo.getPages(), (int)pageInfo.getTotal());
        return pageUtil;
    }

    @Override
    public byte[] generatorCode(String tables) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        String[] tableNames = tables.split(",");
        if (ArrayUtils.isNotEmpty(tableNames)) {
            for (String tableName : tableNames) {
                Map<String, String> table = baseGeneratorDao.queryTable(tableName);
                List<Map<String, String>> columns = baseGeneratorDao.queryColumns(tableName);
                GenUtil.generatorCode(table, columns, zipOutputStream);
            }
            IOUtils.closeQuietly(zipOutputStream);
        }
        return outputStream.toByteArray();
    }
}
