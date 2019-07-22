package com.fehead.initialize.suport;

import com.fehead.initialize.filtter.ValidateCodeFilter;
import com.fehead.initialize.login.TelUserDetailService;
import com.fehead.initialize.login.TelValidateCodeAuthenticationFilter;
import com.fehead.initialize.login.TelValidateCodeAuthenticationProvider;
import com.fehead.initialize.login.TelValidateCodeFilter;
import com.fehead.initialize.login.config.FeheadWebSecurityConfig;
import com.fehead.initialize.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.*;

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
 * @author Nightnessss 2019/7/14 17:16
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler feheadAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler feheadAuthenticationFailureHandler;

//    @Autowired
//    private AuthenticationProvider telValidateCodeAuthenticationProvider;
//
//    @Autowired
//    private TelValidateCodeAuthenticationFilter telValidateCodeAuthenticationFilter;

//    @Autowired
    AuthenticationManagerFactoryBean authenticationManagerFactoryBean;

//    @Autowired
//    TelValidateCodePostProcessor postProcessor;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    AuthenticationDetailsSource authenticationDetailsSource;

//
//    @Autowired
//    AuthenticationManager authenticationManager;

    @Autowired
    FeheadWebSecurityConfig feheadWebSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        TelValidateCodeAuthenticationProvider provider = new TelValidateCodeAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setTelUserDetailService(getApplicationContext().getBean(TelUserDetailService.class));

//        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(postProcessor);
//        authenticationManagerBuilder.authenticationProvider()

        List<AuthenticationProvider> lists = new ArrayList<>();
        lists.add(provider);

        ProviderManager providerManager = new ProviderManager(lists);


        TelValidateCodeFilter filter = new TelValidateCodeFilter();

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(feheadAuthenticationFailureHandler);

        TelValidateCodeAuthenticationFilter validateCodeAuthenticationFilter = new TelValidateCodeAuthenticationFilter();

        validateCodeAuthenticationFilter.setAuthenticationManager(providerManager);


        http
//                .apply(feheadWebSecurityConfig)
//                .failureHandler(feheadAuthenticationFailureHandler)
//                .successHandler(feheadAuthenticationSuccessHandler)
//                .and()
//                .addFilterBefore(telValidateCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
//                .authenticationProvider(telValidateCodeAuthenticationProvider)
                .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(feheadAuthenticationSuccessHandler)
                .failureHandler(feheadAuthenticationFailureHandler)
//                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/loginByOtp").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super
////        auth.
//
//    }
}
