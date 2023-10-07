package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.Pre;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PreService extends IService<Pre> {

//        查找所有记录id
    public List<Pre> findAll();
//    查找自己记录id
    public List<Pre> findByAid(Integer id);
//    添加一条预定记录
    public Boolean add(Pre pre) throws Exception;
}
