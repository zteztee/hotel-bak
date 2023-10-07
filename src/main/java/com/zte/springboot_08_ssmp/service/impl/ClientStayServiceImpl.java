package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.domain.ClientStayRoomList;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.mapper.*;
import com.zte.springboot_08_ssmp.service.IClientStayService;
import com.zte.springboot_08_ssmp.service.impl.util.CountUtil;
import com.zte.springboot_08_ssmp.service.impl.util.HBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientStayServiceImpl extends ServiceImpl<ClientStayMapper, ClientStay> implements IClientStayService {
    @Autowired
    CountUtil countUtil;
    @Autowired
    ClientStayRoomListMapper clientStayRoomListMapper;
    @Autowired
    ClientStayMapper clientStayMapper;
    @Autowired
    AllMapper allMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    CountClientMapper countClientMapper;


//    通过用户aid获取入住信息
    @Override
    public List<ClientStay> getClientStayByAid(Integer aid) {
            //getClientStayByAid
            List<ClientStay> clients = clientStayMapper.selectByAid((aid));
            if (clients!=null){
                for(ClientStay clientStay : clients){
                    List<Room> roomlist = roomMapper.selectByCSId(clientStay.getId());
                    if (roomlist!=null){
                        List<String> roomNum =new ArrayList<>();
                        for (Room room:roomlist){
                            roomNum.add(room.getRoomnum());
                        }
                        clientStay.setRoomList(roomNum);
                    }
                }
                return clients;
            }
            else {
                return null;
            }
    }
//    通过团体用户id获

//    删除当前记录id
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean remove(Integer id) throws Exception {
        int i = roomMapper.updateById(id);
        if (i>=0){
            i = clientStayRoomListMapper.deleteByCsId(id);
            if (i>=0){
                return clientStayMapper.del(id)>0;
            }
            else throw new RuntimeException("删除失败了代码有问题呢");
        }
        else throw new RuntimeException("删除失败了");
    }



//        Integer days = clientStay.getDays();

//        for (Room room : rooms) {
//            payneed += (double) (room.getDaymoney()*days+room.getTimemoney());
//        }
//        clientStay.setPayneed(payneed);
//    新增用户记录
@Override
@Transactional(rollbackFor=Exception.class)
public Boolean saveOne(ClientStay clientStay) throws Exception {
        List<String> rooms = clientStay.getRoomList();
        int id = clientStayMapper.insert(clientStay);
        if (id>0){
            if (clientStay.getId()!=null){
//                One one = allMapper.findOneByAid(clientStay.getAll().getId());
                String username = clientStay.getAll().getUsername();
                countUtil.insertCountMoney(clientStay.getPayneed(),username);
                int count =0;
                String roomtype= null;
                for (String roomnum :rooms){
                    roomtype = roomMapper.getroomtypeByroomnum(roomnum).getType();
                    countClientMapper.insert(roomnum,clientStay.getAll().getId(),roomtype);
                    count = roomMapper.setCSIdWithRoomId(clientStay.getId(),roomnum);
                    clientStayRoomListMapper.insert(roomnum,clientStay.getId(),clientStay.getAll().getId());
                    countUtil.insertCountOrder(username);
                    countUtil.insertCountRoomtype(roomtype,roomnum,clientStay);
//                    HBaseUtils.getInstance();
//                    try {
//                        Map<String, String> cloumns = new HashMap<String, String>();
//                        if (one.getOnesex()==0){
//                            cloumns.put("sex", "男");    //列名和值
//                        }
//                        else {cloumns.put("sex","女");}
//                        cloumns.put("age", one.getOneage().toString());    //列名和值
//                        cloumns.put("roomtype",roomtype);
//                        //往表中的第1row的info族中增加了名为name、age的列并给出数据
//                        HBaseUtils.put("person", clientStay.getId().toString(), "info", cloumns);
//                        System.out.println("增加成功");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("增加失败");
//                    } finally {
//                        HBaseUtils.close();
//                    }

//                    if (stringRedisTemplate.opsForHash().hasKey("房间类型入住统计", roomtype)==false){
//                        redisTemplate.opsForHash().put("房间类型入住统计",roomtype,1);
//                    }
//                    else {
//                        redisTemplate.opsForHash().increment("房间类型入住统计",roomtype,1);
//                    }
//                    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
//                    String now = format.format(new Date());
//                    String nows = now+":"+username;
//                    if (stringRedisTemplate.opsForHash().hasKey("每日下单量统计", nows)==false){
//                        System.out.println(nows+"1");
//                        redisTemplate.opsForHash().put("每日下单量统计",nows,1);
//                    }else {
//                        System.out.println(nows+"2");
//                        redisTemplate.opsForHash().increment("每日下单量统计",nows,1);
//                    }
                }
                if (count>0){
                    return roomMapper.setStateWithStayId(clientStay.getId())>0;
                }
                else {
                    throw new RuntimeException("可能没有该房间号或者用户注册id没有找到");
                }
            }
            else {throw new RuntimeException("重试一下");}
        }
        else {
            throw new RuntimeException("保存失败可能是数据错误");
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean saveTeam(ClientStay clientStay) throws Exception {
        List<String> rooms = clientStay.getRoomList();
        int id = clientStayMapper.insert(clientStay);
        if (id>0){
            if (clientStay.getId()!=null){
                String username = clientStay.getAll().getUsername();
                countUtil.insertCountMoney(clientStay.getPayneed(),username);
                int count =0;
                String roomtype= null;
                for (String roomnum :rooms){
                    roomtype = roomMapper.getroomtypeByroomnum(roomnum).getType();
                    countClientMapper.insert(roomnum,clientStay.getAll().getId(),roomtype);
                    count = roomMapper.setCSIdWithRoomId(clientStay.getId(),roomnum);

                    clientStayRoomListMapper.insert(roomnum,clientStay.getId(),clientStay.getAll().getId());
                    countUtil.insertCountOrder(username);
                    countUtil.insertCountRoomtype(roomtype,roomnum,clientStay);

//                    if (stringRedisTemplate.opsForHash().hasKey("房间类型入住统计", roomtype)==false){
//                        redisTemplate.opsForHash().put("房间类型入住统计",roomtype,1);
//                    }
//                    else {
//                        redisTemplate.opsForHash().increment("房间类型入住统计",roomtype,1);
//                    }
//                    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
//                    String now = format.format(new Date());
//                    String nows = now+":"+username;
//                    if (stringRedisTemplate.opsForHash().hasKey("每日下单量统计", nows)==false){
//                        System.out.println(nows+"1");
//                        redisTemplate.opsForHash().put("每日下单量统计",nows,1);
//                    }else {
//                        System.out.println(nows+"2");
//                        redisTemplate.opsForHash().increment("每日下单量统计",nows,1);
//                    }
                }
                if (count>0){
                    return roomMapper.setStateWithStayId(clientStay.getId())>0;
                }
                else {
                    throw new RuntimeException("可能没有该房间号或者用户注册id没有找到");
                }
            }
            else {throw new RuntimeException("重试一下");}
        }
        else {
            throw new RuntimeException("保存失败可能是数据错误");
        }
    }


//    获取所有用户登录记录
    @Override
    public List<ClientStay> getAll() {
        List<ClientStay> clientStays = clientStayMapper.selectAll();
        for (ClientStay clientStay : clientStays){
            List<String> roomsnums = new ArrayList();
            List<ClientStayRoomList> clientStayRoomLists = clientStayRoomListMapper.selectRoomsBycsId(clientStay.getId());
            for(ClientStayRoomList clientStayRoomList :clientStayRoomLists){
                roomsnums.add(clientStayRoomList.getRoomnum());
            }
                clientStay.setRoomList(roomsnums);
            }
        return clientStays;
    }

    public List<ClientStay> getPage(int currentPage, int pageSize) {
        List<ClientStay> clientStays = clientStayMapper.selectByPage(currentPage, pageSize);
        for (ClientStay clientStay : clientStays){
            List<String> roomsnums = new ArrayList();
            List<ClientStayRoomList> clientStayRoomLists = clientStayRoomListMapper.selectRoomsBycsId(clientStay.getId());
            for(ClientStayRoomList clientStayRoomList :clientStayRoomLists){
                roomsnums.add(clientStayRoomList.getRoomnum());
            }
            clientStay.setRoomList(roomsnums);
        }
        return clientStays;
    }
}

