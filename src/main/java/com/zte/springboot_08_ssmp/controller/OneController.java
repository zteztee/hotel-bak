package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.service.OneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ones")
@CrossOrigin
public class OneController {

    @Autowired
    OneService oneService;

//    修改人员信息
    @PutMapping
    public R updateByUsernameAndUserPwd(@RequestBody One one){
        return new R(oneService.update(one));
    }

//    查找人员信息
    @GetMapping("/u/{username}/{userpwd}")
    public R getByUsernameAndUserPwd(@PathVariable String username, @PathVariable String userpwd){
        return new R(true,oneService.getByUserNameAndPassWord(username,userpwd));
    }

//    查找所有人员信息
    @GetMapping
    public R getAll(){
        return new R(true, oneService.getAll());
    }

//    分页查找
    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,oneService.getPage(currentPage,pageSize));
    }
}
