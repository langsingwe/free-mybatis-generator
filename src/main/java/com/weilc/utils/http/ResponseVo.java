package com.weilc.utils.http;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;

/**
 * @Description
 * @ClassName ResponseVo
 * @Author weilc
 * @Date 2020-11-17
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ResponseVo extends HashMap<String,Object> {
    private String code;
    private String msg;

    public static ResponseVo success() {
        return new ResponseVo().setCode(ResultCodeEnum.OK.getCode())
                .setMsg(ResultCodeEnum.OK.getMsg());
    }

    public static ResponseVo error() {
        return new ResponseVo().setCode(ResultCodeEnum.ERROR.getCode())
                .setMsg(ResultCodeEnum.ERROR.getMsg());
    }

    public static ResponseVo error(String msg) {
        return new ResponseVo().setCode(ResultCodeEnum.ERROR.getCode())
                .setMsg(msg);
    }

    @Override
    public ResponseVo put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
