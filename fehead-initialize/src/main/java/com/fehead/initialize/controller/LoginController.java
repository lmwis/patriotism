package com.fehead.initialize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserMeModel;
import com.fehead.initialize.service.model.UserModel;
import com.fehead.initialize.service.model.YbReturnModel;
import com.fehead.initialize.utils.PostUtil;
import com.fehead.initialize.utils.UnicodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
 * @author Nightnessss 2019/10/14 20:12
 */
@RestController
@RequestMapping("/api/v1.0/sys/login")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

//    private static final String backurl = "http://192.168.43.7:8081/blank";
//    private static final String backurl = "http://192.168.0.110:8081/blank";
//    private static final String backurl = "http://10.111.118.205:8200/blank";
    private static final String backurl = "http://patriotiosm.duizhankeji.com:8180/blank";


    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PostUtil postUtil;
    @Autowired
    private UnicodeUtil unicodeUtil;
    @Autowired
    private UserService userService;

    /**
     * 获取 access_token 并跳转至信息补充界面
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/oauth")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public FeheadResponse oauth(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.info("开始获取access_token...");
//        String getTokenUrl = "http://nightnessss.cn:8018/page/oauth?callback=" + backurl;
        String getTokenUrl = "http://yiban.sust.edu.cn/yibanapi/?backurl=" + backurl;
//        response.sendRedirect(getTokenUrl);
        Map<String,String> data = new HashMap<>();
        data.put("url",getTokenUrl);
        return CommonReturnType.create(data);
    }

    /**
     * 通过前端上传的 access_token 获取用户信息，返回用户信息
     * @param request
     * @param response
     * @return UserModel
     * @throws IOException
     * @throws BusinessException
     */
    @GetMapping("/login")
    public FeheadResponse login(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {

        HttpSession session = request.getSession();
        String accessToken = request.getParameter("access_token");
        logger.info("access_token:" + accessToken);

        if (accessToken == null || accessToken.isEmpty()) {
            logger.info("access_token为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "access_token为空");
        }

        // 获取用户信息
        String getUserInfo = "https://openapi.yiban.cn/user/me?" +
                "access_token=" + accessToken;

        session.setAttribute("access_token", accessToken);
        String userInfo = postUtil.sendGet(getUserInfo);

        // 将请求易班返回的信息存入易班用户封装类
        YbReturnModel ybReturnModel = new YbReturnModel();
        try {
            ybReturnModel = objectMapper.readValue(userInfo, YbReturnModel.class);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.DATARESOURCE_CONNECT_FAILURE);
        }

        // 提取用户信息
        UserMeModel userMeModel = ybReturnModel.getInfo();
        String thirdPartyId = userMeModel.getYb_userid();

        // 从数据库通过第三方ID查找用户信息是否存在，如果存在则返回该用户，否则创建一个新的用户存入数据库
        UserModel userModel = userService.getUserByThirdPartyid(thirdPartyId);
        if (userModel != null) {
//            session.setAttribute("userId", userModel.getId());
            return CommonReturnType.create(userModel);
        }
        String displayName = unicodeUtil.unicode2String(userMeModel.getYb_usernick());
        String avatar = unicodeUtil.unicode2String(userMeModel.getYb_userhead());
        userModel = new UserModel("易班", thirdPartyId, avatar, displayName);
        int id = userService.addUser(userModel);
        userModel.setId(id);

//        session.setAttribute("userModel", userModel);
//        session.setAttribute("access_token", accessToken);


//        System.out.println(session.getId());
        return CommonReturnType.create(userModel);
    }

//    @PostMapping("/addInfo")
//    public CommonReturnType addInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String telphone = request.getParameter("telphone");
//        String email = request.getParameter("email");
//        logger.info("PARAM: telphone " + telphone);
//        logger.info("PARAM: email " + email);
//        HttpSession session = request.getSession();
////        String accessToken = (String) session.getAttribute("access_token");
////        System.out.println(accessToken);
////        String getUserInfo = "https://openapi.yiban.cn/user/me?" +
////                "access_token=" + accessToken;
////
////        String userInfo = postUtil.sendGet(getUserInfo);
////        YbReturnModel ybReturnModel = objectMapper.readValue(userInfo, YbReturnModel.class);
////        UserMeModel userMeModel = ybReturnModel.getInfo();
////        String thirdPartyId = userMeModel.getYb_userid();
////        String displayName = unicodeUtil.unicode2String(userMeModel.getYb_usernick());
////        String avatar = unicodeUtil.unicode2String(userMeModel.getYb_userhead());
////        UserModel userModel = new UserModel(telphone, email, "易班", thirdPartyId, avatar, displayName);
//        System.out.println(session.getId());
//        UserModel userModel = (UserModel) session.getAttribute("userModel");
//        session.removeAttribute("userModel");
//        userModel.setTelphone(telphone);
//        userModel.setEmail(email);
//
//        logger.info(userModel.toString());
//        int userId = cloudService.addUser(userModel);
//        logger.info("userId:" + userId);
//
//        return CommonReturnType.creat("success");
//    }


    /**
     * 补充信息
     * @param request
     * @param response
     * @return UserModel
     * @throws BusinessException
     */
    @PostMapping("/addInfo")
    public FeheadResponse addInfo(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        String thirdPartyId = request.getParameter("username");
        String telphone = request.getParameter("telphone");
        String email = request.getParameter("email");
        UserModel userModel = userService.getUserByThirdPartyid(thirdPartyId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.SERVICE_REQUIRE_AUTHENTICATION);
        }
        if (userModel.getTelphone() == null && userModel.getEmail() == null) {
            userModel.setTelphone(telphone);
            userModel.setEmail(email);
            logger.info(userModel.toString());
            int id = userService.addInfo(userModel);
            userModel.setId(id);
        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "信息已补充");
        }
        return CommonReturnType.create(userModel);
    }
}
