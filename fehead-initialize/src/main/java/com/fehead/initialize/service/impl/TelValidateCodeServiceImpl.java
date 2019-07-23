package com.fehead.initialize.service.impl;

import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dataobject.UserPasswordDO;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.TelValidateCodeService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
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
//        smsUtil.sendSms(modelName, paramMap, telphone);
    }

    @Override
    public boolean loginValidate(String telphoneInRequest, String codeInRequest) {
        return false;
    }

    @Override
    public boolean check(String telphone) throws BusinessException {

        boolean result = true;
        // 检查手机号是否合法
        if (!CheckEmailAndTelphoneUtil.checkTelphone(telphone)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号不合法");
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
                throw new BusinessException(EmBusinessError.SMS_ALREADY_SEND);
            } else {
                redisService.remove(telphone);
            }
        }

        return result;
    }
}
