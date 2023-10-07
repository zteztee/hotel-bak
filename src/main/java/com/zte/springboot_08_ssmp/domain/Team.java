package com.zte.springboot_08_ssmp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Team {
    private Integer id;
    private String teamname;
    private String teamemil;
    private String teamprimary;
    private String teamnumber;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date teamlogin;
    private String username;
    private String userpwd;
    private Integer allid;


}
