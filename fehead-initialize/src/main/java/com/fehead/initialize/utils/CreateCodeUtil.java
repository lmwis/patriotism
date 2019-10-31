package com.fehead.initialize.utils;

import com.fehead.initialize.service.model.ValidateCode;
import org.springframework.security.crypto.password.PasswordEncoder;

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
 * @author Nightnessss 2019/7/22 18:29
 */
public class CreateCodeUtil {

    /**
     * 生成随机验证码
     *
     * @return
     */
    public static ValidateCode createCode(String telphone, Integer number) {

        return createCode(telphone, number, null);
    }

    public static ValidateCode createCode(String telphone, Integer number, PasswordEncoder passwordEncoder) {

        Random random = new Random();

        String sRand = "";
        for (int i = 0; i < number; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        if (passwordEncoder == null) {
            return new ValidateCode(telphone, sRand);
        }
        return new ValidateCode(telphone, passwordEncoder.encode(sRand));
    }
}
