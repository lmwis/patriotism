package com.fehead.initialize.service;

import com.fehead.initialize.controller.viewobject.UserVO;
import com.fehead.initialize.dataobject.UserDO;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.service.model.UserModel;

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
 * @author Nightnessss 2019/7/8 14:53
 */
public interface UserService {

    public UserModel getUserById(Integer id);

    public UserModel getUserByThirdPartyid(String thirdPartyId);

    public int addInfo(UserModel userModel);

    // 用户注册
    public void register(UserModel userModel) throws BusinessException;

    public int addUser(UserModel userModel);

    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;

    UserDO getUserByTel(String tel);

    public UserVO getUserByUsername(String username) throws BusinessException;
}
