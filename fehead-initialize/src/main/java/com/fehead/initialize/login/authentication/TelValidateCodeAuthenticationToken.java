package com.fehead.initialize.login.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author lmwis on 2019-07-21 10:16
 */
public class TelValidateCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -5398316014284884362L;
    private final Object principal;
    private Object credentials;
    private Object type;

    public TelValidateCodeAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public TelValidateCodeAuthenticationToken(Object principal, Object credentials,
                                              Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * 禁止手动设置验证状态  只能通过构造函数创建一个新的验证后的Token
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    /**
     * 抹去验证码
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
