package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lmwis on 2019-07-26 14:53
 */
@RestController
@RequestMapping("/sms")
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

    /**
     * 发送短信验证码
     * @param request
     * @param response
     * @throws IOException
     * @throws BusinessException
     */
    @PostMapping("/send")
    public CommonReturnType sendOtp(String tel,String action,HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
        String telphone = tel;

        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }

        if(StringUtils.equals(action,SmsAction.REGISTER.value())){    //  行为判断
            // 检查验证码在60秒内是否已经发送
            if (registerService.check(telphone)) {
                ValidateCode code = (ValidateCode) redisService.get(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphone);
                if (!code.isExpired(60)) {
                    logger.info("验证码已发送");
                    throw new BusinessException(EmBusinessError.SMS_ALREADY_SEND);
                } else {
                    //移除原验证码
                    redisService.remove(telphone);
                }
            }
            //重新发送
            registerService.send4Register(telphone);
        }


        return CommonReturnType.create(telphone);
    }

}
