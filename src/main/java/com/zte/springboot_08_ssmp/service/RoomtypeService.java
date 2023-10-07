package com.zte.springboot_08_ssmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.Roomtype;

import java.util.List;

public interface RoomtypeService extends IService<Roomtype> {

    public List<Roomtype> getAll();

    public Roomtype findById(Integer id);

}
