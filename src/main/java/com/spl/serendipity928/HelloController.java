package com.spl.serendipity928;

import com.spl.serendipity928.Utils.EmailService;
import com.spl.serendipity928.Dao.ReportInfoDao;
import com.spl.serendipity928.Pojo.ReportInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class HelloController {

    @Resource
    private EmailService emailService;

    @Resource
    private ReportInfoDao reportInfoDao;

    @RequestMapping("/index")
    public String hello() {
//        emailService.sendMail("878478652@qq.com", "主题", "内容");
        return "index";
    }

    @RequestMapping("/select")
    @ResponseBody
    public String selectCall() {
        ReportInfo reportInfo = reportInfoDao.selectByPrimaryKey(0L);
        if(reportInfo == null) return "没有查询到数据，id=0L";
//        System.out.println(reportInfo.toString());
        return reportInfo.toString();
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insertCall() {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setName("孙培林").setStudentID("SY1906412").setEmail("878478652@qq.com")
                .setCookies("eai-sess=epcdg6rc9dgbm5lt2v2gjr3j11").setCookiesExpired(System.currentTimeMillis())
                .setStime(System.currentTimeMillis()).setEtime(System.currentTimeMillis());
        int i = reportInfoDao.insertReportInfo(reportInfo);
        if(i == 0) return "没有插入任何数据";
        return "插入成功！";
    }
  
}