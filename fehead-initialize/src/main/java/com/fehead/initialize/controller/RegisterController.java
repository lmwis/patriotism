package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.SmsService;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/api/v1.0/user/register")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RegisterController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private SmsService smsService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     * @throws ServletRequestBindingException
     * @throws BusinessException
     */
    @PostMapping("/tel")
    public FeheadResponse register(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, BusinessException {
        String telphoneInRequest = request.getParameter("tel");
        logger.info("PARAM tel:" + telphoneInRequest);
        String smsKey = request.getParameter("sms_key");
        logger.info("PARAM sms_key:" + smsKey);
        String password = request.getParameter("password");
        logger.info("PARAM password:" + password);
        String displayName = request.getParameter("display_name");
        logger.info("PARAM display_name:" + displayName);
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphoneInRequest)) {
            logger.info("手机号不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }
        if (password.isEmpty()) {
            logger.info("密码为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码为空");
        }
        if (displayName.isEmpty()) {
            logger.info("昵称为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "昵称为空");
        }
        if (redisService.exists("sms_key_" + telphoneInRequest)) {
            if (redisService.get("sms_key_" + telphoneInRequest).equals(smsKey)) {
                redisService.remove("sms_key_" + telphoneInRequest);
                registerService.registerByTelphone(telphoneInRequest, password, displayName);
            } else {
                logger.info("操作不合法");
                throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL);
            }
        } else {
            logger.info("操作不合法");
            throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL);
        }

        return CommonReturnType.create("注册成功");
    }

    @PostMapping("/email")
    public FeheadResponse registerByEmail(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String smsKey = request.getParameter("sms_key");

        if (!CheckEmailAndTelphoneUtil.checkEmail(email)) {
            logger.info("邮箱不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "邮箱不合法");
        }
        if (password.isEmpty()) {
            logger.info("密码为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码为空");
        }
        if (redisService.exists("sms_key_" + email)) {
            if (redisService.get("sms_key_" + email).equals(smsKey)) {
                redisService.remove("sms_key_" + email);
                registerService.registerByEmail(email, password);
            } else {
                logger.info("操作不合法");
                throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL);
            }
        } else {
            logger.info("操作不合法");
            throw new BusinessException(EmBusinessError.OPERATION_ILLEGAL);
        }

        logger.info("用户： " + email);

        return CommonReturnType.create("注册成功！");
    }

}
