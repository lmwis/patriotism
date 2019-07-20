package com.fehead.initialize.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lmwis on 2019-07-20 19:38
 */
public class FeheadWebAuthenticationDetails extends WebAuthenticationDetails {

    public static final Logger logger = LoggerFactory.getLogger(FeheadWebAuthenticationDetails.class);

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public FeheadWebAuthenticationDetails(HttpServletRequest request) {

        super(request);

        ServletWebRequest req = new ServletWebRequest(request);

        String tel = req.getParameter("tel");
        String code = req.getParameter("code");

        logger.info("tel:"+tel);
        logger.info("code:"+code);

    }
}
