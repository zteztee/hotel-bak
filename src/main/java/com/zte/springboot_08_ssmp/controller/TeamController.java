package com.zte.springboot_08_ssmp.controller;


import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Team;
import com.zte.springboot_08_ssmp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamController {
    @Autowired
    TeamService teamService;

    //    修改团队信息
    @PutMapping
    public R updateByUsernameAndUserPwd(@RequestBody Team team){
        return new R(teamService.update(team));
    }

    //    查找人员信息
    @GetMapping("/u/{username}/{userpwd}")
    public R getByUsernameAndUserPwd(@PathVariable String username, @PathVariable String userpwd){
        return new R(true,teamService.getByUserNameAndPassWord(username,userpwd));
    }

    //    查找所有人员信息
    @GetMapping
    public R getAll(){
        return  new R(true,teamService.getAll());
    }

    //    分页查找
    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,teamService.getPage(currentPage,pageSize));
    }
}
