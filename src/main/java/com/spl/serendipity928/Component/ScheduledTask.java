package com.spl.serendipity928.Component;

import com.alibaba.fastjson.JSON;
import com.spl.serendipity928.Pojo.ReportMessage;
import com.spl.serendipity928.Utils.EmailService;
import com.spl.serendipity928.Utils.HttpClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ScheduledTask {

    private final String fixedPart = "sfzs=1&bzxyy=&bzxyy_other=&brsfzc=1&tw=&sfcxzz=&zdjg=&zdjg_other=&sfgl=&gldd=&gldd_other=&glyy=&glyy_other=&gl_start=&gl_end=&sfmqjc=&sfzc_14=1&sfqw_14=0" +
            "&sfqw_14_remark=&sfzgfx=0&sfzgfx_remark=&sfjc_14=0&sfjc_14_remark=&sfjcqz_14=0&sfjcqz_14_remark=&sfgtjz_14=0&sfgtjz_14_remark=&szsqqz=0&sfyqk=&szdd=1&area=北京市 朝阳区" +
            "&city=北京市&province=北京市&address=北京市朝阳区东湖街道恒基伟业大厦BⅡ望京恒电大厦" +
            "&geo_api_info={\"type\":\"complete\",\"info\":\"SUCCESS\",\"status\":1,\"cEa\":\"jsonp_295677_\",\"position\":{\"Q\":40.00855,\"R\":116.48752000000002,\"lng\":116.48752,\"lat\":40.00855},\"message\":\"Get ipLocation success.Get address success.\",\"location_type\":\"ip\",\"accuracy\":null,\"isConverted\":true,\"addressComponent\":{\"citycode\":\"010\",\"adcode\":\"110105\",\"businessAreas\":[{\"name\":\"望京\",\"id\":\"110105\",\"location\":{\"Q\":39.996171,\"R\":116.47029299999997,\"lng\":116.470293,\"lat\":39.996171}},{\"name\":\"来广营\",\"id\":\"110105\",\"location\":{\"Q\":40.034234,\"R\":116.453779,\"lng\":116.453779,\"lat\":40.034234}}],\"neighborhoodType\":\"\",\"neighborhood\":\"\",\"building\":\"\",\"buildingType\":\"\",\"street\":\"望京东路\",\"streetNumber\":\"4号院\",\"country\":\"中国\",\"province\":\"北京市\",\"city\":\"\",\"district\":\"朝阳区\",\"township\":\"东湖街道\"},\"formattedAddress\":\"北京市朝阳区东湖街道恒基伟业大厦BⅡ望京恒电大厦\",\"roads\":[],\"crosses\":[],\"pois\":[]}" +
            "&gwdz=&is_move=0&move_reason=&move_remark=&realname=孙培林&number=SY1906412&uid=376685&created=1623661268&date=20210614&created_uid=300068&id=2994284&gwszdd=";

    @Resource
    private EmailService emailService;

    // 每天下午 17:01 执行
    @Scheduled(cron = "0 01 17 ? * * ")
    public void studentReport() {
        System.out.println("任务执行时间：" + LocalDateTime.now());

        String cookie = "eai-sess=epcdg6rc9dgbm5lt2v2gjr3j11";
        String url = "https://app.buaa.edu.cn/buaaxsncov/wap/default/save";
        String result = HttpClient.doPostOrGet(url, "POST", fixedPart, cookie);
        ReportMessage reportMessage = JSON.parseObject(result, ReportMessage.class);
        if(reportMessage == null || (reportMessage.getE() != 0 && reportMessage.getE() != 1)) {
            // 打卡失败, 发送邮箱  https://segmentfault.com/a/1190000021587834
            System.out.println("scheduledTask is failed!, result="+result);
            emailService.sendMail("878478652@qq.com", "自动打卡失败邮件通知",
                    "系统自动打开失败，失败原因已反馈，请立即手动打卡; 接口反馈信息：" + result, false);
            return;
        }

        System.out.println(result);
    }

    @Scheduled(cron = "0 05 17 ? * * ")
    public void Only4Email() {
        System.out.println("发送邮件...");
        String text = "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>${title}</title>\n" +
                "    <meta name=\"keywords\" content=\"注册成功\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge, chrome=1\">\n" +
                "</head>\n" +
                "<body  style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                "<div>\n" +
                "    <table cellpadding=\"0\" align=\"center\" style=\"width: 600px; margin: 0 auto; text-align: left;\n" +
                "    position: relative; border-radius: 5px;font-size: 14px; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0 0 5px;\n" +
                "    border-collapse: collapse; background: #fff no-repeat initial initial;\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <th valign=\"middle\" style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px;\n" +
                "                border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-radius: 5px 5px 0 0;\">\n" +
                "                    <span style=\"color: rgb(255, 255, 255); font-family: 微软雅黑; font-size: large; \">打卡邮件通知! （小乔）</span>\n" +
                "               </th>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                "                        <h2 style=\"margin: 5px 0; \">\n" +
                "                            <span style=\"line-height: 20px;  color: #333333; \">\n" +
                "                               <span style=\"line-height: 22px; font-size: medium; \">白白的展昭展护卫：</span></span>\n" +
                "                            </span>\n" +
                "                        </h2>\n" +
                "                        <p>WARNING:FORBIDDEN TO LURE YOUNG GIRLS<br>\n" +
                "                        当您在使用本网站时，遵守当地法律法规。<br>\n" +
                "                        如果您有什么疑问可以联系管理员，Email: 526570971@qq.com</p>\n" +
                "                        <p align=\"right\"><b></b></p>\n" +
                "                        <p align=\"right\">${date}</p>\n" +
                "                        <div style=\"width:700px;margin:0 auto;;line-height:1.3em;font-size:12px;color:#747474;\">\n" +
                "                            <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;margin-bottom:20px\">\n" +
                "                                <p>此为系统邮件，请勿回复<br>请保管好您的邮箱，避免账号被他人盗用</p>\n" +
                "                            </div>\n" +
                "                            <div style=\"line-height: 30px; margin: 0\">\n" +
                "                                <img src=\"http://47.93.8.7:8080/static/my_qiao.jpg\" alt=\"IMAGE\" STYLE=\"width: 30px; height: 30px; border-radius: 50px\">\n" +
                "                                <p style=\"display: inline-block\">@有多少人曾爱过你</p>\n" +
                "                            </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        text = text.replace("${date}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        emailService.sendMail("526570971@qq.com", "专属邮件-Bigger Sister", text, true);
    }

}
