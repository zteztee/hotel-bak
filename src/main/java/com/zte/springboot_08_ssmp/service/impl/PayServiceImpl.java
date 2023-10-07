package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Pay;
import com.zte.springboot_08_ssmp.domain.Team;
import com.zte.springboot_08_ssmp.mapper.*;
import com.zte.springboot_08_ssmp.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {

    @Autowired
    OneMapper oneMapper;
    @Autowired
    ClientStayMapper clientStayMapper;
    @Autowired
    AllMapper allMapper;
    @Autowired
    PayMapper payMapper;
    @Autowired
    TeamMapper teamMapper;

    @Override
    public Pay findByOneUsernameAndPassword(String username, String userpwd) {
        One one = oneMapper.getByUserNameAndPassWord(username, userpwd);
        Integer id = allMapper.selectByPid(one.getId()).getId();
        return payMapper.selectByAid(id);
    }
    @Override
    public Pay findByTeamUsernameAndPassword(String username, String userpwd) {
        Team team = teamMapper.getByUserNameAndPassWord(username,userpwd);
        Integer id = allMapper.selectByPid(team.getId()).getId();
        return payMapper.selectByAid(id);
    }

    @Override
    public Boolean save1(Pay pay) {
        return payMapper.insert(pay)>0;
    }

    @Override
    public List<Pay> findAll() {
        return payMapper.selectAll();
    }


}
