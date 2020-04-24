package com.learn.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;
    public CommonResult(Integer code, String msg){
        this(code, msg, null);
    }
}
