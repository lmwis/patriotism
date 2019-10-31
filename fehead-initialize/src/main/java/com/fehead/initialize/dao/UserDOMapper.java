package com.fehead.initialize.dao;

import com.fehead.initialize.dataobject.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByThirdPartyId(String thirdPartyId);

    void deleteByThirdPartyId(String thirdPartyId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserDO selectByTelphone(String telphone);

    UserDO selectByEmail(String email);

}