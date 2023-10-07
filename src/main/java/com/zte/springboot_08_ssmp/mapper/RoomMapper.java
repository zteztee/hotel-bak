package com.zte.springboot_08_ssmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.domain.Roomstate;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {

//    将房间入住信息设为空
    @Update("update tbl_room set stay_id = null , room_state_id=1 where id=#{id}")
    public int updateById(Integer id);

    @Update("update tbl_room set pre_id = null , room_state_id=1 where id=#{id}")
    public int updatePreById(Integer id);

//    根据id查找Room
    @Results(id = "r1",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "roomnum",property = "roomnum"),
            @Result(column = "room_state_id",property = "roomState.id"),
            @Result(column = "state",property = "roomState.state"),
            @Result(column = "room_type_id",property = "roomType.id"),
            @Result(column = "type",property = "roomType.type"),
            @Result(column = "timemoney",property = "timemoney"),
            @Result(column = "daymoney",property = "daymoney"),
            @Result(column = "stay_id",property = "clientStay.id"),
            @Result(column = "pre_id",property = "pre.id")
    })
    @Select("select * from tbl_room r join tbl_roomstate rs on rs.id = r.room_state_id join tbl_roomtype rt on rt.id=r.room_type_id where stay_id=#{id}")
    List<Room> selectByCSId(Integer id);

    @ResultMap("r1")
    @Select("select * from tbl_room r join tbl_roomstate rs on rs.id = r.room_state_id join tbl_roomtype rt on rt.id=r.room_type_id where pre_id=#{id}")
    List<Room> selectByPreId(Integer id);

    @Insert("INSERT INTO tbl_room(roomnum,room_state_id,room_type_id,timemoney,daymoney,stay_id) VALUES(#{roomnum},#{roomState.id},#{roomType.id},#{timemoney},#{daymoney},#{clientStay.id})")
    int add(Room room);

    @Select("select * from tbl_room rm  join tbl_roomtype rmt on rm.room_type_id=rmt.id join tbl_roomstate rms on rm.room_state_id=rms.id where rm.room_state_id = #{id}")
    @ResultMap("r1")
    List<Room> getByState(Integer id);

    @Select("select * from tbl_room rm  join tbl_roomstate rms on rm.room_state_id=rms.id join tbl_roomtype rmt on rm.room_type_id=rmt.id where rm.room_type_id = #{id}")
    @ResultMap("r1")
    List<Room> getByType(Integer id);


    @ResultMap("r1")
    @Select("select * from tbl_room rm join tbl_roomstate rms on rm.room_state_id=rms.id join tbl_roomtype rmt on rm.room_type_id=rmt.id")
    List<Room> selectAll();

//    修改所有操作
    @Update("Update tbl_room set roomnum=#{roomnum},room_state_id=#{roomState.id},room_type_id=#{roomType.id},timemoney=#{timemoney},daymoney=#{daymoney},stay_id=#{clientStay.id},pre_id=#{pre.id} where id=#{id}")
    public int update(Room room);

//    分页查找
    @ResultMap("r1")
    @Select("select * from tbl_room rm join tbl_roomstate rms on rm.room_state_id=rms.id join tbl_roomtype rmt on rm.room_type_id=rmt.id limit #{currentPage},#{pageSize}")
    List<Room> selectByPage(int currentPage, int pageSize);

    @Update("UPDATE tbl_room set stay_id=#{csid} WHERE roomnum=#{roomnum}")
    int setCSIdWithRoomId(Integer csid,String roomnum);

    @Update("UPDATE tbl_room set pre_id=#{preid} WHERE roomnum=#{roomnum}")
    int setPreIdWithRoomId(Integer preid,String roomnum);
//    修改用户入住后状态信息
    @Update("update tbl_room set room_state_id = 5 where stay_id=#{csid}")
    int setStateWithStayId(Integer csid);

    @Update("update tbl_room set room_state_id = 4 where pre_id=#{preid}")
    int setStateWithPreId(Integer preid);
    @Select("select roomnum from tbl_room where stay_id=#{id}")
    @Results({
            @Result(column = "roomnum",property = "roomnum")
    })
    List<Room> selectRoomnumByCSId(Integer id);

    @Select("select roomnum from tbl_room where pre_id=#{id}")
    @Results({
            @Result(column = "roomnum",property = "roomnum")
    })
    List<Room> selectRoomnumByPreId(Integer id);

    @Select("SELECT type from tbl_roomtype t JOIN tbl_room r ON r.room_type_id=t.id WHERE r.roomnum=#{roomnum}")
    public Roomtype getroomtypeByroomnum(String roomnum);

    @Select("SELECT * FROM tbl_room rm join tbl_roomstate rms on rm.room_state_id=rms.id JOIN tbl_roomtype rmt on rm.room_type_id=rmt.id WHERE rm.room_type_id=(SELECT id FROM tbl_roomtype WHERE type=#{type})")
    @ResultMap("r1")
    List<Room> getByTypeChar(String type);
}
