package com.zte.springboot_08_ssmp.sevice;

import com.zte.springboot_08_ssmp.domain.Room;
import com.zte.springboot_08_ssmp.service.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    void save(){
        Room room = new Room();
//        room.setRoomnum(130);
        room.getRoomState().getState();
        room.setDaymoney(90);
        room.setTimemoney(10);

        System.out.println(roomService.save(room));

    }

    @Test
    void getAll(){
//        List<Room> all = roomService.getAll();
//        for (Room room :
//                all) {
//            System.out.println(room);
//        }
    }

    @Test
    void getByState(){
        List<Room> byState = roomService.getByState(2);
        for (Room room:byState
             ) {
            System.out.println(room);

        }
    }

}
