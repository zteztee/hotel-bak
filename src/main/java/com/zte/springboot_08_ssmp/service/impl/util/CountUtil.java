package com.zte.springboot_08_ssmp.service.impl.util;

import com.alibaba.fastjson.JSONObject;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.domain.CountClient;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import com.zte.springboot_08_ssmp.mapper.ClientStayRoomListMapper;
import com.zte.springboot_08_ssmp.mapper.CountClientMapper;
import com.zte.springboot_08_ssmp.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
public class CountUtil {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate  redisTemplate;

    //插入访问量方法
    public void insertCountVisit(String username){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String now = format.format(new Date());
        String nows = now+":"+username;
        if (stringRedisTemplate.opsForHash().hasKey("每日访问量统计", nows)==false){
            redisTemplate.opsForHash().put("每日访问量统计",nows,1);
        }else {
            redisTemplate.opsForHash().increment("每日访问量统计",nows,1);
        }
    }
    //插入下单量方法
    public void insertCountOrder(String username){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String now = format.format(new Date());
        String nows = now+":"+username;
        if (stringRedisTemplate.opsForHash().hasKey("每日下单量统计", nows)==false){
            System.out.println(nows+"1");
            redisTemplate.opsForHash().put("每日下单量统计",nows,1);
        }else {
            System.out.println(nows+"2");
            redisTemplate.opsForHash().increment("每日下单量统计",nows,1);
        }
    }
    //插入入住类型统计方法
    public void insertCountRoomtype(String roomtype, String roomnum, ClientStay clientStay){
        if (stringRedisTemplate.opsForHash().hasKey("房间类型入住统计", roomtype)==false){
            redisTemplate.opsForHash().put("房间类型入住统计",roomtype,1);
        }
        else {
            redisTemplate.opsForHash().increment("房间类型入住统计",roomtype,1);
        }
    }
    //获取7天用户访问量，下单量统计
    public List getDaysCount() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        List<String> countClients = new ArrayList<>();
        //过去七天
        c.setTime(new Date());
        for (int i = 0; i < 8; i++) {
            Date d = c.getTime();
            String day = format.format(d);
            Cursor<Map.Entry<Object, Object>> cursor = stringRedisTemplate.opsForHash()
                    .scan("每日访问量统计", ScanOptions.scanOptions().match(day + "*").build());
            int count = 0;
            while (cursor.hasNext()) {
                Map.Entry<Object,Object> entry = cursor.next();
                Object valueSet =  entry.getValue();
                count+=Integer.parseInt(valueSet.toString());
            }
            Cursor<Map.Entry<Object, Object>> cursor1 = stringRedisTemplate.opsForHash()
                    .scan("每日下单量统计", ScanOptions.scanOptions().match(day + "*").build());
            int count1 = 0;
            while (cursor1.hasNext()){
                Map.Entry<Object,Object> entry =cursor1.next();
                Object valueSet = entry.getValue();
                count1+=Integer.parseInt(valueSet.toString());
            }
            CountClient countClient = new CountClient();
            countClient.setDay(day);
            countClient.setValue1(count);
            countClient.setValue2(count1);
//                System.out.println(JSONObject.toJSONString(countClient));
            countClients.add(JSONObject.toJSONString(countClient));
            //关闭cursor
            cursor.close();
            c.add(Calendar.DATE, - 1);
        }
        return countClients;
    }

    //获取入住类型统计
    public List getOrderByRoomtype() throws Exception{
        List<String> countClients = new ArrayList<>();
        Set<Object> typeList = stringRedisTemplate.opsForHash().keys("房间类型入住统计");
        for (Object type : typeList) {
            Object value = stringRedisTemplate.opsForHash().get("房间类型入住统计", type);
            CountClient countClient = new CountClient();
            countClient.setRoomtype((String) type);
            countClient.setCount1(Integer.valueOf((String) value));
            System.out.println(JSONObject.toJSONString(countClient));
            countClients.add(JSONObject.toJSONString(countClient));
        }
        return countClients;
    }

    public void insertCountMoney(Double payneed,String username) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        String now = format.format(new Date());
        String nows = now+":"+username;
        if (stringRedisTemplate.opsForHash().hasKey("每日收入统计", nows)==false){
            redisTemplate.opsForHash().put("每日收入统计",nows,payneed);
        }else {
            redisTemplate.opsForHash().increment("每日收入统计",nows,payneed);
        }
    }
    //获取过去一周收入
    public List getCountLastThreeMonthMoney() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        List<String> countClients = new ArrayList<>();
        //过去三个月
        for (int i = 0; i < 3; i++) {
            Date m3 = c.getTime();
            String mon3 = format.format(m3);
            System.out.println("过去三个月：" + mon3);
            Cursor<Map.Entry<Object, Object>> cursor = stringRedisTemplate.opsForHash()
                    .scan("每日收入统计", ScanOptions.scanOptions().match(mon3 + "*").build());
            double count = 0;
            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                Object valueSet = entry.getValue();
                count += Double.parseDouble(valueSet.toString());
            }
            CountClient countClient = new CountClient();
            countClient.setValue3(count);
            countClient.setMonth(mon3);
            countClients.add(JSONObject.toJSONString(countClient));
            //关闭cursor
            cursor.close();
            c.add(Calendar.MONTH, -1);
        }
        return countClients;
    }
}

