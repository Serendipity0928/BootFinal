package com.spl.serendipity928;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping("/school/report")
  public String hello() {
      return "Hello World";
  }
  
}