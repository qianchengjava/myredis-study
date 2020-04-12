package com.qiancheng.redis;

import lombok.Data;

import java.util.HashSet;
@Data
public class MyUser {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        MyUser user = new MyUser();
        user.setName("qc");
        hashSet.add(user);
        MyUser user1 = new MyUser();
        user1.setName("qc");
        user1.setAge(null);
        hashSet.add(user1);
        System.out.println(hashSet);


    }
}
