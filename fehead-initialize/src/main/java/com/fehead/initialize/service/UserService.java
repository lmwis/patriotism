package com.fehead.initialize.service;

<<<<<<< HEAD
import com.fehead.initialize.controller.viewobject.UserVO;
=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
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

<<<<<<< HEAD
    public UserModel getUserByThirdPartyid(String thirdPartyId);

    public int addInfo(UserModel userModel);

    // 用户注册
    public void register(UserModel userModel) throws BusinessException;

    public int addUser(UserModel userModel);

    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;

    UserDO getUserByTel(String tel);

    public UserVO getUserByUsername(String username) throws BusinessException;
=======
    // 用户注册
    public void register(UserModel userModel) throws BusinessException;

    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException;

    UserDO getUserByTel(String tel);
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
}
