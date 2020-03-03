package com.woniu.netmonitor.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.woniu.netmonitor.util.DateUtil;
import com.woniu.netmonitor.util.WebClientUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientMain {
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();
    static Calendar cal = Calendar.getInstance();
    public static DateFormat format6 = new SimpleDateFormat("MM-dd");
    public static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) {
//        String sss = "10.0";
//        double ds = Double.valueOf(sss);
//        //Long ls = Long.valueOf(sss);
//        System.out.println(ds);
//        long lo = SECURE_RANDOM.nextLong();
//        long lo2 = SECURE_RANDOM.nextLong();
//        System.out.println(lo+"");
//        System.out.println(lo2+"");
//
//        System.out.println(DateUtil.longToString(System.currentTimeMillis()));
//        String ss = ClientMain.randomStrForLength(8);
//        System.out.println(ss);
//        String baseUrl = "http://www.hnjs.gov.cn/xxgk/category/getArticles/null/null?page=1&length=10&categoryId=2937";
//        WebClient webClient = WebClient.create(baseUrl);
//        Mono<JSONObject> mono = webClient.get().retrieve().bodyToMono(JSONObject.class);
//        JSONObject resultEntity = mono.block();
//        //JSONObject jsonObject = JSONObject.parseObject(resultEntity);
//        JSONArray jsonArray = resultEntity.getJSONArray("list");
//        String ss = "";

        Date dateTime = null;
        dateTime = regxStringToDate("(02-11)");
        System.out.println(dateTime.toString());
    }

    public static Date regxStringToDate(String timeStr){
        Date time = null;
        String dateStr = null;
        String regx0 = "(\\d{4}).(\\d{1,2}).(\\d{1,2})";
        String regx1 = "(\\d{1,2}).(\\d{1,2})";
        try{
            Pattern compile = Pattern.compile(regx0);
            Matcher matcher = compile.matcher(timeStr);
            if (matcher.find()){
                String year = matcher.group(1);
                String month = matcher.group(2);
                String day = matcher.group(3);
                dateStr = year + "-" + month + "-" + day;
            } else {
                compile = Pattern.compile(regx1);
                matcher = compile.matcher(timeStr);
                if (matcher.find()){
                    String month = matcher.group(1);
                    String day = matcher.group(2);
                    String tempDate = month + "-" + day;
                    String tempDateStr = format6.format(format6.parse(tempDate));
                    String currentTimeStr = new SimpleDateFormat("MM-dd").format(new Date());
                    int year = cal.get(Calendar.YEAR);
                    if (tempDateStr.compareTo(currentTimeStr) > 0){
                        year = year - 1;
                    }
                    dateStr = year + "-" + tempDateStr;
                }
            }
            time = format1.parse(dateStr);
        }catch (Exception e){
            e.getStackTrace();
        }
        return time;
    }

    public static String randomStrForLength(int length){
        return RandomStringUtils.random(length,0,0,true,true,(char[])null,SECURE_RANDOM);
    }
}
