package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zte.springboot_08_ssmp.domain.One;

import java.util.List;

public interface OneService {

//    添加一个客户记录
    public Boolean save(One one);

//    查找一个客户记录
    public One getByUserNameAndPassWord(String username, String userpwd);

//     查找所有客户记录
    public List<One> getAll();


//   删除某个客户记录
    public Boolean delete(Integer id);

//    修改某个客户记录
    public Boolean update(One one);

//
    IPage<One> getPage(int currentPage, int pageSize);


}
