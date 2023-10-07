package com.zte.springboot_08_ssmp.domain;


import lombok.Data;

import java.util.Date;

@Data
public class Pay {
    private Integer id;

    private All all;

    private ClientStay clientStay;//该订单属于哪一个入住记录,所需支付

    private PayType payType;//记录支付订单类型

    private Date paytime;




}
