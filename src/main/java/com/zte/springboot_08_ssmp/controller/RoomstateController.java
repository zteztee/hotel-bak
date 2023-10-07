package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Roomstate;
import com.zte.springboot_08_ssmp.service.RoomstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rooms/s")
@RestController
@CrossOrigin
public class RoomstateController {

    @Autowired
    RoomstateService roomStateService;

//    获取所有状态集合
    @GetMapping
    public R getAll(){
        return new R(true,roomStateService.getAll());
    }

//    增加状态
    @PutMapping
    public R add(@RequestBody Roomstate roomState){
        return new R(roomStateService.save(roomState));
    }

//    删除状态
    @DeleteMapping("{id}")
    public R del(@PathVariable Integer id){return new R(roomStateService.removeById(id));}
}
