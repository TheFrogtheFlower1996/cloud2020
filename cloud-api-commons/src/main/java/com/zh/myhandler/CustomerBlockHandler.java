package com.zh.myhandler;

import com.zh.entities.CommonResult;

/**
 * @author zh
 * @date 2022/3/23 10:01
 * @description: 全局统一降级类
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(){

        return new CommonResult(444,"全局统一降级类 CustomerBlockHandler，降级方法 handlerException");
    }
}
