package com.zte.springboot_08_ssmp.sevice;

import com.zte.springboot_08_ssmp.domain.All;
import com.zte.springboot_08_ssmp.service.AllService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class testall {

    @Autowired
    AllService allService;
    @Test
    public void test1(){
        All byOneId = allService.findByOneId(1);
        System.out.println((Integer)byOneId.getId() );
    }
}
