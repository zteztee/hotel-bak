package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.CountClient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountClientService extends IService<CountClient> {

    public String getBest(Integer aid);
    public int getAllCount();

    public int getCountByAid(Integer aid);

    public int getCountByRoomnum(String roomnum);
    public int getCountByRoomtype(String roomtype);

    public List getOrderByRoomtype()throws Exception;

    public void addWeekDetailCount(String username)throws Exception;

    public List getDaysCount() throws Exception;

    public List getLastThreeMonthMoney();

//    public List getWeekDetailCount() throws Exception;
}
