package com.fehead.initialize.component;

import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dao.UserPasswordDOMapper;
import com.fehead.initialize.login.authentication.TelUserDetailService;
import com.fehead.initialize.properties.SecurityProperties;
import com.fehead.initialize.service.RedisService;
import com.fehead.initialize.service.model.ValidateCode;
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
 * @author Nightnessss 2019/7/16 16:19
 */
@Component
public class MyUserDetailsService implements UserDetailsService, TelUserDetailService {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SecurityProperties securityProperties;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自定义登陆校验逻辑
     *      tel+password
     *      email+password
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);
        String password = "";

        if (CheckEmailAndTelphoneUtil.checkEmail(username)) {
            password = userPasswordDOMapper.selectByUserId(userDOMapper.selectByEmail(username).getId()).getEncrptPassword();
        } else if (CheckEmailAndTelphoneUtil.checkTelphone(username)) {
            password = userPasswordDOMapper.selectByUserId(userDOMapper.selectByTelphone(username).getId()).getEncrptPassword();
        } else {
            throw new UsernameNotFoundException("手机号或邮箱格式错误");
        }
        if (StringUtils.isEmpty(password)) {
            throw new UsernameNotFoundException("用户不存在");
        }


//        String password = userPasswordDOMapper.selectByUserId(userDOMapper.selectByTelphone(username).getId()).getEncrptPassword();


        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));
    }

    /**
     * tel+code
     * @param tel
     * @return
     */
    @Override
    public UserDetails loadUserByUserTel(String tel) {

        logger.info("登录手机号：" + tel);

        String password = "";

        if (userDOMapper.selectByTelphone(tel) == null) {
            throw new UsernameNotFoundException("该手机号未注册");
        }
        if (!CheckEmailAndTelphoneUtil.checkTelphone(tel)) {
            throw new UsernameNotFoundException("手机号格式错误");
        }

        if (!redisService.exists(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + tel)) {
            throw new UsernameNotFoundException("验证码不存在");
        } else {
            password =((ValidateCode)redisService.get(securityProperties.getSmsProperties().getLoginPreKeyInRedis() + tel)).getCode();
        }


        return new User(tel, password,
                true, true, true, true,
                AuthorityUtils.createAuthorityList("admin"));

    }
}
