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
 * @author lmwis on 2019-08-14 19:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoControllerTest {

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
    public void whenFindVideoListsPageableSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/video/lists")
                        .param("page","0")
                        .param("size","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 按照id请求video
     * @throws Exception
     */
    @Test
    public void whenFindVideoInfoSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/video/info/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
