package com.zte.springboot_08_ssmp.service;

import com.zte.springboot_08_ssmp.domain.Room;

import java.util.List;

public interface RoomService {

 /*添加房间记录
 * */
    public Boolean save(Room room);

//    将stay_id设为空
    public Boolean updateById(Integer id);

//  通过csid查找房间信息
    public List<Room> getByCSId(Integer id);

//  查找所有的房间信息
    public List<Room> getAll() throws Exception;

//    通过状态条件查询所有房间信息
    public List<Room> getByState(Integer id);

//    通过类型条件查询所有房间信息
    public List<Room> getByType(Integer id);

//    删除一条房间信息
    public Boolean delete(Integer id);

//    修改一条房间信息
    public Boolean update(Room room);

//      分页查找
    List<Room> getPage(int currentPage, int pageSize);

    public void checkRooms();

    public List<Room> getByTypeChar(String type);

}
