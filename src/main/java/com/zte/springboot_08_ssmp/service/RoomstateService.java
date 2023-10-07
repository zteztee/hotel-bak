package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.Roomstate;


import java.util.List;

public interface RoomstateService extends IService<Roomstate> {



    public List<Roomstate> getAll();

    public Roomstate findById(Integer id);
}
