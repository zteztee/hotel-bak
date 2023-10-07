package com.zte.springboot_08_ssmp.service;

import com.zte.springboot_08_ssmp.domain.All;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;

import java.util.List;

public interface AllService {

//    添加个体记录
    public Boolean addOne(One one);

    //    通过pid查找allid
    public All findByOneId(Integer id);

//    通过tid查找allid
    public All findByTeamId(Integer id);

//    添加团体记录
    Boolean addTeam(Team team);

    //    查找所有记录id
    public List<All> findAll();

//    删除个体记录id
    public Boolean delOneById(Integer id);

//    删除团体记录id
    public Boolean delTeamById(Integer id);


}
