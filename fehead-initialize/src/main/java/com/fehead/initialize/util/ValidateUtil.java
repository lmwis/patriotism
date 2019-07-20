package com.fehead.initialize.util;

import com.fehead.initialize.service.model.ValidateCode;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author lmwis on 2019-07-20 19:10
 */
public class ValidateUtil {

    /**
     * 生成随机验证码
     * @return
     */
    public static ValidateCode createCode(int length) {

        Random random = new Random();

        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        return new ValidateCode(sRand, 60);
    }
}
