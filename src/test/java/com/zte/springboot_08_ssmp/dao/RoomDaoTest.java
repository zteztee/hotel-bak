package com.zte.springboot_08_ssmp.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.mapper.RoomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomDaoTest {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    void testGetById(){
        System.out.println(roomMapper.selectById(2));
    }
    @Test
    void testSave(){

    }
    @Test
    void testUpdate() {
    }
    @Test
    void testDelete() {
        roomMapper.deleteById(3);
    }
    @Test
    void testGetAll() {
    }
    @Test
    void testGetPage() {
        IPage page = new Page(1,5);
        roomMapper.selectPage(page,null);
    }
    @Test
    void  testGetBy(){
        QueryWrapper<Room> qw = new QueryWrapper<>();
        qw.like("roomnum","10");
        roomMapper.selectList(qw);
    }
    @Test
    void  testGetBy2(){
        LambdaQueryWrapper<Room> qw = new LambdaQueryWrapper<>();
        qw.like(Room::getRoomnum,"10");
        roomMapper.selectList(qw);
    }

}
