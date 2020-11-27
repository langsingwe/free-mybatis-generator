package com.weilc.exception;

import lombok.Data;

/**
 * @Description
 * @ClassName DataBaseException
 * @Author weilc
 * @Date 2020-11-19
 * @Version 1.0
 */
@Data
public class DataBaseException extends RuntimeException {

    private int code = 999;
    private String msg;

    public DataBaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DataBaseException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public DataBaseException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public DataBaseException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
