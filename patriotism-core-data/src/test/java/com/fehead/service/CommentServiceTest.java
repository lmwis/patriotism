package com.fehead.service;

import com.fehead.error.BusinessException;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

    @Test
    public void selectArticleCommentsByActualIdPageable() throws BusinessException {
        Pageable pageable = new PageRequest(1, 6);
        commentService.selectArticleCommentByActualId(1,pageable).forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
    }
}
