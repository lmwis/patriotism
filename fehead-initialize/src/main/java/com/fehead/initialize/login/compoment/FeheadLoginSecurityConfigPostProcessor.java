package com.fehead.initialize.login.compoment;

import com.fehead.initialize.login.authentication.TelUserDetailService;
import com.fehead.initialize.login.authentication.TelValidateCodeAuthenticationFilter;
import com.fehead.initialize.login.authentication.TelValidateCodeAuthenticationProvider;
import com.fehead.initialize.login.config.FeheadLoginSecurityConfig;
import com.fehead.initialize.properties.SecurityProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * chu shi hua
 * @author lmwis on 2019-07-23 16:11
 */

@Component
public class FeheadLoginSecurityConfigPostProcessor implements BeanPostProcessor {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private AuthenticationSuccessHandler feheadAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler feheadAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof FeheadLoginSecurityConfig){

            FeheadLoginSecurityConfig config = (FeheadLoginSecurityConfig)bean;

            //初始化provider
            TelValidateCodeAuthenticationProvider telValidateCodeAuthenticationProvider = new TelValidateCodeAuthenticationProvider();

            List<AuthenticationProvider> lists = new ArrayList<>();
            lists.add(telValidateCodeAuthenticationProvider);
            ProviderManager providerManager = new ProviderManager(lists);

            telValidateCodeAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            telValidateCodeAuthenticationProvider.setTelUserDetailService(applicationContext.getBean(TelUserDetailService.class));

            //初始化filter
            TelValidateCodeAuthenticationFilter telValidateCodeAuthenticationFilter = new TelValidateCodeAuthenticationFilter(securityProperties.getBrowser().getOtpLoginUrl());

            telValidateCodeAuthenticationFilter.setSuccessHandler(feheadAuthenticationSuccessHandler);
            telValidateCodeAuthenticationFilter.setFailureHandler(feheadAuthenticationFailureHandler);

            telValidateCodeAuthenticationFilter.setAuthenticationManager(providerManager);

            config.setTelValidateCodeAuthenticationFilter(telValidateCodeAuthenticationFilter);


        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
