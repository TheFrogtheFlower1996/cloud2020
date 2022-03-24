package com.zh.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zh
 * @date 2022/1/24 20:18
 * @说明：统一返回结果集
 */
@Data
@AllArgsConstructor //全参
@NoArgsConstructor  //无参
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    //data为null的有参构造器
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
