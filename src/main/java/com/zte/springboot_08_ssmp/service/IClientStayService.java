package com.zte.springboot_08_ssmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.springboot_08_ssmp.domain.ClientStay;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClientStayService extends IService<ClientStay> {


//    查询用户入住登记记录
        public List<ClientStay> getClientStayByAid(Integer aid);
//        删除当前用户登记记录
        public Boolean remove(Integer id) throws Exception;
//        保存用户入住登记记录
//        public Boolean save1(ClientStay clientStay) throws Exception;

    //        for (Room room : rooms) {
    //            payneed += (double) (room.getDaymoney()*days+room.getTimemoney());
    //        }
    //        clientStay.setPayneed(payneed);
    //    新增用户记录
    @Transactional(rollbackFor=Exception.class)
    Boolean saveOne(ClientStay clientStay) throws Exception;

    @Transactional(rollbackFor=Exception.class)
    Boolean saveTeam(ClientStay clientStay) throws Exception;

    //        查询所有入住登记记录
        public List<ClientStay> getAll();

        public List<ClientStay> getPage(int currentPage, int pageSize);

}
