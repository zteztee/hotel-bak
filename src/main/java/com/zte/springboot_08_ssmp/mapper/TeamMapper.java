package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Team;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    @Select("select * from tbl_team where username=#{username} and userpwd=#{userpwd}")
    Team getByUserNameAndPassWord(String username, String userpwd);

    @Update("update tbl_team set teamname=#{teamname},teamemil=#{teamemil},teamprimary=#{teamprimary},teamnumber=#{teamnumber},teamlogin=#{teamlogin} where username=#{username} and userpwd=#{userpwd} ")
    public int updateByUserNameAndPassWord(Team team);
//    查找username是否为空
    @Select("SELECT username FROM tbl_team WHERE username=#{username}")
    public String getUserName(String username);

    @Insert("insert into tbl_team(teamname,teamemil,teamprimary,teamnumber,teamlogin,username,userpwd) values(#{teamname},#{teamemil},#{teamprimary},#{teamnumber},#{teamlogin},#{username},#{userpwd})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int save(Team team);
}
