package com.fehead.initialize.service.impl;

import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.error.SmsValidateException;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.TelValidateCodeService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import com.fehead.initialize.utils.CreateCodeUtil;
import com.fehead.initialize.utils.SmsUtil;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private UserDOMapper userDOMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String telphone) {
        Map<String, String> paramMap = new HashMap<>();
        ValidateCode smsCode = CreateCodeUtil.createCode(telphone, 6);
        paramMap.put("code", smsCode.getCode());
        logger.info(smsCode.getCode());
        String modelName = securityProperties.getSmsProperties().getSmsModel().get(0).getName();

        logger.info(securityProperties.getSmsProperties().getSmsModel().get(0).getDes());

        smsCode.encode(passwordEncoder);
        redisService.set(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + smsCode.getTelphone(), smsCode, new Long(300));
        logger.info(smsCode.getCode());

        logger.info("此处模拟发送短信");
        //发送短信
//        smsUtil.sendSms(modelName, paramMap, telphone);
    }

    @Override
    public boolean loginValidate(String telphoneInRequest, String codeInRequest) {
        return false;
    }

    @Override
    public boolean check(String telphone) throws SmsValidateException {

        if(StringUtils.isEmpty(telphone)){
            throw new SmsValidateException(EmBusinessError.TEL_NOT_BE_NULL, "手机号不能为空");
        }

        boolean result = true;
        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            logger.info("手机号<"+telphone+">: "+"手机号不合法");
            throw new SmsValidateException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
        }

        if (userDOMapper.selectByTelphone(telphone) == null) {
            logger.info("用户不存在");
            result = false;
        }

        // 检查验证码在60秒内是否已经发送
        if (redisService.exists(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone)) {
            ValidateCode code = (ValidateCode) redisService.get(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone);
            if (!code.isExpired(60)) {
                result = false;
                logger.info("验证码已发送");
                throw new SmsValidateException(EmBusinessError.SMS_ALREADY_SEND);
            } else {
                redisService.remove(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + telphone);
            }
        }

        return result;
    }
}
