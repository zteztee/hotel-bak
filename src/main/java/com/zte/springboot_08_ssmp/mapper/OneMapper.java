package com.zte.springboot_08_ssmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.One;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OneMapper extends BaseMapper<One> {

    @Select("select * from tbl_one where username=#{username} and userpwd=#{userpwd}")
    One getByUserNameAndPassWord(String username, String userpwd);

    @Update("update tbl_one set onename=#{onename},onesex=#{onesex},oneidentify=#{oneidentify},oneage=#{oneage},onelogin=#{onelogin} where username=#{username} and userpwd=#{userpwd} ")
    public int updateByUserNameAndPassWord(One one);

    @Select("SELECT username FROM tbl_one WHERE username=#{username}")
    public String getUserName(String username);

    @Insert("insert into tbl_one(id,onename,onesex,oneidentify,oneage,onelogin,username,userpwd) values(null,#{onename},#{onesex},#{oneidentify},#{oneage},#{onelogin},#{username},#{userpwd})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public int save(One one);
}
