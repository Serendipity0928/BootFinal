package com.spl.serendipity928;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class RootController {

    @RequestMapping("/index")
    @ResponseBody
    public String hello() {

//        emailService.sendMail("878478652@qq.com", "主题", "内容");
//        "2021-06-17 00:00:01"
        long date0 = new Date("2021-06-17 00:00:01").getTime();
        long date1 = new Date().getTime();
        long l = System.currentTimeMillis();
        System.out.println(date0);
        System.out.println(date1);
        System.out.println(l);

        return "index";
    }
}