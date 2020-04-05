package com.qiancheng.redis.practice.vo;

import lombok.Data;

@Data
public class UserVo {
    public static final String Table = "t_user";
    private int age;
    private String name;
    private String address;
}
