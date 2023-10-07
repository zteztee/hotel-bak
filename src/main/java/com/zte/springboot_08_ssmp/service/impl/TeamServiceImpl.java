package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;
import com.zte.springboot_08_ssmp.mapper.AllMapper;
import com.zte.springboot_08_ssmp.mapper.OneMapper;
import com.zte.springboot_08_ssmp.mapper.TeamMapper;
import com.zte.springboot_08_ssmp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    AllMapper allMapper;

    @Autowired
    OneMapper oneMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean save(Team team) {
        if (oneMapper.getUserName(team.getUsername())==null && teamMapper.getUserName(team.getUsername())==null){
            if (teamMapper.save(team)>0){
                if (team.getId()!=null){
                    return allMapper.addTeam(team)>0;
                }
                else {
                    throw new RuntimeException("重试一下");
                }
            }
//            运行错误
            throw new RuntimeException("运行错误");
        }
//        用户账户已存在
        throw new RuntimeException("用户账户已存在");
    }

    @Override
    public Team getByUserNameAndPassWord(String username, String userpwd) {
        int allid = 0;
        Team team = teamMapper.getByUserNameAndPassWord(username,userpwd);
        if (team!=null){
            allid = allMapper.selectByPid(team.getId()).getId();
            team.setAllid(allid);
            return team;
        }
        return null;
    }

    @Override
    public List<Team> getAll() {
        return teamMapper.selectList(null);
    }

    @Override
    public Boolean delete(Integer id) {
        return teamMapper.deleteById(id)>0;
    }

    @Override
    public Boolean update(Team team) {
        return teamMapper.updateByUserNameAndPassWord(team) >0;
    }

    @Override
    public IPage<Team> getPage(int currentPage, int pageSize) {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Team.class, info->!info.getColumn().equals("allid"));
        IPage page = new Page(currentPage,pageSize);
        return teamMapper.selectPage(page,queryWrapper);
    }
}
