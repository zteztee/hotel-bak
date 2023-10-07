package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.Adm;

public interface AdmService extends IService<Adm> {

    Adm getByUserNameAndPassWord(String username, String userpwd);
}
