package com.learn.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 * @author banjiawei
 * @date 2020/10/09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -6839398848498112315L;
    private int id;
    private String userName;
    private int age;
}
