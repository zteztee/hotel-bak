package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.All;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AllMapper extends BaseMapper<All> {

    @Insert("insert into tbl_all(pid,username) values(#{id},#{username})")
    public int addOne(One one);

    @Select("select * from tbl_one where id=#{id}")
    public One findOneByAid(Integer id);
    @Insert("insert into tbl_all(tid,username) values(#{id},#{username})")
    public int addTeam(Team team);

    @Results(id = "resultMap",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "pid",property = "one1.id"),
            @Result(column = "tid",property = "team.id"),
            @Result(column = "username",property = "username")
    })
    @Select("select * from tbl_all")
    public List<All> getAll();


    @Delete("delete from tbl_all where pid=#{id}")
    public int delOneById(Integer id);


    @Delete("delete from tbl_all where tid=#{id}")
    public int delTeamById(Integer id);

    @Select("select id from tbl_all where pid=#{id}")
    public All selectByPid(Integer id);

    @Select("select id from tbl_all where tid=#{id}")
    public All selectByTid(Integer id);

}
