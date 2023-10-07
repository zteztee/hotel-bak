package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.Pay;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayMapper extends BaseMapper<Pay> {

//    根据用户账户密码查询订单
    @Results(id = "pay1",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "paytype",property = "paytype.paytype"),
            @Result(column = "staytime",property = "clientStay.staytime"),
            @Result(column = "payname",property = "all.username"),
            @Result(column = "paytime",property = "paytime"),
    })
    @Select("select * from tbl_pay where aid=#{id}")
    public Pay selectByAid(Integer id);

//    添加订单操作
    @Insert("insert into tbl_pay values(null,#{paytype.paytype},#{clientStay.staytime},#{all.username},#{paytime})")
    public int insert(Pay pay);

//    查找所有记录
    @ResultMap("pay1")
    @Select("select * from tbl_pay")
    public List<Pay> selectAll();

}
