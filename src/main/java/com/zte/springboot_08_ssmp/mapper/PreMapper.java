package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import com.zte.springboot_08_ssmp.domain.Pre;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PreMapper extends BaseMapper<Pre> {


//    查找所有预定信息
    @Results(id = "resultMap2",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "aid",property = "all.id"),
            @Result(column = "preper",property = "preper"),
            @Result(column = "cometime",property = "cometime"),
    })
    @Select("select * from tbl_pre")
    public List<Pre> selectAll();

//    查找自己记录信息
    @ResultMap("resultMap2")
    @Select("select * from tbl_pre where aid=#{id}")
    public List<Pre> selectByAid(Integer id);

//    添加预定记录
    @Insert("insert into tbl_pre(id,aid,preper,cometime) values(null,#{all.id},#{preper},#{cometime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int insert(Pre pre);

    @Select("select cometime from tbl_pre where id=#{id}")
    @Result(column = "cometime",property = "cometime")
    public Pre selectComeTimeById(Integer id);
}
