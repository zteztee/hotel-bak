package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.Adm;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.mapper.AdmMapper;
import com.zte.springboot_08_ssmp.service.AdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmServiceImpl extends ServiceImpl<AdmMapper, Adm> implements AdmService {

    @Autowired
    AdmMapper admMapper;

    @Override
    public Adm getByUserNameAndPassWord(String username, String userpwd) {
        return  admMapper.getByUserNameAndPassWord(username,userpwd);
    }

//        用户账户已存在
}
