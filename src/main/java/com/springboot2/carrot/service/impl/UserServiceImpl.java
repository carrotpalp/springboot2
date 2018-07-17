package com.springboot2.carrot.service.impl;

import com.springboot2.carrot.dao.UserDao;
import com.springboot2.carrot.model.User;
import com.springboot2.carrot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String userName) {
        User user = userDao.getUser(userName);
        return user;
    }
}
