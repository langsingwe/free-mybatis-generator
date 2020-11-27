package com.weilc.controller;

import com.weilc.service.SysGeneratorService;
import com.weilc.utils.PageUtil;
import com.weilc.utils.http.ResponseVo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description
 * @ClassName SysGeneratorController
 * @Author weilc
 * @Date 2020-11-16
 * @Version 1.0
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    @ResponseBody
    @RequestMapping("/list")
    public ResponseVo list(@RequestParam Map<String, Object> params) {
        PageUtil pageUtil = sysGeneratorService.queryList(params);
        return ResponseVo.success().put("page", pageUtil);
    }

    @RequestMapping("/code")
    public void code(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data,response.getOutputStream());
    }
}
