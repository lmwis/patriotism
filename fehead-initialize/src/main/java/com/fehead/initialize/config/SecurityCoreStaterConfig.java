package com.fehead.initialize.config;

import com.fehead.initialize.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lmwis on 2019-07-20 17:24
 */

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreStaterConfig {
}
