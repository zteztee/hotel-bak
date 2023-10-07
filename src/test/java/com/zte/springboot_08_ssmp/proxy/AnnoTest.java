package com.zte.springboot_08_ssmp.proxy;

import com.zte.springboot_08_ssmp.service.OneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnnoTest {

    @Autowired
    OneService oneService;
    @Test
    public void test1(){

        oneService.getAll();
    }
}
