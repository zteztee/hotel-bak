package com.zte.springboot_08_ssmp.mapper;

import com.zte.springboot_08_ssmp.domain.Adm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAdmMapper {
    @Autowired
    public AdmMapper admMapper;
    @Test
    void testGetByUserNameAndPassWord(){
        String username = "adm456";
        String password = "123456";
        Adm adm = admMapper.getByUserNameAndPassWord(username,password);
        System.out.println(adm);
    }
}
