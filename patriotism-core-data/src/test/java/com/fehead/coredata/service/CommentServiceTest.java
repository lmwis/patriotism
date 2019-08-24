package com.fehead.coredata.service;

import com.fehead.coredata.dao.IfLikeMapper;
import com.fehead.coredata.dao.dataobject.IfLike;
import com.fehead.coredata.error.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 11:58
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    IfLikeMapper ifLikeMapper;

    @Test
    public void selectArticleCommentsByActualIdPageable() throws BusinessException {
        Pageable pageable = new PageRequest(1, 6);
//        commentService.selectArticleCommentByActualId(1,pageable).forEach(k->{
//            System.out.println(new ReflectionToStringBuilder(k));
//        });
    }

    @Test
    public void selectCommentByActualIdAndDataType() {

        IfLike ifLike = ifLikeMapper.selectLikeByUserIdAndCommentId(25, 11);

        System.out.println(ifLike == null);

    }
}
