package com.zte.springboot_08_ssmp.mapper;

import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.domain.Roomstate;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestRoomMapper {

    @Autowired
    RoomMapper roomMapper;

    @Test
    public void testSelectByCSId(){
        System.out.println(roomMapper.selectByCSId(40));
    }
    @Test
    public void testadd(){
        Room room = new Room();
        Roomstate roomstate = new Roomstate();
        Roomtype roomtype = new Roomtype();
        room.setRoomnum("10086");
        roomstate.setId(2);
        roomtype.setId(2);
        room.setRoomState(roomstate);
        room.setRoomType(roomtype);
        room.setTimemoney(40);
        room.setDaymoney(100);
        System.out.println(roomMapper.add(room));
    }
}
