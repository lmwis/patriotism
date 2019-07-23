package com.fehead.initialize.login.config;

import com.fehead.initialize.login.TelValidateCodeAuthenticationFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author lmwis on 2019-07-22 10:26
 */
@Component("feheadLoginSecurityConfig")
public class FeheadLoginSecurityConfig
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TelValidateCodeAuthenticationFilter telValidateCodeAuthenticationFilter;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);

        builder.addFilterBefore(telValidateCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public TelValidateCodeAuthenticationFilter getTelValidateCodeAuthenticationFilter() {
        return telValidateCodeAuthenticationFilter;
    }

    public void setTelValidateCodeAuthenticationFilter(TelValidateCodeAuthenticationFilter telValidateCodeAuthenticationFilter) {
        this.telValidateCodeAuthenticationFilter = telValidateCodeAuthenticationFilter;
    }

}
