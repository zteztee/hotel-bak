package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.Roomstate;
import com.zte.springboot_08_ssmp.mapper.RoomstateMapper;
import com.zte.springboot_08_ssmp.service.RoomstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomstateServiceImpl extends ServiceImpl<RoomstateMapper, Roomstate> implements RoomstateService {

    @Autowired
    RoomstateMapper roomStateMapper;


    @Override
    public List<Roomstate> getAll() {
        return roomStateMapper.selectList(null);
    }

    @Override
    public Roomstate findById(Integer id) {
        return roomStateMapper.findById(id);
    }
}
