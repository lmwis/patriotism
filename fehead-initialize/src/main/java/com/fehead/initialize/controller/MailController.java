package com.fehead.initialize.controller;

import com.fehead.initialize.authentication.FeheadAuthenticationFailureHandler;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.AuthenticationReturnType;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import com.fehead.initialize.service.EMailService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
 * @author Nightnessss 2019/7/23 9:56
 */
@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private EMailService eMailService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FeheadAuthenticationFailureHandler feheadAuthenticationFailureHandler;

    @PostMapping("/send")
    public FeheadResponse sendAuthenticationEmail(String toAddress, HttpServletRequest request, HttpServletResponse response) throws MessagingException, BusinessException, IOException, ServletException {

        if(StringUtils.isEmpty(toAddress)){
//            feheadAuthenticationFailureHandler.onAuthenticationFailure(request,response,new SmsValidateException(EmBusinessError.EMAIL_TO_EMPTY));

            return CommonReturnType.create("fail","发送失败");
        }

        logger.info("调用发送邮件接口");

        //发送注册校验邮箱
        eMailService.sendValidateEmail(toAddress);

        return CommonReturnType.create("发送成功");
    }

    //我觉得此时安全性并不是很重要所以选择了速度快的get请求
    @PutMapping("/validate")
    public FeheadResponse validateEmailCode(String address,String code){

        if(StringUtils.isEmpty(address) || StringUtils.isEmpty(code)){
            return AuthenticationReturnType.create(EmBusinessError.EMAIL_TO_EMPTY.getErrorMsg(), HttpStatus.PAYMENT_REQUIRED.value());
        }
        eMailService.validateEmailCode(address,code);

        return CommonReturnType.create("校验成功");
    }

}


