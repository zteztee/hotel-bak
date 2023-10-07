package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import com.zte.springboot_08_ssmp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

//    查询全部
    @GetMapping
    public R getAll() throws Exception {
        return new R(true,roomService.getAll());
    }

//    添加房间种类
    @PostMapping
    public R save(@RequestBody Room room){
        return new R(roomService.save(room)) ;
    }

//    修改房间信息
    @PutMapping
    public R update(@RequestBody Room room){
        return new R(roomService.update(room));
    }

//    删除房间信息
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(roomService.delete(id));
    }

//    通过房间状态查找
    @GetMapping("/s/{id}")
    public R getByStateId(@PathVariable Integer id){
        return new R(true,roomService.getByState(id));
    }
//    通过房间类型查找
    @GetMapping("/t/{id}")
    public R getByTypeId(@PathVariable Integer id){
        return new R(true,roomService.getByType(id));
    }

    @PostMapping ("/type")
    public R getRoomsByType(@RequestBody Roomtype roomtype){return new R(true,roomService.getByTypeChar(roomtype.getType()));}
//    分页查询
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,roomService.getPage(currentPage,pageSize));
    }
}
