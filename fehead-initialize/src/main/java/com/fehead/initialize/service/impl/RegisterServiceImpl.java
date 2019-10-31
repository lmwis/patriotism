
package com.fehead.initialize.service.impl;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.SmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
 * @author Nightnessss 2019/7/22 12:46
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RedisService redisService;

    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void registerByTelphone(String telphoneInRequest, String password, String displayName) throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setTelphone(telphoneInRequest);
        userModel.setRegisterMode("byTelphone");
        userModel.setEncrptPassword(passwordEncoder.encode(password));
        userModel.setDisplayName(displayName);
        userService.register(userModel);
    }

    @Override
    public void registerByEmail(String email, String password,String display) throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setDisplayName(display);
        userModel.setEmail(email);
        userModel.setRegisterMode("byEmail");
        userModel.setEncrptPassword(passwordEncoder.encode(password));
        userService.register(userModel);
    }


    public boolean registerValidate(String telphoneInRequest, String codeInRequest) throws BusinessException {


        ValidateCode smsCode = new ValidateCode();

        // 检查redis中是否存有该手机号验证码
        if (!redisService.exists(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphoneInRequest)) {
            logger.info("验证码不存在");
            throw new BusinessException(EmBusinessError.SMS_ISNULL);
        }
        smsCode = (ValidateCode)redisService.get(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphoneInRequest);

        if (StringUtils.isBlank(codeInRequest)) {
            logger.info("验证码不能为空");
            throw new BusinessException(EmBusinessError.SMS_BLANK);
        }

        if (smsCode == null) {
            logger.info("验证码不存在");
            throw new BusinessException(EmBusinessError.SMS_ISNULL);
        }


        if (!passwordEncoder.matches(codeInRequest, smsCode.getCode())) {
            logger.info("验证码不匹配");
            throw new BusinessException(EmBusinessError.SMS_ILLEGAL);
        }

        redisService.remove(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + telphoneInRequest);


        return true;
    }
}