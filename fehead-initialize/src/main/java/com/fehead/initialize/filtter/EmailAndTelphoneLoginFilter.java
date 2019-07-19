package com.fehead.initialize.filtter;

import com.fehead.initialize.error.BusinessExpection;
import com.fehead.initialize.error.EmBusinessError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
 * @author Nightnessss 2019/7/19 17:05
 */
public class EmailAndTelphoneLoginFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("requestURI:" + httpServletRequest.getRequestURI());
        logger.info("method:" + httpServletRequest.getMethod());
        if (StringUtils.equals("/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {

            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }


        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) {
    }
}
