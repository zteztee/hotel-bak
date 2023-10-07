package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import com.zte.springboot_08_ssmp.mapper.RoomtypeMapper;
import com.zte.springboot_08_ssmp.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomtypeServiceImpl extends ServiceImpl<RoomtypeMapper,Roomtype> implements RoomtypeService {

    @Autowired
    RoomtypeMapper roomTypeMapper;

    @Override
    public List<Roomtype> getAll() {
        return roomTypeMapper.selectList(null);
    }

    @Override
    public Roomtype findById(Integer id) {
        return roomTypeMapper.findById(id);
    }



}
