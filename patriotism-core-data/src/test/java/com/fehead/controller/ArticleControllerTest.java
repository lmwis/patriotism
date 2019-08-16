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
 * @date 2019-08-16 16:09
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleControllerTest {

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
                MockMvcRequestBuilders.get(urlPre+"/data/article/lists")
                        .param("page","1")
                        .param("size","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 获取article详细信息
     * @throws Exception
     */
    @Test
    public void whenFindArticleDetailInfoSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/article/info/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
