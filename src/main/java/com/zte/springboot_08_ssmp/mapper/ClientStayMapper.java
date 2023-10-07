package com.zte.springboot_08_ssmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.domain.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClientStayMapper extends BaseMapper<ClientStay> {

//    保存用户登录入住记录
    @Insert("insert into tbl_client_stay(id,aid,staytime,dateout,days,times,payneed,paytype_id) values(null,#{all.id},#{staytime},#{dateout},#{days},#{times},#{payneed},#{paytype.id})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int insert(ClientStay clientStay);

//      查询用户登录入住记录
    @ResultMap("resultmap99")
    @Select("select * from tbl_client_stay where aid=#{id}")
    public List<ClientStay> selectByAid(Integer id);

//    删除用户登录记录
    @Delete("delete from tbl_client_stay where id=#{id}")
    public int del(Integer id);

//    查找所有用户入住记录
    @Results(id = "resultmap99",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "aid",property = "all.id"),
            @Result(column = "staytime",property = "staytime"),
            @Result(column = "dateout",property = "dateout"),
            @Result(column = "days",property = "days"),
            @Result(column = "times",property = "times"),
            @Result(column = "payneed",property = "payneed"),
            @Result(column = "paytype_id",property = "paytype.id"),
//            @Result(
//                    property = "roomList",
//                    column = "id",
//                    javaType = List.class,
//                    many = @Many(select = "com.zte.springboot_08_ssmp.mapper.RoomMapper.selectByCSId")
//            )
    })
    @Select("SELECT * FROM tbl_client_stay")
    public List<ClientStay> selectAll();

//    通过记录id获取退房时间
    @Select("select dateout from tbl_client_stay where id=#{id}")
    @Result(column = "dateout",property = "dateout")
    public ClientStay selectDateOutById(Integer id);
    @ResultMap("resultmap99")
    @Select("select * from tbl_client_stay limit #{currentPage},#{pageSize}")
    List<ClientStay> selectByPage(int currentPage, int pageSize);
}
