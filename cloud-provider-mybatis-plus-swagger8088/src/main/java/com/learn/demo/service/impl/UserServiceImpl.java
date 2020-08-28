package com.learn.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.demo.entity.User;
import com.learn.demo.mapper.UserMapper;
import com.learn.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectList() {
        return userMapper.selectList(null);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int delete(Wrapper<User> wrapper) {
        return userMapper.delete(wrapper);
    }

    @Override
    public int update(User user, Wrapper<User> wrapper) {
        return userMapper.update(user, wrapper);
    }

    @Override
    public Page<User> selectPage(Page<User> page, Wrapper<User> queryWrapper) {
        return userMapper.selectPage(page, queryWrapper);
    }
}
