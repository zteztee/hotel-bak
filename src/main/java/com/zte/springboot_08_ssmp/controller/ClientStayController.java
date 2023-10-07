package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.service.IClientStayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientStayController {

    @Autowired
    IClientStayService clientStayService;

//    添加客户入住记录
    @PutMapping
    public R add(@RequestBody ClientStay clientStay,@RequestBody Integer logtype) throws Exception {
        if (logtype==2){
            return new R(clientStayService.saveOne(clientStay));
        }
        if (logtype==3){
            return new R(clientStayService.saveTeam(clientStay));
        }
        else {
            return new R(clientStayService.saveTeam(clientStay));
        }
    }

//  查找所有用户入住记录
    @GetMapping
    public R getAll(){
        return new R(true,clientStayService.getAll());
    }

//    查找用户入住记录
    @GetMapping("/{aid}")
    public R getByOneId(@PathVariable Integer aid){
        return new R(true,clientStayService.getClientStayByAid(aid));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,clientStayService.getPage(currentPage,pageSize));
    }

//    删除当前入住记录
    @DeleteMapping("/{id}")
    public R del(@PathVariable Integer id) throws Exception {
        return new R(clientStayService.remove(id));
    }
}
