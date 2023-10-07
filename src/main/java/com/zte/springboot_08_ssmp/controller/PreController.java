package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Pre;
import com.zte.springboot_08_ssmp.service.PreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pres")
public class PreController {
    @Autowired
    PreService preService;

    @PutMapping
//    新增预定记录
    public R save(@RequestBody Pre pre) throws Exception {
        return new R(preService.add(pre));
    }

    @GetMapping
//    查找所有订单记录
    public R getAll(){
        return new R(true,preService.findAll());
    }

    @GetMapping("{id}")
//    查找自己订单记录
    public R getByAid(Integer id){
        return new R(true,preService.findByAid(id));
    }
}
