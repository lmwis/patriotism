package com.fehead.initialize.controller;

import com.fehead.InitializeApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
 * @author Nightnessss 2019/8/12 9:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InitializeApplication.class)
public class RegisterControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 测试手机验证码注册
     * @throws Exception
     */
    @Test
    public void registerByTelphone() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/user/register/tel")
                .param("tel", "17772726977")
                .param("password", "123456")
                .param("sms_key", "$2a$10$ztF/Pj2THdzmPy26KMeKEesAYV9sY8x8milMTAOxJixoCIL112kmC")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    /**
     * 测试邮箱验证注册
     * @throws Exception
     */
    @Test
    public void registerByEmail() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/user/register/email")
                .param("email", "1948730080@qq.com")
                .param("password", "123456")
                .param("sms_key", "$2a$10$ztF/Pj2THdzmPy26KMeKEesAYV9sY8x8milMTAOxJixoCIL112kmC")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
