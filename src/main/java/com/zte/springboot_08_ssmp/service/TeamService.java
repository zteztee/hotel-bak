package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zte.springboot_08_ssmp.domain.Team;

import java.util.List;

public interface TeamService {
    //    添加一个团体记录
    public Boolean save(Team team);

    //    查找一个团体记录
    public Team getByUserNameAndPassWord(String username,String userpwd);

    //     查找所有团体记录
    public List<Team> getAll();

    //   删除某个团体记录
    public Boolean delete(Integer id);

    //    修改某个团体记录
    public Boolean update(Team team);

    //
    IPage<Team> getPage(int currentPage, int pageSize);
}
