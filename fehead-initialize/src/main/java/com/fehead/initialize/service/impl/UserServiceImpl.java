package com.fehead.initialize.service.impl;

import com.alibaba.druid.util.StringUtils;
<<<<<<< HEAD
import com.fehead.initialize.controller.viewobject.UserVO;
=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
import com.fehead.initialize.dao.UserDOMapper;
import com.fehead.initialize.dao.UserPasswordDOMapper;
import com.fehead.initialize.dataobject.UserDO;
import com.fehead.initialize.dataobject.UserPasswordDO;
import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.error.EmBusinessError;
import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
<<<<<<< HEAD
import com.fehead.initialize.utils.CheckEmailAndTelphoneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
@Service
public class UserServiceImpl implements UserService {

<<<<<<< HEAD
    Logger logger = LoggerFactory.getLogger(getClass());

=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        // 调用userdomapper获取到对应用户dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);

        UserModel userModel = convertFromDO(userDO, userPasswordDO);

        return userModel;
    }

<<<<<<< HEAD
    /**
     * 通过第三方id查用户
     * @param thirdPartyId
     * @return
     * @Author Nightnessss
     */
    @Override
    public UserModel getUserByThirdPartyid(String thirdPartyId) {
        UserDO userDO = userDOMapper.selectByThirdPartyId(thirdPartyId);
        if (userDO == null) {
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        UserModel userModel = convertFromDO(userDO, userPasswordDO);
        return userModel;
    }

    @Override
    public int addInfo(UserModel userModel) {


        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        int id = userDOMapper.updateByPrimaryKeySelective(userDO);
        return id;
    }

=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        // 将userModel转为dataobject存入数据库
        if (userModel == null) {
<<<<<<< HEAD
            logger.info("userModel为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (StringUtils.isEmpty(userModel.getTelphone()) && StringUtils.isEmpty(userModel.getEmail())) {
            logger.info("手机号或邮箱为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号或邮箱为空");
        }
        if (StringUtils.isEmpty(userModel.getEncrptPassword())) {
            logger.info("密码不能为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        }
        if (StringUtils.isEmpty(userModel.getDisplayName())) {
            logger.info("昵称不能为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "昵称不能为空");
        }
=======
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (StringUtils.isEmpty(userModel.getTelphone())) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if (StringUtils.isEmpty(userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "密码不能为空");
        }
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
        // userModel -> userDO
        UserDO userDO = convertFromModel(userModel);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex) {
<<<<<<< HEAD
            logger.info("手机号或邮箱已被注册");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号或邮箱已被注册");
=======
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已被注册");
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
        }

        userModel.setId(userDO.getId());

        // userModel -> userPasswordDO
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
<<<<<<< HEAD
        logger.info("注册成功！");

    }

    @Override
    public int addUser(UserModel userModel) {

        UserDO userDO = new UserDO();
//        BeanUtils.copyProperties(userModel, userDO);
        userDO.setAvatar(userModel.getAvatar());
        userDO.setDisplayName(userModel.getDisplayName());
//        userDO.setEmail(userModel.getEmail());
        userDO.setRegisterMode(userModel.getRegisterMode());
        userDO.setThirdPartyId(userModel.getThirdPartyId());
//        userM
        int id = userDOMapper.insertSelective(userDO);

        return userDO.getId();
=======

        return;
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        // 判空
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(encrptPassword)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        // 用户校验
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        if (!StringUtils.equals(encrptPassword, userPasswordDO.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }

        UserModel userModel =  convertFromDO(userDO,userPasswordDO);
        return userModel;
        // 标记用户已登录

    }

    @Override
    public UserDO getUserByTel(String tel) {

        if(org.apache.commons.lang3.StringUtils.equals(tel,"123")){
            return new UserDO();
        }

        return null;
    }

<<<<<<< HEAD
    @Override
    public UserVO getUserByUsername(String username) throws BusinessException {
        UserDO userDO = new UserDO();
        if (CheckEmailAndTelphoneUtil.checkTelphone(username)) {
            userDO = userDOMapper.selectByTelphone(username);
        } else if (CheckEmailAndTelphoneUtil.checkEmail(username)) {
            userDO = userDOMapper.selectByEmail(username);
        } else {
            logger.info("用户名不合法");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名不合法");
        }

        if (userDO == null) {
            logger.info("用户不存在");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);

        return userVO;
    }

=======
>>>>>>> 6241bafc948d42dfafed63292dd74c355a70d486
    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());

        return userPasswordDO;
    }

    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserDO userDO = new UserDO();

        BeanUtils.copyProperties(userModel, userDO, "id");
//        userDO.setName(userModel.getName());
//        userDO.setGender(userModel.getGender());
//        userDO.setAge(userModel.getAge());
//        userDO.setTelphone(userModel.getTelphone());
//        userDO.setThirdPartyId(userModel.getThirdPartyId());
//        userDO.setRegisterMode(userModel.getRegisterMode());
        return userDO;
    }

    private UserModel convertFromDO(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }

        return userModel;
    }
}
