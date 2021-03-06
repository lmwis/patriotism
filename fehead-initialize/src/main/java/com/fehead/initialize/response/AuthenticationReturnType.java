package com.fehead.initialize.response;

/**
 * 封装授权登陆过程过程中的返回
 *
 * @author lmwis on 2019-07-24 12:19
 */
public class AuthenticationReturnType extends FeheadResponse{

    //返回状态码
    Integer code;


    public static AuthenticationReturnType create(Object result,Integer code){
        AuthenticationReturnType authenticationReturnType = new AuthenticationReturnType();
        authenticationReturnType.setCode(code);
        authenticationReturnType.setData(result);
        return authenticationReturnType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
