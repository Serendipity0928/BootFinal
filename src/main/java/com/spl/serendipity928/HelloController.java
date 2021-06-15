package com.spl.serendipity928;

import com.spl.serendipity928.Component.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private EmailService emailService;

    @RequestMapping("/school/report")
    public String hello() {
      emailService.sendMail("878478652@qq.com", "主题", "内容");
      return "Hello World";
    }
  
}