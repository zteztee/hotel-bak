package com.zte.springboot_08_ssmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.Roomstate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomstateMapper extends BaseMapper<Roomstate> {

    @Select("select * from tbl_roomstate where id=#{id}")
    public Roomstate findById(Integer id);
}
