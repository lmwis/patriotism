package com.fehead.initialize.controller;

import com.fehead.initialize.controller.viewobject.UserVO;
import com.fehead.initialize.error.BusinessExpection;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * @author Nightnessss 2019/7/8 14:50
 */
@RestController("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest httpServletRequest;

    // 用户登录
    @PostMapping(value = "/login")
    public CommonReturnType login(HttpServletRequest)

    // 用户手机注册接口
    @RequestMapping(value = "/registerByTelphone", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "password") String password) throws BusinessExpection, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 验证手机号与对应的otp符合度
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!com.alibaba.druid.util.StringUtils.equals(inSessionOtpCode, otpCode)) {
            throw new BusinessExpection(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码错误");
        }

        // 用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        // 加密密码
        userModel.setEncrptPassword(passwordEncoder.encode(password));
        userService.register(userModel);

        return CommonReturnType.creat(null);
    }
    // 用户电子邮件注册接口
    @RequestMapping(value = "/registerByEmail", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType register(@RequestParam(name = "email") String email,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "password") String password) throws BusinessExpection, UnsupportedEncodingException, NoSuchAlgorithmException {

        // 用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setRegisterMode("byemail");
        // 加密密码
        userModel.setEncrptPassword(passwordEncoder.encode(password));
        userService.register(userModel);

        return CommonReturnType.creat(null);
    }

    // 加密密码
//    private String EncodeByMB5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        // 确定计算方法
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        BASE64Encoder base64en = new BASE64Encoder();
//        // 加密字符串
//        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
//        return newstr;
//    }

    // 用户获取短信验证码接口
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    public CommonReturnType getotp(String telphone) throws BusinessExpection {

        // 需要按照一定的规则生成otp验证码
        Random random = new Random();
        Integer randomInt = random.nextInt(89999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        // 将opt验证码与对应的手机号关联
        this.httpServletRequest.getSession().setAttribute(telphone, otpCode);
        System.out.println("telphone=" + telphone + "&otp=" + otpCode);


        return CommonReturnType.creat(randomInt);
    }


    @RequestMapping("/get")
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessExpection {
        // 调用service服务获取相应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        // 若获取用户不存在
        if (userModel == null) {
            throw new BusinessExpection(EmBusinessError.USER_NOT_EXIST);
        }

        // 将核心模型转化为供UI使用的viewobject
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.creat(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserVO userVO = new UserVO();

        BeanUtils.copyProperties(userModel, userVO);

        return userVO;
    }


}