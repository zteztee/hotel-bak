package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.CountClient;
import com.zte.springboot_08_ssmp.service.CountClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/count")
@CrossOrigin
public class CountClientController {
    @Autowired
    CountClientService countClientService;

//    获取该用户推荐
    @GetMapping("/a/{aid}")
    public  R getBest(@PathVariable Integer aid){return new R(true,countClientService.getBest(aid));}

//    获取所有入住次数
    @GetMapping
    public R getAllCount(){
        return new R(true,countClientService.getAllCount());
    }
//    获取aid的所有入住记录
    @GetMapping("/aid/{aid}")
    public R getCountByAid(@PathVariable Integer aid){
        return new R(true,countClientService.getCountByAid(aid));
    }

    @GetMapping("/roomnum/{roomnum}")
    public R getCountByRoomnum(@PathVariable String roomnum){
        return new R(true,countClientService.getCountByRoomnum(roomnum));
    }

    @GetMapping("/roomtype/{roomtype}")
    public R getCountByRoomtype(@PathVariable String roomtype){
        return new R(true,countClientService.getCountByRoomtype(roomtype));
    }

    @GetMapping("/roomtype1")
    public R getCountByRoomtype() throws Exception {
        return new R(true,countClientService.getOrderByRoomtype());
    }

    @GetMapping("/visits/{username}")
    public R addWeekCount(@PathVariable String username ) throws Exception{
        countClientService.addWeekDetailCount(username);
        return new R(true,"当前天数访问量+1");
    }

    @GetMapping("/dayscount")
    public R getDaysCount() throws Exception{
        return new R(true,countClientService.getDaysCount());
    }

    @GetMapping("/threemonthmoney")
    public R getLastThreeMonthMoney() throws Exception{
        return new R(true,countClientService.getLastThreeMonthMoney());
    }
}
