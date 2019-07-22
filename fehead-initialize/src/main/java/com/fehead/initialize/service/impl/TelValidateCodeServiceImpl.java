package com.fehead.initialize.service.impl;

import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.TelValidateCodeService;
import com.fehead.initialize.service.model.SmsModel;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CreateCodeUtil;
import com.fehead.initialize.utils.SmsUtil;
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
 * @author Nightnessss 2019/7/22 19:05
 */
@Service
public class TelValidateCodeServiceImpl implements TelValidateCodeService {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String telphone) {
        Map<String, String> paramMap = new HashMap<>();
        ValidateCode smsCode = CreateCodeUtil.createCode(telphone, 4);
        paramMap.put("code", smsCode.getCode());
        String modelName = securityProperties.getSmsProperties().getSmsModel().get(1).getName();

        smsCode.encode(passwordEncoder);
        redisService.set(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + smsCode.getTelphone(), smsCode, new Long(300));
        logger.info(smsCode.getCode());
        smsUtil.sendSms(modelName, paramMap, telphone);
    }

    @Override
    public boolean loginValidate(String telphoneInRequest, String codeInRequest) {
        return false;
    }
}
