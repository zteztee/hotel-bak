package com.zte.springboot_08_ssmp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Pre {
    private Integer id;

    private All all; // 记录预定的账户信息

    private Integer preper;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date cometime;

    private List<String> roomList;//拿room类房间记录

    public List<String> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<String> roomList) {
        this.roomList = roomList;
    }


}
