package com.fehead.initialize.controller;

import com.fehead.initialize.authentication.FeheadAuthenticationFailureHandler;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.AuthenticationReturnType;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import com.fehead.initialize.service.EMailService;
import com.fehead.initialize.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/api/v1.0/sys/email")
public class MailController extends BaseController {

    @Autowired
    private EMailService eMailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    FeheadAuthenticationFailureHandler feheadAuthenticationFailureHandler;

    @PostMapping("/send")
    public FeheadResponse sendAuthenticationEmail(HttpServletRequest request, HttpServletResponse response) throws MessagingException, BusinessException, IOException, ServletException {

        String toAddress = request.getParameter("address");
        String action = request.getParameter("action");

        if(StringUtils.isEmpty(toAddress)){
//            feheadAuthenticationFailureHandler.onAuthenticationFailure(request,response,new SmsValidateException(EmBusinessError.EMAIL_TO_EMPTY));
           logger.info("接收地址为空");
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "接收地址为空");
        }

        // 检查行为是否为空
        if (action.isEmpty()) {
            logger.info("action为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "action为空");
        }

        logger.info("调用发送邮件接口");


        // 目前登录注册的验证邮件格式相同，暂不做区分
        if (action.equals("login")) {
            eMailService.sendValidateEmail(toAddress);
        } else if (action.equals("register")) {
            //发送注册校验邮箱
            eMailService.sendValidateEmail(toAddress);
        } else {
            logger.info("action参数不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "action参数不合法");
        }

        return CommonReturnType.create("发送成功");
    }

    //我觉得此时安全性并不是很重要所以选择了速度快的get请求
    @PutMapping("/validate")
    public FeheadResponse validateEmailCode(String address,String code) throws BusinessException {

        String smsKey = "";
        if(StringUtils.isEmpty(address) || StringUtils.isEmpty(code)){
            return AuthenticationReturnType.create(EmBusinessError.EMAIL_TO_EMPTY.getErrorMsg(), HttpStatus.PAYMENT_REQUIRED.value());
        }

        if (eMailService.validateEmailCode(address,code)) {
            smsKey = passwordEncoder.encode(address);
            logger.info("密钥：" + smsKey);
            redisService.set("sms_key_"+ address, smsKey, new Long(300));
        } else {
            logger.info("验证码不匹配");
            throw new BusinessException(EmBusinessError.SMS_ILLEGAL);
        }
        return CommonReturnType.create(smsKey);
    }

}


