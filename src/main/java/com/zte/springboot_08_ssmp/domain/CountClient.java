package com.zte.springboot_08_ssmp.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountClient {
    private Integer id;
    private String roonum;
    private Integer aid;
    private String roomtype;
    private Integer count;
    //房间类型统计
    private Integer count1;
    //房间号统计
    private Integer count2;
    //入住id统计
    private Integer count3;
    //房间最佳类型
    private String count4;

    private String day;
    //房间访问量统计
    private Integer value1;
    //房间下单量统计
    private Integer value2;
    //收入统计
    private Double value3;
    //月
    private String month;


}
