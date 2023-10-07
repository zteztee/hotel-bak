package com.zte.springboot_08_ssmp.service.impl;

import com.zte.springboot_08_ssmp.domain.All;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;
import com.zte.springboot_08_ssmp.mapper.AllMapper;
import com.zte.springboot_08_ssmp.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllServiceImpl implements AllService {

    @Autowired
    AllMapper allMapper;


    @Override
    public Boolean addOne(One one) {
        return allMapper.addOne(one)>0;
    }

    @Override
    public All findByOneId(Integer id) {
        return allMapper.selectByPid(id);
    }
    @Override
    public All findByTeamId(Integer id){
        return allMapper.selectByTid(id);
    }

    @Override
    public Boolean addTeam(Team team) {
        return allMapper.addTeam(team)>0;
    }


    @Override
    public List<All> findAll() {
        return allMapper.getAll();
    }

    @Override
    public Boolean delOneById(Integer id) {
        return allMapper.delOneById(id)>0;
    }

    @Override
    public Boolean delTeamById(Integer id) {
        return allMapper.delTeamById(id)>0;
    }
}
