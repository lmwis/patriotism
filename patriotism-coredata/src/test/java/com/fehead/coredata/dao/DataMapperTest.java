package com.fehead.coredata.dao;

import com.fehead.coredata.dao.dataobject.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired
    DataMapper dataMapper;

    @Test
    public void whenGoodAndCancelSuccess() {

        ifLikeMapper.cancel(1,1);
    }

    @Test
    public void whenSelectDataByTagSuccess() {

        List<Data> dataList = dataMapper.selectDataByTag(2004);

        System.out.println(dataList);
    }
}
