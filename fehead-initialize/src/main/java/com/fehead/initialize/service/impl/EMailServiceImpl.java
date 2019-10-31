
package com.fehead.initialize.service.impl;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.EMailService;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CreateCodeUtil;
import com.fehead.initialize.utils.SendEmailUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmwis on 2019-07-25 10:44
 */
@Service
public class EMailServiceImpl implements EMailService {

    private static final Logger logger = LoggerFactory.getLogger(EMailServiceImpl.class);

    private static final String EMAIL_VALIDATE_PARAM_ADDRESS_KEY ="your_email";
    private static final String EMAIL_VALIDATE_PARAM_CODE_KEY ="code";

    private static final String EMAIL_VALIDATE_TITLE ="爱之国-爱国主义教育平台";
    private static final String EMAIL_VALIDATE_TEMPLATE_NAME ="email";

    private String addressParameter = EMAIL_VALIDATE_PARAM_ADDRESS_KEY;
    private String codeParameter = EMAIL_VALIDATE_PARAM_CODE_KEY;

    //校验邮件过期时间
//    private Integer emailExpiredTime = 21600;
    //邮件能重复发送最小间隔
//    private Integer emailTime = 60;

    @Autowired
    private SendEmailUtil sendEmailUtil;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private RedisService redisService;

    @Override
    public void sendValidateEmail(String toAddress) throws MessagingException, BusinessException {

        if(StringUtils.isEmpty(toAddress)){
            throw new BusinessException(EmBusinessError.EMAIL_TO_EMPTY);
        }

        ValidateCode emailCode =  CreateCodeUtil.createCode(toAddress,4);
        String key = securityProperties.getSendEmailProperties().getEmailValidatePreKeyInRedis().concat(toAddress);
        //先判断是否已经发送过了
        if(redisService.exists(key)){
            if(!((ValidateCode)redisService.get(key)).isExpired(securityProperties.getTimeProperties().getEmailResendTime())){//是否能重复发送
                logger.info("邮件已发送");
                throw new BusinessException(EmBusinessError.EMAIL_ALREADY_SEND);
            }else{
                //移除原校验
                redisService.remove(key);
            }
        }

        //参数封装
        Map<String,String> params = new HashMap<>();
        params.put(getAddressParameter(),emailCode.getTelphone());
        params.put(getCodeParameter(),emailCode.getCode());
        //发送
        sendEmailUtil.sendEmail(toAddress, params, EMAIL_VALIDATE_TITLE, EMAIL_VALIDATE_TEMPLATE_NAME);
        //未抛异常表示发送成功
        //写入redis
        //超时时间为6个小时
        redisService.set(key, emailCode, new Long(securityProperties.getTimeProperties().getEmailExpiredTime()));

    }

    @Override
    public boolean validateEmailCode(String yourEmail, String code) throws BusinessException {

        String codeInRedis = "";

        if (!redisService.exists(securityProperties.getSendEmailProperties().getEmailValidatePreKeyInRedis() + yourEmail)) {
            logger.info("验证码不存在");
            throw new BusinessException(EmBusinessError.SMS_ISNULL);
        } else {
            codeInRedis =((ValidateCode)redisService.get(securityProperties.getSendEmailProperties().getEmailValidatePreKeyInRedis() + yourEmail)).getCode();
        }
        if(StringUtils.equals(code,codeInRedis)){
            redisService.remove(securityProperties.getSendEmailProperties().getEmailValidatePreKeyInRedis() + yourEmail);
            return true;
        }else{
            return false;
        }
    }

    public String getAddressParameter() {
        return addressParameter;
    }

    public void setAddressParameter(String addressParameter) {
        this.addressParameter = addressParameter;
    }

    public String getCodeParameter() {
        return codeParameter;
    }

    public void setCodeParameter(String codeParameter) {
        this.codeParameter = codeParameter;
    }
}