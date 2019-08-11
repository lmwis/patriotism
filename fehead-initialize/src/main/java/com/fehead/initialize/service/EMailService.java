package com.fehead.initialize.service;

import com.fehead.initialize.error.BusinessException;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @author lmwis on 2019-07-25 10:42
 */
public interface EMailService {

    public void sendValidateEmail(String toAddress) throws MessagingException, BusinessException;

    boolean validateEmailCode(String yourEmail, String code);
}
