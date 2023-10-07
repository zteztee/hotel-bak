package com.zte.springboot_08_ssmp.controller;

import com.zte.springboot_08_ssmp.controller.utils.R;
import com.zte.springboot_08_ssmp.domain.Adm;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;
import com.zte.springboot_08_ssmp.service.AdmService;
import com.zte.springboot_08_ssmp.service.OneService;
import com.zte.springboot_08_ssmp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("logs")
@CrossOrigin
public class UserController {

    @Autowired
    OneService oneService;
    @Autowired
    AdmService admService;
    @Autowired
    TeamService teamService;

    //    散客用户注册
    @PutMapping("/p")
    public R saveOne(@RequestBody One one) {
        return new R(oneService.save(one));
    }

    //    团体用户注册
    @PutMapping("/t")
    public R saveTeam(@RequestBody Team team) {
        return new R(teamService.save(team));
    }

    //      管理员登录
    @GetMapping("/{username}/{userpwd}")
    public R AdmLog(@PathVariable String username, @PathVariable String userpwd){
        return new R(true,admService.getByUserNameAndPassWord(username,userpwd));
    }

    //    散客用户登录
    public R OneLog(One one) {
        if (oneService.getByUserNameAndPassWord(one.getUsername(), one.getUserpwd()) != null) {
            return new R(true, one);
        }
        //用户登录不成功,账户不存在,或密码错误
        return new R(false);
    }
    //  团体用户登录
    public R TeamLog(Team team){
        if (teamService.getByUserNameAndPassWord(team.getUsername(),team.getUserpwd())!=null){
            return new R(true,team);
        }
        return new R(false);
    }
}
