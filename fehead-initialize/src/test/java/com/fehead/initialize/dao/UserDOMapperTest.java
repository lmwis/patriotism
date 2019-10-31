package com.fehead.initialize.dao;

import com.fehead.InitializeApplication;
import com.fehead.initialize.dataobject.UserDO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-10-27 13:55
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InitializeApplication.class)
public class UserDOMapperTest {

    @Autowired
    private UserDOMapper userDOMapper;
    @Test
    public void WhenselectByThirdPartyIdSuccess() {

        UserDO userDO = userDOMapper.selectByThirdPartyId("123");

        System.out.println(new ReflectionToStringBuilder(userDO));
    }
}
