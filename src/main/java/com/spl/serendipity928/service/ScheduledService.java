package com.spl.serendipity928.service;

import com.alibaba.fastjson.JSON;
import com.spl.serendipity928.constant.EmailRelevantCons;
import com.spl.serendipity928.dao.ReportInfoDao;
import com.spl.serendipity928.pojo.ReportInfo;
import com.spl.serendipity928.domain.ReportMessage;
import com.spl.serendipity928.util.EmailClient;
import com.spl.serendipity928.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledService {

    @Resource
    private ReportInfoDao reportInfoDao;

    @Resource
    private EmailClient emailClient;

    // 每天下午 17:01 执行
    @Scheduled(cron = "0 01 17 ? * * ")
    public void studentReport() {
        log.info("任务执行时间：{}.", LocalDateTime.now());

        // 查询数据库
        long curTime = System.currentTimeMillis();
        List<ReportInfo> reportInfoList = reportInfoDao.filterReportInfoList(curTime);

        reportInfoList.forEach(reportInfo -> {
            if(reportInfo.cookiesExpired - curTime < 86400000) {
                emailClient.sendMail(reportInfo.email, "Cookies过期提醒邮件",
                        "Cookies即将过期，可能导致打卡不成功，请及时更新cookies值", false);
            }
            String data = EmailRelevantCons.REPORT_LOCATION.replace("${name}", reportInfo.name).replace("${number}", reportInfo.studentID);
            String result = HttpClient.doPostOrGet(EmailRelevantCons.REPORT_URL, "POST", data, reportInfo.cookies);
            ReportMessage reportMessage = JSON.parseObject(result, ReportMessage.class);

            if(reportMessage == null || (reportMessage.getE() != 0 && reportMessage.getE() != 1)) {
                // 打卡失败, 发送邮箱  https://segmentfault.com/a/1190000021587834
                log.error("scheduledTask is failed!, result={}.", result);
                emailClient.sendMail(reportInfo.email, "自动打卡失败邮件通知",
                        "系统自动打卡失败，失败原因已反馈，请立即手动打卡; 接口反馈信息：" + result, false);
            }
        });
    }

    @Scheduled(cron = "0 05 17 ? * * ")
    public void Only4Email() {
        log.info("ScheduledTask, 发送专属邮件...");

        String emailContent = EmailRelevantCons.SPECIFIED_CONTENT.replace("${date}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        emailClient.sendMail("526570971@qq.com", "专属邮件-Bigger Sister", emailContent, true);
    }

}
