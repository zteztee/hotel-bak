package com.zte.springboot_08_ssmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.springboot_08_ssmp.domain.Adm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdmMapper extends BaseMapper<Adm> {

    @Select("Select * from tbl_adm where username=#{username} and userpwd=#{userpwd}")
    Adm getByUserNameAndPassWord(String username, String userpwd);
}
