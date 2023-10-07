package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.ClientStayRoomList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClientStayRoomListMapper extends BaseMapper<ClientStayRoomList> {
    @Insert("Insert into tbl_client_stay_roomList(id,roomnum,csid,aid) values(null,#{roomnum},#{csid},#{aid})")
    public int insert(String roomnum, Integer csid,Integer aid);


    @Select("select roomnum from tbl_client_stay_roomList where csid=#{id}")
    public List<ClientStayRoomList> selectRoomsBycsId(Integer id);
    @Delete("delete from tbl_client_stay_roomList where csid=#{id}")
    public int deleteByCsId(Integer id);
}
