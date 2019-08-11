package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import com.fehead.initialize.utils.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @author Nightnessss 2019/7/17 17:23
 */
@RestController
@RequestMapping("/register")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RegisterController extends BaseController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RegisterService registerService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 发送短信验证码
     * @param request
     * @param response
     * @throws IOException
     * @throws BusinessException
     */
//    @PostMapping("/sendSms")
//    public CommonReturnType sendOtp(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
//        String telphone = request.getParameter("telphone");
//
//        // 检查手机号是否合法
//        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
//        }
//
//        // 检查验证码在60秒内是否已经发送
//        if (registerService.check(telphone)) {
//            ValidateCode code = (ValidateCode) redisService.get(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphone);
//            if (!code.isExpired(60)) {
//                logger.info("验证码已发送");
//                throw new BusinessException(EmBusinessError.SMS_ALREADY_SEND);
//            } else {
//                //移除原验证码
//                redisService.remove(telphone);
//            }
//        }
//        //重新发送
//        registerService.send(telphone);
//
//
//        return CommonReturnType.create(telphone);
//    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     * @throws ServletRequestBindingException
     * @throws BusinessException
     */
    @PostMapping("/register")
    public CommonReturnType register(String telphone,String smsCode,String password,
                                     HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, BusinessException {
        String telphoneInRequest = telphone;
        String codeInRequest = smsCode;
//        String password = request.getParameter("password");
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphoneInRequest)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }
        if (password.isEmpty()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码为空");
        }
        if (registerService.registerValidate(telphoneInRequest, codeInRequest)) {
            registerService.registerByTelphone(telphoneInRequest, password);
        }

        return CommonReturnType.create(null);
    }

}
