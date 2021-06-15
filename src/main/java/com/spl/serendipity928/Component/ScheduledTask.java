package com.spl.serendipity928.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spl.serendipity928.Pojo.ReportMessage;
import com.spl.serendipity928.Utils.HttpClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.dc.pr.PRError;

import java.time.LocalDateTime;

@Component
public class ScheduledTask {

    private final String fixedPart = "sfzs=1&bzxyy=&bzxyy_other=&brsfzc=1&tw=&sfcxzz=&zdjg=&zdjg_other=&sfgl=&gldd=&gldd_other=&glyy=&glyy_other=&gl_start=&gl_end=&sfmqjc=&sfzc_14=1&sfqw_14=0" +
            "&sfqw_14_remark=&sfzgfx=0&sfzgfx_remark=&sfjc_14=0&sfjc_14_remark=&sfjcqz_14=0&sfjcqz_14_remark=&sfgtjz_14=0&sfgtjz_14_remark=&szsqqz=0&sfyqk=&szdd=1&area=北京市 朝阳区" +
            "&city=北京市&province=北京市&address=北京市朝阳区东湖街道恒基伟业大厦BⅡ望京恒电大厦" +
            "&geo_api_info={\"type\":\"complete\",\"info\":\"SUCCESS\",\"status\":1,\"cEa\":\"jsonp_295677_\",\"position\":{\"Q\":40.00855,\"R\":116.48752000000002,\"lng\":116.48752,\"lat\":40.00855},\"message\":\"Get ipLocation success.Get address success.\",\"location_type\":\"ip\",\"accuracy\":null,\"isConverted\":true,\"addressComponent\":{\"citycode\":\"010\",\"adcode\":\"110105\",\"businessAreas\":[{\"name\":\"望京\",\"id\":\"110105\",\"location\":{\"Q\":39.996171,\"R\":116.47029299999997,\"lng\":116.470293,\"lat\":39.996171}},{\"name\":\"来广营\",\"id\":\"110105\",\"location\":{\"Q\":40.034234,\"R\":116.453779,\"lng\":116.453779,\"lat\":40.034234}}],\"neighborhoodType\":\"\",\"neighborhood\":\"\",\"building\":\"\",\"buildingType\":\"\",\"street\":\"望京东路\",\"streetNumber\":\"4号院\",\"country\":\"中国\",\"province\":\"北京市\",\"city\":\"\",\"district\":\"朝阳区\",\"township\":\"东湖街道\"},\"formattedAddress\":\"北京市朝阳区东湖街道恒基伟业大厦BⅡ望京恒电大厦\",\"roads\":[],\"crosses\":[],\"pois\":[]}" +
            "&gwdz=&is_move=0&move_reason=&move_remark=&realname=孙培林&number=SY1906412&uid=376685&created=1623661268&date=20210614&created_uid=300068&id=2994284&gwszdd=";

    // 每天下午 17:01 执行
    @Scheduled(cron = "0 01 17 ? * * ")
    public void scheduledTask() {
        System.out.println("任务执行时间：" + LocalDateTime.now());

        String cookie = "eai-sess=epcdg6rc9dgbm5lt2v2gjr3j11";
        String url = "https://app.buaa.edu.cn/buaaxsncov/wap/default/save";
        String result = HttpClient.doPostOrGet(url, "POST", fixedPart, cookie);
        ReportMessage reportMessage = JSON.parseObject(result, ReportMessage.class);
        if(reportMessage == null || (reportMessage.getE() != 0 && reportMessage.getE() != 1)) {
            // 打卡失败, 发送邮箱  https://segmentfault.com/a/1190000021587834
            System.out.println("scheduledTask is failed!, result="+result);

        }
        System.out.println(result);

    }
}
