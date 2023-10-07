package com.zte.springboot_08_ssmp.sevice;


import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.service.IClientStayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testclent {
    @Autowired
    IClientStayService iClientStayService;
    @Test
    public void test1(){
//        ClientStay clientStayByTid = iClientStayService.getClientStayByTid(1);
//        System.out.println(clientStayByTid);
    }
}
