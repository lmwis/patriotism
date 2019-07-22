package com.fehead.initialize.service.impl;

import com.fehead.initialize.Utils.CheckEmailAndTelphoneUtil;
import com.fehead.initialize.Utils.SmsUtil;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.RegisterService;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
import com.fehead.initialize.service.model.ValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    public boolean check(String telphone) throws BusinessException {

        boolean result = false;
        // 检查验证码在60秒内是否已经发送
        if (redisService.exists(telphone)) {
            result = true;
        }

        return result;
    }

    @Override
    public void send(String telphone) {
        Map<String, String> paramMap = new HashMap<>();
        ValidateCode smsCode = createCode(telphone);
        paramMap.put("code", smsCode.getCode());
        String modelName = securityProperties.getSmsProperties().getSmsModel().get(1).getName();

        redisService.set(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + smsCode.getTelphone(), smsCode, new Long(300));
        logger.info(smsCode.getCode());
        smsUtil.sendSms(modelName, paramMap, telphone);
    }

    @Override
    public void registerByTelphone(String telphoneInRequest, String password) throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setTelphone(telphoneInRequest);
        userModel.setRegisterMode("byTelphone");
        userModel.setEncrptPassword(passwordEncoder.encode(password));
        userService.register(userModel);
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

        return new ValidateCode(telphone, sRand);
    }

    public boolean validate(String telphoneInRequest, String codeInRequest) throws BusinessException {


        ValidateCode smsCode = new ValidateCode();

        // 检查redis中是否存有该手机号验证码
        if (!redisService.exists(telphoneInRequest)) {
            logger.info("验证码不存在");
            throw new BusinessException(EmBusinessError.SMS_ISNULL);
        }
        smsCode = (ValidateCode)redisService.get(telphoneInRequest);
        logger.info("smsCode:" + smsCode.getCode());

        if (StringUtils.isBlank(codeInRequest)) {
            logger.info("验证码不能为空");
            throw new BusinessException(EmBusinessError.SMS_BLANK);
        }

        if (smsCode == null) {
            logger.info("验证码不存在");
            throw new BusinessException(EmBusinessError.SMS_ISNULL);
        }


        if (!StringUtils.equals(smsCode.getCode(), codeInRequest)) {
            logger.info("验证码不匹配");
            throw new BusinessException(EmBusinessError.SMS_ILLEGAL);
        }

        redisService.remove(telphoneInRequest);


        return true;
    }
}
