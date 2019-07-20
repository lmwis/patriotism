package com.fehead.initialize.controller;

import com.fehead.initialize.service.model.ValidateCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
 * @author Nightnessss 2019/7/17 17:23
 */
@RestController
public class ValidateCodeController extends BaseController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/sms")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ValidateCode otp = createCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, otp);
    }

    /**
     * 生成随机验证码
     * @param request
     * @return
     */
    private ValidateCode createCode(HttpServletRequest request) {

        Random random = new Random();

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }

        return new ValidateCode(sRand, 60);
    }
}
