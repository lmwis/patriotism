package com.fehead.initialize.error;

import org.springframework.security.core.AuthenticationException;

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
 * @author Nightnessss 2019/7/22 16:40
 */
public class SmsValidateException extends AuthenticationException implements CommonError {

    public SmsValidateException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsValidateException(String msg) {
        super(msg);
    }

    @Override
    public int getErrorCode() {
        return 0;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return null;
    }
}
