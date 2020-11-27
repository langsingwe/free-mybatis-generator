package com.weilc.config.handler;

import com.weilc.utils.http.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @ClassName GlobalException
 * @Author weilc
 * @Date 2020-11-25
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理Exception异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseVo handlerException(Exception e) {
        return ResponseVo.error(e.getMessage());
    }

}
