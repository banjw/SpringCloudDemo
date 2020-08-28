package com.learn.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectList();

    int insert(User user);

    int delete(Wrapper<User> wrapper);

    int update(User user, Wrapper<User> wrapper);

    Page<User> selectPage(Page<User> page,  Wrapper<User> queryWrapper);
}
