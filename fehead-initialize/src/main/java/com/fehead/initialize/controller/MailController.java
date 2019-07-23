package com.fehead.initialize.controller;

import com.fehead.initialize.error.BusinessException;
import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.utils.SendEmailUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @author Nightnessss 2019/7/23 9:56
 */
@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private SendEmailUtil sendEmailUtil;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/send")
    public CommonReturnType sendAuthenticationEmail(HttpServletRequest request, HttpServletResponse response) throws MessagingException, BusinessException {

        String toAddress = request.getParameter("toAddress");
        String uri = "index.html";
        String subject = "THIS IS A TEST EMAIL";
        String templateName = "email";

        sendEmailUtil.sendEmail(toAddress, uri, subject, templateName);

        return CommonReturnType.creat(null);
    }
}


