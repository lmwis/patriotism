package com.fehead.initialize.service;

import com.fehead.initialize.error.BusinessException;

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
 * @author Nightnessss 2019/7/22 12:42
 */
public interface RegisterService {

    public boolean check(String telphone) throws BusinessException;

    public void send(String telphone);

    public void registerByTelphone(String telphoneInRequest, String password) throws BusinessException;

    public boolean validate(String telphoneInRequest, String codeInRequest) throws BusinessException;
}
