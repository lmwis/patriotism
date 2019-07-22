package com.fehead.initialize.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

/**
 * @author lmwis on 2019-07-22 10:26
 */
//@Configuration
public class FeheadWebSecurityConfig {

    private boolean authenticationManagerInitialized;

    private AuthenticationManager authenticationManager;

//    @Override
//    public AuthenticationManager getAuthenticationManager() throws Exception {
//        if (this.authenticationManagerInitialized) {
//            return this.authenticationManager;
//        }
//        AuthenticationManagerBuilder authBuilder = authenticationManagerBuilder(
//                this.objectPostProcessor);
//        if (this.buildingAuthenticationManager.getAndSet(true)) {
//            return new AuthenticationManagerDelegator(authBuilder);
//        }
//
//        for (GlobalAuthenticationConfigurerAdapter config : globalAuthConfigurers) {
//            authBuilder.apply(config);
//        }
//
//        authenticationManager = authBuilder.build();
//
//        if (authenticationManager == null) {
//            authenticationManager = getAuthenticationManagerBean();
//        }
//
//        this.authenticationManagerInitialized = true;
//        return authenticationManager;

//        return null;
//    }

}
