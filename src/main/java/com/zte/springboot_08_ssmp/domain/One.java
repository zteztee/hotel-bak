package com.zte.springboot_08_ssmp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class One {
    private Integer id;
    private String onename;
    private Integer onesex;
    private String oneidentify;
    private Integer oneage;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date onelogin;
    private String username;
    private String userpwd;
    private Integer allid;
}
