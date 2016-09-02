package com.fx.controller.exception;

import com.fx.controller.base.BaseController;
import com.fx.enums.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Michael on 8/24/2016.
 */
@Controller
@RequestMapping("/exception")
public class ExceptionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @RequestMapping("/message/{code}")
    @ResponseBody
    public static String getExceptionMsgAndCode(@PathVariable String code) {
        String msg = ExceptionEnum.getMsg(code);
        LOGGER.info("请求出错，错误代码:{}， 错误信息:{}", code, msg);
        return msg;
    }

}
