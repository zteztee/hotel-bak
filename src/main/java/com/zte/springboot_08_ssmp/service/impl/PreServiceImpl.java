package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.domain.Pre;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.mapper.PreMapper;
import com.zte.springboot_08_ssmp.mapper.RoomMapper;
import com.zte.springboot_08_ssmp.service.PreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreServiceImpl extends ServiceImpl<PreMapper, Pre> implements PreService {

    @Autowired
    PreMapper preMapper;
    @Autowired
    RoomMapper roomMapper;


    @Override
    public List<Pre> findByAid(Integer aid) {
            //getClientStayByAid
      List<Pre> pres = preMapper.selectByAid((aid));
      if (pres!=null){
          for(Pre pre : pres){
              List<Room> roomlist = roomMapper.selectByPreId(pre.getId());
              if (roomlist!=null){
                  List<String> roomNum =new ArrayList<>();
                  for (Room room:roomlist){
                      roomNum.add(room.getRoomnum());
                  }
                  pre.setRoomList(roomNum);
              }
          }
          return pres;
      }
      else {
          return null;
      }
    }

    //    新增用户记录
    //        for (Room room : rooms) {
//            payneed += (double) (room.getDaymoney()*days+room.getTimemoney());
//        }
//        clientStay.setPayneed(payneed);
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean add(Pre pre) throws Exception{
        List<String> rooms = pre.getRoomList();
            int id = preMapper.insert(pre);
            System.out.println("+++++++++++"+id);
            if (id>0){
                if (pre.getId()!=null){
                    int count =0;
                    for (String roomnum :rooms){
                        count = roomMapper.setPreIdWithRoomId(pre.getId(),roomnum);}
                    if (count>0){
                        return roomMapper.setStateWithPreId(pre.getId())>0;}
                    else {
                        throw new RuntimeException("可能没有该房间号或者用户注册id没有找到");}
                }
                else {throw new RuntimeException("重试一下");}}
            else {
                throw new RuntimeException("保存失败可能是数据错误");}}
    //    获取所有用户登录记录
    @Override
    public List<Pre> findAll() {
        List<Pre> pres = preMapper.selectAll();
        for (Pre pre : pres){
            List<String> roomnums = new ArrayList();
            List<Room> rooms = roomMapper.selectRoomnumByPreId(pre.getId());
            System.out.println("+++++++++"+rooms);
            if (rooms!=null){
                for (Room room:rooms){
                    roomnums.add(room.getRoomnum());
                }
                pre.setRoomList(roomnums);
            }else {
                return pres;
            }
        }   return pres;
    }
}
