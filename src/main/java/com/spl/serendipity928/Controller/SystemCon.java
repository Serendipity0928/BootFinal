package com.spl.serendipity928.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemCon {

    @RequestMapping("/login")
    public String loginView() {
        return "login";
    }

}
