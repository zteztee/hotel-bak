package com.zte.springboot_08_ssmp;

import com.alibaba.fastjson.JSONObject;
import com.zte.springboot_08_ssmp.domain.CountClient;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import com.zte.springboot_08_ssmp.mapper.RoomtypeMapper;
import com.zte.springboot_08_ssmp.service.RoomtypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class HotelBackApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RoomtypeService roomtypeService;

    @Test
    public void findRoomtype() {
        //查找所有房间类型
        List<Roomtype> roomtypeList = roomtypeService.getAll();
        //向缓存中添加内容
        try {
            //把list转换成字符串
            //向缓存中存入数据
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
            System.out.println(countClients);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Test
        public void findRoomtype1(){
          try {
              if (stringRedisTemplate.opsForHash().hasKey("房间类型入住统计", "双人房")==false){
                  redisTemplate.opsForHash().put("房间类型入住统计","双人房",0);
              }else {
                  redisTemplate.opsForHash().increment("房间类型入住统计","双人房",1);
              }
          }
          catch (Exception e) {
              e.printStackTrace();
          }
        }
//从缓存中取内容
    @Test
    public void addWeekDetailCount(){
        try {

            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            String now = format.format(new Date());
            if (stringRedisTemplate.opsForHash().hasKey("每日访问量统计", now)==false){
             redisTemplate.opsForHash().put("每日访问量统计",now,1);
            }else {
               redisTemplate.opsForHash().increment("每日访问量统计",now,1);
            }
     }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    @Test
    public void addDailyOccupancy(){
        try {
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM");
            String now = format.format(new Date());
            if (stringRedisTemplate.opsForHash().hasKey("每日下单量", now)==false){
                redisTemplate.opsForHash().put("每日下单量",now,1);
            }else {
                redisTemplate.opsForHash().increment("每日下单量",now,1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findlastdays(){
        try {
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
            System.out.println(countClients);



////过去一月
//            c.setTime(new Date());
//            c.add(Calendar.MONTH, -1);
//            Date m = c.getTime();
//            String mon = format.format(m);
//            System.out.println("过去一个月："+mon);
//
////过去三个月
//            c.setTime(new Date());
//            c.add(Calendar.MONTH, -3);
//            Date m3 = c.getTime();
//            String mon3 = format.format(m3);
//            System.out.println("过去三个月："+mon3);
//
////过去一年
//            c.setTime(new Date());
//            c.add(Calendar.YEAR, -1);
//            Date y = c.getTime();
//            String year = format.format(y);
//            System.out.println("过去一年："+year);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试过去三个月的收入
    @Test
    public void TestLastThreeMonthMoney(){
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
            countClients.add(JSONObject.toJSONString(countClient));
            //关闭cursor
            cursor.close();
            c.add(Calendar.MONTH, -1);
        }
        System.out.println(countClients);
    }
}
