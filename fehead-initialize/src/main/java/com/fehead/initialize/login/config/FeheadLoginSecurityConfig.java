package com.fehead.initialize.login.config;

import com.fehead.initialize.login.TelUserDetailService;
import com.fehead.initialize.login.TelValidateCodeAuthenticationFilter;
import com.fehead.initialize.login.TelValidateCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmwis on 2019-07-22 10:26
 */
@Component("feheadLoginSecurityConfig")
public class FeheadLoginSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    private TelValidateCodeAuthenticationFilter telValidateCodeAuthenticationFilter = new TelValidateCodeAuthenticationFilter();

    @Autowired
    private AuthenticationSuccessHandler feheadAuthenticationSuccessHandler;

    private TelValidateCodeAuthenticationProvider telValidateCodeAuthenticationProvider = new TelValidateCodeAuthenticationProvider();

    @Autowired
    private AuthenticationFailureHandler feheadAuthenticationFailureHandler;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);

        List<AuthenticationProvider> lists = new ArrayList<>();
        lists.add(telValidateCodeAuthenticationProvider);
        ProviderManager providerManager = new ProviderManager(lists);

        telValidateCodeAuthenticationFilter.setSuccessHandler(feheadAuthenticationSuccessHandler);
        telValidateCodeAuthenticationFilter.setFailureHandler(feheadAuthenticationFailureHandler);
        telValidateCodeAuthenticationFilter.setAuthenticationManager(providerManager);


        telValidateCodeAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        telValidateCodeAuthenticationProvider.setTelUserDetailService(getApplicationContext().getBean(TelUserDetailService.class));

        builder.addFilterBefore(telValidateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}
