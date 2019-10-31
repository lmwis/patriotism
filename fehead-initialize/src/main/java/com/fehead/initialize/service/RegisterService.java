
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

    public void registerByTelphone(String telphoneInRequest, String password, String displayName) throws BusinessException;

    public void registerByEmail(String email, String password,String displayName) throws BusinessException;

    public boolean registerValidate(String telphoneInRequest, String codeInRequest) throws BusinessException;
}