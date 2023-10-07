package com.zte.springboot_08_ssmp.controller.utils;

import lombok.Data;

@Data
public class R {
    private Boolean flag;
    private Object data;

    public R(){}
    public R(Boolean flag){
        this.flag=flag;
    }
    public R(Boolean flag,Object data){
        this.flag=flag;
        this.data=data;
    }
}
