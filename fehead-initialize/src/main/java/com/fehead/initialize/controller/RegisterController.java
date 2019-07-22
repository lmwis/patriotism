package com.fehead.initialize.controller;

import com.fehead.initialize.Utils.CheckEmailAndTelphoneUtil;
import com.fehead.initialize.Utils.SmsUtil;
import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dao.UserPasswordDOMapper;
import com.fehead.initialize.dataobject.UserPasswordDO;
import com.fehead.initialize.error.BusinessExpection;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
import com.fehead.initialize.service.model.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

//    public static final Long EXPIRETIME = new Long(300);

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

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 发送短信验证码
     * @param request
     * @param response
     * @throws IOException
     * @throws BusinessExpection
     */
    @PostMapping("/sendSms")
    public CommonReturnType sendOtp(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessExpection {
        String telphone = request.getParameter("telphone");
        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            throw new BusinessExpection(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }

        // 检查验证码在60秒内是否已经发送
        if (redisService.exists(telphone)) {
            ValidateCode code = (ValidateCode) redisService.get(telphone);
            if (!code.isExpried()) {
                logger.info("验证码已发送");
//            sessionStrategy.removeAttribute(servletWebRequest, RegisterController.SESSION_KEY);
                throw new BusinessExpection(EmBusinessError.SMS_ALREADY_SEND);
            } else {
                redisService.remove(telphone);
            }
        }
        Map<String, String> paramMap = new HashMap<>();
        ValidateCode smsCode = createCode(telphone);
        paramMap.put("code", smsCode.getCode());
        String modelName = securityProperties.getSmsProperties().getSmsModel().get(1).getName();

        redisService.set(smsCode.getTelphone(), smsCode, new Long(300));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        logger.info(smsCode.getCode());
        smsUtil.sendSms(modelName, paramMap, telphone);

        return CommonReturnType.creat(telphone);
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     * @throws ServletRequestBindingException
     * @throws BusinessExpection
     */
    @PostMapping("/register")
    public CommonReturnType register(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, BusinessExpection {
        String telphone = request.getParameter("telphone");
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            throw new BusinessExpection(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }
        String password = request.getParameter("password");
        if (password.isEmpty()) {
            throw new BusinessExpection(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码为空");
        }
        if (validate(new ServletWebRequest(request))) {
            UserModel userModel = new UserModel();
            userModel.setTelphone(telphone);
            userModel.setRegisterMode("byTelphone");
            userModel.setEncrptPassword(passwordEncoder.encode(password));
            userService.register(userModel);
        }

        return CommonReturnType.creat(null);
    }

    /**
     * 生成随机验证码
     *
     * @return
     */
    private ValidateCode createCode(String telphone) {

        Random random = new Random();

        String sRand = "";
        for (int i = 0; i < securityProperties.getSmsProperties().getSmsNumber(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        return new ValidateCode(telphone, sRand, 60);
    }

    private boolean validate(ServletWebRequest servletWebRequest) throws BusinessExpection, ServletRequestBindingException {

        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");

        String telphoneInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "telphone");
        ValidateCode smsCode = new ValidateCode();

        // 检查redis中是否存有该手机号验证码
        if (!redisService.exists(telphoneInRequest)) {
            logger.info("验证码不存在");
            throw new BusinessExpection(EmBusinessError.SMS_ISNULL);
        }
        smsCode = (ValidateCode)redisService.get(telphoneInRequest);
        logger.info("smsCode:" + smsCode.getCode());
//        ValidateCode smsCode = (ValidateCode) sessionStrategy.getAttribute(servletWebRequest,
//                RegisterController.SESSION_KEY);

        if (StringUtils.isBlank(codeInRequest)) {
            logger.info("验证码不能为空");
            throw new BusinessExpection(EmBusinessError.SMS_ISBLANK);
        }

        if (smsCode == null) {
            logger.info("验证码不存在");
            throw new BusinessExpection(EmBusinessError.SMS_ISNULL);
        }


        if (!StringUtils.equals(smsCode.getCode(), codeInRequest)) {
            logger.info("验证码不匹配");
            throw new BusinessExpection(EmBusinessError.SMS_ISILLEGAL);
        }

        redisService.remove(telphoneInRequest);


        return true;
    }
}
