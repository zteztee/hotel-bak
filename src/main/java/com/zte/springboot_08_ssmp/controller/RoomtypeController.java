package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import com.zte.springboot_08_ssmp.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rooms/t")
@RestController
@CrossOrigin
public class RoomtypeController {
    @Autowired
    RoomtypeService roomTypeService;

    //    获取所有类型集合
    @GetMapping
    public R getAll(){
        return new R(true,roomTypeService.getAll());
    }

    //    增加类型
    @PutMapping
    public R add(@RequestBody Roomtype roomState){
        return new R(roomTypeService.save(roomState));
    }

    //    删除类型
    @DeleteMapping("{id}")
    public R del(@PathVariable Integer id){return new R(roomTypeService.removeById(id));}

}
