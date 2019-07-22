package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessExpection;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/7/10 11:50
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    // 定义exceptionHandler来解决controller层中未被吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerExcepetion(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessExpection) {
            BusinessExpection businessExpection = (BusinessExpection)ex;
            responseData.put("errorCode", businessExpection.getErrorCode());
            responseData.put("errorMsg", businessExpection.getErrorMsg());
            System.out.println(responseData);
        } else {
            responseData.put("errorCode", EmBusinessError.UNKNOW_ERROR.getErrorCode());
            responseData.put("errorMsg", EmBusinessError.UNKNOW_ERROR.getErrorMsg());
            System.out.println(responseData);
        }

        return CommonReturnType.creat(responseData,"fail");
    }
}
