package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.SmsService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmwis on 2019-07-26 14:53
 */
@RestController
@RequestMapping("/api/v1.0/sys/sms")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class SmsController extends BaseController{

    enum SmsAction{
        REGISTER("register"),
        LOGIN("login"),
        ;
        private String actionStr;
        SmsAction(String actionStr) {
            this.actionStr = actionStr;
        }
        public String value() {
            return actionStr;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private SmsService smsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 提供手机号和当前行为，根据行为发送相应短信
     * @param request
     * @param response
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "/send")
    public FeheadResponse sendSms(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        String telphone = request.getParameter("tel");
        logger.info("手机号：" + telphone);
        String action = request.getParameter("action");
        if (action.isEmpty()) {
            logger.info("action为空");
            throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL, "action异常");
        }
        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            logger.info("手机号不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }


        // 检查验证码在60秒内是否已经发送
        if (action.equals(SmsAction.REGISTER.actionStr)) {
            if (smsService.check(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphone)) {
                ValidateCode code = (ValidateCode) redisService.get(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphone);
                if (!code.isExpired(securityProperties.getTimeProperties().getSmsResendTime())) {
                    logger.info("验证码已发送");
                    throw new BusinessException(EmBusinessError.SMS_ALREADY_SEND);
                } else {
                    redisService.remove(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphone);
                }
            }
        } else if (action.equals(SmsAction.LOGIN.actionStr)) {
            if (smsService.check(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone)) {
                ValidateCode code = (ValidateCode) redisService.get(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone);
                if (!code.isExpired(securityProperties.getTimeProperties().getSmsResendTime())) {
                    logger.info("验证码已发送");
                    throw new BusinessException(EmBusinessError.SMS_ALREADY_SEND);
                } else {
                    redisService.remove(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone);
                }
            }
        } else {
            logger.info("action异常");
            throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL, "action异常");
        }

        // 根据行为选择模板发送短信  0为注册模板，1为登录模板
        if (action.equals(SmsAction.LOGIN.actionStr)) {
            smsService.send(telphone, 1);
        } else if (action.equals(SmsAction.REGISTER.actionStr)) {
            smsService.send(telphone, 0);
        } else {
            logger.info("action异常");
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR, "action异常");
        }

        return CommonReturnType.create(telphone);
    }

    /**
     * 对手机号和验证码进行校验
     * @param request
     * @param response
     * @return
     * @throws BusinessException
     */
    @PutMapping(value = "/validate")
    public FeheadResponse validateSms(HttpServletRequest request, HttpServletResponse response) throws BusinessException {

        String telphoneInRequest = request.getParameter("tel");
        String codeInRequest = request.getParameter("code");
        String smsKey = "";
        logger.info("手机号：" + telphoneInRequest);
        logger.info("验证码：" + codeInRequest);
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphoneInRequest)) {
            logger.info("手机号不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }
        if (codeInRequest.isEmpty()) {
            logger.info("验证码为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码为空");
        }
        if (registerService.registerValidate(telphoneInRequest, codeInRequest)) {
            smsKey = passwordEncoder.encode(telphoneInRequest);
            logger.info("密钥：" + smsKey);
            redisService.set("sms_key_"+ telphoneInRequest, smsKey, new Long(securityProperties.getTimeProperties().getSmsKeyExpiredTime()));
        }

        return CommonReturnType.create(smsKey);
    }


}
