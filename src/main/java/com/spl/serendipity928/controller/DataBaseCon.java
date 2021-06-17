package com.spl.serendipity928.controller;

import com.spl.serendipity928.dao.ReportInfoDao;
import com.spl.serendipity928.pojo.ReportInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class DataBaseCon {

    @Resource
    private ReportInfoDao reportInfoDao;

    @RequestMapping("/db/reportInfo/select")
    public Object reportInfoSelect() {
        ReportInfo reportInfo = reportInfoDao.selectByPrimaryKey(1L);
        if(reportInfo == null) return "没有查询到数据，id=0L";
        return reportInfo;
    }

    @RequestMapping("/db/reportInfo/insert")
    public String reportInfoInsert() {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setName("孙培林").setStudentID("SY1906412").setEmail("878478652@qq.com")
                .setCookies("eai-sess=epcdg6rc9dgbm5lt2v2gjr3j11").setCookiesExpired(System.currentTimeMillis())
                .setStime(System.currentTimeMillis()).setEtime(System.currentTimeMillis());
        int ret = 0;
        try {
            ret = reportInfoDao.insertReportInfo(reportInfo);
            log.info("成功插入一条数据.");
        } catch (Exception e) {
            log.error("插入数据时, 出现错误.", e);
            return "插入失败, 请稍后再试~";
        }
//        if(ret == 0) return "没有插入任何数据";  思考: 会有这种情况吗？
        return "插入成功！";
    }

    @RequestMapping("/db/reportInfo/update")
    public String reportInfoUpdate() {
        ReportInfo reportInfo = new ReportInfo();
        reportInfo.setName("孙培林").setStudentID("SY1906412").setEmail("878478652@qq.com")
                .setCookies("eai-sess=epcdg6rc9dgbm5lt2v2gjr3j11").setCookiesExpired(1624982400000L)
                .setStime(1623860919322L).setEtime(1624982400000L);

        int ret = reportInfoDao.updateReportInfo(reportInfo);
        if(ret == 0) return "没有找到对应的学生ID";
        else return "更新成功";
    }
}
