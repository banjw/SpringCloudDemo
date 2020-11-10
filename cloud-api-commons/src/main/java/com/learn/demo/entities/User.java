package com.learn.demo.entities;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户实体
 *
 * @author banjiawei
 * @date 2020/11/10
 */
@Data
public class User {
    @NotNull(message = "用户id不能为空")
    private Long id;

    @NotBlank(message = "用户账号不能为空")
    private String account;

    @Size(min = 6, max = 11, message = "密码长度必须是6-16个字符")
    private String password;

    @NotBlank(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
}
