package com.zte.springboot_08_ssmp.domain;

import lombok.Data;

@Data
public class Room {
    private Integer id;
    private String roomnum;
    private Roomstate roomState;
    private Roomtype roomType;
    private Integer timemoney;
    private Integer daymoney;
    private ClientStay clientStay;
    private Pre pre;

    private Integer count;
}
