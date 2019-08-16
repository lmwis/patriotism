package com.fehead.initialize.service.impl;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.SmsService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CreateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
 * @author Nightnessss 2019/8/11 17:38
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RedisService redisService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean check(String key) throws BusinessException {

        boolean result = false;
        // 检查验证码在60秒内是否已经发送
        if (redisService.exists(key)) {
            result = true;
        }

        return result;
    }

    @Override
    public void send(String telphone, Integer modelId) throws BusinessException {
        Map<String, String> paramMap = new HashMap<>();
        ValidateCode smsCode = CreateCodeUtil.createCode(telphone, 6);
        paramMap.put("code", smsCode.getCode());
        String modelName = "";
        try {
            modelName = securityProperties.getSmsProperties().getSmsModel().get(modelId).getName();
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        logger.info("action：" + securityProperties.getSmsProperties().getSmsModel().get(modelId).getDes());
        logger.info("模板：" + modelName);
        logger.info("验证码：" + smsCode.getCode());
        smsCode.encode(passwordEncoder);
        logger.info("encode:" + smsCode.getCode());
        switch (modelId) {
            case 0:
//                String key = passwordEncoder.encode(telphone);
//                logger.info("sms_key: " + key);
//                redisService.set("sms_key_" + telphone, key, new Long(300));
                redisService.set(securityProperties.getSmsProperties().getRegisterPreKeyInRedis() + smsCode.getTelphone(), smsCode, new Long(30*60));
                break;
            case 1:
                redisService.set(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + smsCode.getTelphone(), smsCode, new Long(30*60));
                break;
            default:
                break;
        }
//        smsUtil.sendSms(modelName, paramMap, telphone);
    }
}
