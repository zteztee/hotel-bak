package com.zte.springboot_08_ssmp.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClientStay {
    private Integer id;

    private All all; //拿all类的记录
    private List<String> roomList;//拿room类房间记录
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date staytime;
    private Integer days;
    private Double payneed;

    private Integer times;

    private ClientStayRoomList clientStayRoomList;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private  Date dateout;
    public List<String> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<String> roomList) {
        this.roomList = roomList;
    }

    private PayType paytype;

    public All getAll() {
        return all;
    }

    public void setAll(All all) {
        this.all = all;
    }



}
