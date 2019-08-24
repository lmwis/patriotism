package com.fehead.coredata.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-17 17:05
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataMapperTest {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    IfLikeMapper ifLikeMapper;

    @Test
    public void whenGoodAndCancelSuccess() {

        ifLikeMapper.cancel(1,1);
    }
}
