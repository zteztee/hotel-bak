package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Pay;
import com.zte.springboot_08_ssmp.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pays")
@RestController
@CrossOrigin
public class PayController {

    @Autowired
    PayService payService;


//  添加支付记录
    @PutMapping
    public R add(@RequestBody Pay pay){
        return new R(payService.save1(pay));
    }

//    查找散客支付记录
    @GetMapping("/o/{username}/{userpwd}")
    public R findByOneUsernameAndPassword(@PathVariable String username,@PathVariable String userpwd){
        return new R(true,payService.findByOneUsernameAndPassword(username,userpwd));
    }

//    查找团体支付记录
    @GetMapping("/t/{username}/{userpwd}")
    public R findByTeamUsernameAndPassword(@PathVariable String username,@PathVariable String userpwd){
        return new R(true,payService.findByTeamUsernameAndPassword(username,userpwd));
    }
//    查找所有支付记录
    @GetMapping
    public R findAll(){
        return new R(true,payService.findAll());
    }
}
