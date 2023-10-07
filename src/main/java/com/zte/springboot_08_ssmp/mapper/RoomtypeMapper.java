package com.zte.springboot_08_ssmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.Roomtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomtypeMapper extends BaseMapper<Roomtype> {

    @Select("select * from tbl_roomtype where id=#{id}")
    public Roomtype findById(Integer id);

}
