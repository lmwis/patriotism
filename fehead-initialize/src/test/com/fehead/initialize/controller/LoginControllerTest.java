package com.fehead.initialize.controller;

import com.fehead.initialize.service.UserService;
import com.fehead.initialize.service.model.UserModel;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
 * @author lmwis on 2019-08-11 17:38
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    @Autowired
    WebApplicationContext wac;

//    String urlPre="http://localhost:8092/api/v1";

    String urlPre="";

    private MockMvc mockMvc;
    @Autowired
    UserService userService;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    //发送注册的验证码
    public void whenSendRegisterOtpCodeSuccess(){
//        mockMvc.perform(MockMvcRequestBuilders.post("/send/otpCode"))
    }

    @Test
    public void testTestController() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoFailWithUnauthorized() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post(urlPre+"/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenRegisterByEmailSuccess(){



    }

    @Test
    public void whenUsernamePasswordLoginSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post(urlPre+"/loginByForm")
                .param("username","15389159576")
                .param("password","gongtengxinyi")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void testUserService(){
        UserModel userByTel = userService.getUserById(14);
        System.out.println(userByTel.getEncrptPassword());
        ReflectionToStringBuilder.toString(userService.getUserById(14), ToStringStyle.MULTI_LINE_STYLE);
    }



}
