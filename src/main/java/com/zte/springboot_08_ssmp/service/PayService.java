package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.Pay;

import java.util.List;


public interface PayService extends IService<Pay> {

//      通过账户密码查找散客订单信息
    public Pay findByOneUsernameAndPassword(String username, String userpwd);

    public Pay findByTeamUsernameAndPassword(String username, String userpwd);

//    支付订单，添加记录
    public Boolean save1(Pay pay);

//    查找所有记录
    public List<Pay> findAll();
}
