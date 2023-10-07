package com.zte.springboot_08_ssmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.springboot_08_ssmp.domain.One;
import com.zte.springboot_08_ssmp.mapper.AllMapper;
import com.zte.springboot_08_ssmp.mapper.OneMapper;
import com.zte.springboot_08_ssmp.mapper.TeamMapper;
import com.zte.springboot_08_ssmp.service.OneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OneServiceImpl implements OneService {

    @Autowired
    OneMapper oneMapper;

    @Autowired
    AllMapper allMapper;

    @Autowired
    TeamMapper teamMapper;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean save(One one) {
        if (oneMapper.getUserName(one.getUsername())==null && teamMapper.getUserName(one.getUsername())==null){
            if (oneMapper.save(one)>0){
                if (one.getId()!=null){
                    return allMapper.addOne(one)>0;
                }
                else {
                    throw new RuntimeException("重试一下");
                }
            }
//            运行错误
            throw new RuntimeException("运行错误");
        }
//        用户账户已存在
        throw new RuntimeException("用户账户已存在");
    }

    @Override
    public One getByUserNameAndPassWord(String username, String userpwd) {
        int allid = 0;
        One one = oneMapper.getByUserNameAndPassWord(username,userpwd);
        if (one!=null){
            allid = allMapper.selectByPid(one.getId()).getId();
            one.setAllid(allid);
            return one;
        }
        return null;
    }

    @Override
    public List<One> getAll() {
        return oneMapper.selectList(null);
    }

    @Override
    public Boolean delete(Integer id) {
        return oneMapper.deleteById(id) >0;
    }

    @Override
    public Boolean update(One one) {
        return oneMapper.updateByUserNameAndPassWord(one) >0;
    }

    @Override
    public IPage<One> getPage(int currentPage, int pageSize) {
        QueryWrapper<One> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(One.class, info->!info.getColumn().equals("allid"));
        IPage page = new Page(currentPage,pageSize);
        return oneMapper.selectPage(page,queryWrapper);
    }
}
