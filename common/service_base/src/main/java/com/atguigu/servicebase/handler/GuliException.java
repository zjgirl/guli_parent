package com.atguigu.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成有参构造方法
@NoArgsConstructor //生成无参构造方法
public class GuliException extends RuntimeException{
    private Integer code;
    private String msg;
}
