package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.mapper.ClientStayMapper;
import com.zte.springboot_08_ssmp.mapper.CountClientMapper;
import com.zte.springboot_08_ssmp.mapper.PreMapper;
import com.zte.springboot_08_ssmp.mapper.RoomMapper;
import com.zte.springboot_08_ssmp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private CountClientMapper countClientMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ClientStayMapper clientStayMapper;
    @Autowired
    private PreMapper preMapper;

    @Override
    public Boolean save(Room room) {

        return roomMapper.add(room) >0;
    }

    @Override
    public Boolean updateById(Integer id) {
        return roomMapper.updateById(id)>0;
    }

    @Override
    public List<Room> getByCSId(Integer id) {
        return roomMapper.selectByCSId(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<Room> getAll() throws Exception {
        List<Room> rooms = roomMapper.selectAll();
        for (Room room:rooms){
            if (room.getClientStay()!=null) {
                Integer count = countClientMapper.selectRoomnumCount(room.getRoomnum()).getCount2();
                room.setCount(count);
                System.out.println("+++++"+room.getClientStay().getId());
                        if (clientStayMapper.selectDateOutById(room.getClientStay().getId())!=null){
                            Date dateout = clientStayMapper.selectDateOutById(room.getClientStay().getId()).getDateout();
                            Date date = new Date();
                            System.out.println(date + "++++++++" + dateout);
                            if (dateout.getTime() < date.getTime()) {
                                room.getClientStay().setId(null);
                                room.getRoomState().setId(1);
                                roomMapper.updateById(room.getId());
                            }
                    } else {
                            room.getRoomState().setId(1);
                            roomMapper.updateById(room.getId());}

            } else if (room.getPre()!=null) {
                System.out.println("+++++"+room.getPre().getId());
                if (preMapper.selectComeTimeById(room.getPre().getId())!=null){
                    Date cometime = preMapper.selectComeTimeById(room.getPre().getId()).getCometime();
                    Date date = new Date();
                    if (cometime.getTime() < date.getTime()) {
                        room.getPre().setId(null);
                        room.getRoomState().setId(1);
                        roomMapper.updatePreById(room.getId());
                    }
                } else {
                    room.getRoomState().setId(1);
                    roomMapper.updatePreById(room.getId());}
            } else {
                        if (room.getRoomState().getId()==5 ||room.getRoomState().getId()==4){
                            room.getRoomState().setId(1);
                            roomMapper.updateById(room.getId());
                            roomMapper.updatePreById(room.getId());
                        }
                    }
                }
        return rooms;
    }

    @Override
    public List<Room> getByState(Integer id) {
        List<Room> rooms = roomMapper.getByState(id);
        for (Room room:rooms) {
            Integer count = countClientMapper.selectRoomnumCount(room.getRoomnum()).getCount2();
            room.setCount(count);
        }return rooms;

    }

    @Override
    public List<Room> getByType(Integer id) {
        List<Room> rooms = roomMapper.getByType(id);
        for (Room room:rooms) {
            Integer count = countClientMapper.selectRoomnumCount(room.getRoomnum()).getCount2();
            room.setCount(count);
        }return rooms;
    }

    @Override
    public Boolean delete(Integer id) {
        return roomMapper.deleteById(id)>0;
    }

    @Override
    public Boolean update(Room room) {
        return roomMapper.update(room)>0;
    }

    @Override
    public List<Room> getPage(int currentPage, int pageSize) {
        checkRooms();
        List<Room> rooms = roomMapper.selectByPage(currentPage,pageSize);
        for (Room room:rooms) {
            Integer count = countClientMapper.selectRoomnumCount(room.getRoomnum()).getCount2();
            room.setCount(count);
        }return rooms;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void checkRooms(){
        System.out.println("测试前端调用后端服务器");
        List<Room> rooms = roomMapper.selectAll();
        for (Room room:rooms){
            if (room.getClientStay()!=null) {
                if (clientStayMapper.selectDateOutById(room.getClientStay().getId())!=null){
                    Date dateout = clientStayMapper.selectDateOutById(room.getClientStay().getId()).getDateout();
                    Date date = new Date();
                    if (dateout.getTime() < date.getTime()) {
                        room.getClientStay().setId(null);
                        room.getRoomState().setId(1);
                        roomMapper.updateById(room.getId());}} else {
                    room.getRoomState().setId(1);
                    roomMapper.updateById(room.getId());}
            }else if (room.getPre()!=null) {
                if (preMapper.selectComeTimeById(room.getPre().getId())!=null){
                    Date cometime = preMapper.selectComeTimeById(room.getPre().getId()).getCometime();
                    Date date = new Date();
                    if (cometime.getTime() < date.getTime()) {
                        room.getPre().setId(null);
                        room.getRoomState().setId(1);
                        roomMapper.updatePreById(room.getId());}} else {
                    room.getRoomState().setId(1);
                    roomMapper.updatePreById(room.getId());}}  else {
                if (room.getRoomState().getId()==5 ||room.getRoomState().getId()==4){
                    room.getRoomState().setId(1);
                    roomMapper.updateById(room.getId());
                    roomMapper.updatePreById(room.getId());}}}}

    @Override
    public List<Room> getByTypeChar(String type) {
        List<Room> rooms = roomMapper.getByTypeChar(type);
        for (Room room:rooms) {
            Integer count = countClientMapper.selectRoomnumCount(room.getRoomnum()).getCount2();
            room.setCount(count);
        }return rooms;
    }
}
