package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.CountClient;
import com.zte.springboot_08_ssmp.mapper.CountClientMapper;
import com.zte.springboot_08_ssmp.service.CountClientService;
import com.zte.springboot_08_ssmp.service.RoomtypeService;
import com.zte.springboot_08_ssmp.service.impl.util.CountUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CountClientServiceImpl extends ServiceImpl<CountClientMapper, CountClient> implements CountClientService {
    @Autowired
    CountClientMapper countClientMapper;

    @Autowired
    CountUtil countUtil;
    @Autowired
    RoomtypeService roomtypeService;

    @Override
    public String getBest(Integer aid) {
        System.out.println("测试前端");
        List<CountClient> bests = countClientMapper.selectBest(aid);
        if (bests.listIterator().hasNext()){
            CountClient countClient = bests.get(0);
            return  countClient.getCount4();
        }
        else return null;
    }

    @Override
    public int getAllCount() {
        return countClientMapper.selectAllCount().getCount();
    }

    @Override
    public int getCountByAid(Integer aid) {
        return countClientMapper.selectAidCount(aid).getCount3();
    }

    @Override
    public int getCountByRoomnum(String roomnum) {
        return countClientMapper.selectRoomnumCount(roomnum).getCount2();
    }

    @Override
    public int getCountByRoomtype(String roomtype) {
        return countClientMapper.selectTypeCount(roomtype).getCount1();
    }

    public List getOrderByRoomtype() throws Exception{
        return countUtil.getOrderByRoomtype();
    }

    @Override
    public void addWeekDetailCount(String username) throws Exception {
        countUtil.insertCountVisit(username);
//        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
//        String now = format.format(new Date());
//        String nows = now+":"+username;
//        if (stringRedisTemplate.opsForHash().hasKey("每日访问量统计", nows)==false){
//            redisTemplate.opsForHash().put("每日访问量统计",nows,1);
//        }else {
//            redisTemplate.opsForHash().increment("每日访问量统计",nows,1);
//        }

    }

    @Override
    public List getDaysCount() throws Exception {
        return countUtil.getDaysCount();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar c = Calendar.getInstance();
//        List<String> countClients = new ArrayList<>();
//        //过去七天
//        c.setTime(new Date());
//        for (int i = 0; i < 8; i++) {
//            Date d = c.getTime();
//            String day = format.format(d);
//            Cursor<Map.Entry<Object, Object>> cursor = stringRedisTemplate.opsForHash()
//                    .scan("每日访问量统计", ScanOptions.scanOptions().match(day + "*").build());
//            int count = 0;
//            while (cursor.hasNext()) {
//                Map.Entry<Object,Object> entry = cursor.next();
//                Object valueSet =  entry.getValue();
//                count+=Integer.parseInt(valueSet.toString());
//            }
//            Cursor<Map.Entry<Object, Object>> cursor1 = stringRedisTemplate.opsForHash()
//                    .scan("每日下单量统计", ScanOptions.scanOptions().match(day + "*").build());
//            int count1 = 0;
//            while (cursor1.hasNext()){
//                Map.Entry<Object,Object> entry =cursor1.next();
//                Object valueSet = entry.getValue();
//                count1+=Integer.parseInt(valueSet.toString());
//            }
//            CountClient countClient = new CountClient();
//            countClient.setDay(day);
//            countClient.setValue1(count);
//            countClient.setValue2(count1);
////                System.out.println(JSONObject.toJSONString(countClient));
//            countClients.add(JSONObject.toJSONString(countClient));
//            //关闭cursor
//            cursor.close();
//            c.add(Calendar.DATE, - 1);
//        }
//        return countClients;
    }

    @Override
    public List getLastThreeMonthMoney() {
        return countUtil.getCountLastThreeMonthMoney();
    }


}

