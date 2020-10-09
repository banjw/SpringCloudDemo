package com.learn.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author banjiawei
 * @description
 * @date 2020/10/09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestEntity implements Serializable {
    private static final long serialVersionUID = -6839398848498112315L;
    private String userName;
    private int age;
}
