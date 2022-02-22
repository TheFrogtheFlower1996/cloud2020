package com.zh.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zh
 * @version 1.0
 * @date 2022/1/24 20:14
 */
@Data
@AllArgsConstructor //有参 无参
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
