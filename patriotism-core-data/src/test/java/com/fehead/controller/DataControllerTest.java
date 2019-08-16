package com.fehead.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:26
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataControllerTest {

    @Autowired
    WebApplicationContext applicationContext;

    MockMvc mockMvc;

    String urlPre = "/api/v1";

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }


    /**
     * 首页分页测试
     * @throws Exception
     */
    @Test
    public void whenFindArticleListsPageableSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/lists")
                        .param("page","5")
                        .param("size","2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 进行评论
     * @throws Exception
     */
    @Test
    public void whenDoCommentByDataIdSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                .post(urlPre+"/data/info/1/comment")
                .param("user_id","14")
                .param("comment_content","新创建的评论"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    /**
     * 根据dataId获取该数据类型
     * @throws Exception
     */
    @Test
    public void whenFindTypeByDataIdSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders
                .get(urlPre+"/data/info/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
