package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.CountClient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountClientMapper extends BaseMapper<CountClient> {
    @Insert("insert into tbl_count_client(id,roomnum,aid,roomtype) values(null,#{roomnum},#{aid},#{roomtype})")
    public int insert(String roomnum,Integer aid,String roomtype);

//    获取某个房间类型的入住次数
    @Select("SELECT COUNT(roomtype) as count1 FROM tbl_count_client WHERE  roomtype=#{roomtype}")
    public CountClient selectTypeCount(String roomtype);
//  获取某个房间号的入住次数
    @Select("SELECT COUNT(roomnum) as count2 FROM tbl_count_client WHERE  roomnum=#{roomnum}")
    public CountClient selectRoomnumCount(String roomnum);
//    获取某个房客的入住记录
    @Select("SELECT COUNT(aid) as count3 FROM tbl_count_client WHERE  aid=#{aid}")
    public CountClient selectAidCount(Integer aid);

    @Select("select Count(id) as count from tbl_count_client")
    public CountClient selectAllCount();
    @Select("SELECT roomtype as count4 FROM tbl_count_client WHERE aid=#{aid} GROUP BY roomtype ORDER BY COUNT(roomtype) DESC ")
    public List<CountClient> selectBest(Integer aid);
}
