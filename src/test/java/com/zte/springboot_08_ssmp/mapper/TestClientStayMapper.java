package com.zte.springboot_08_ssmp.mapper;

import com.zte.springboot_08_ssmp.domain.All;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestClientStayMapper {
    @Autowired
    ClientStayMapper clientStayMapper;

    @Test
    void testInsert(){
        ClientStay clientStay = new ClientStay();
        All all = new All();
        all.setId(2);
        clientStay.setAll(all);
        clientStay.setStaytime(new Date());
        clientStay.setDateout(new Date());
        clientStay.setDays(3);
        clientStay.setPayneed(300.00);
        System.out.println(clientStayMapper.insert(clientStay));
    }
}
