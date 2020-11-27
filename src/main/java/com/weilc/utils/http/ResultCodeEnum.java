package com.weilc.utils.http;

import lombok.Getter;

/**
 * @Description
 * @ClassName ResultCodeEnum
 * @Author weilc
 * @Date 2020-11-17
 * @Version 1.0
 */
@Getter
public enum ResultCodeEnum {

    OK("200", "成功"),
    ERROR("9999","服务器异常，请联系管理员"),
    CAMELCASE_ERROR("9995", "驼峰命名配置单词拼写错误，请确认");


    private String code;
    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
