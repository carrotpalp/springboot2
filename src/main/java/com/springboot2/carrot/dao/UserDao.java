package com.springboot2.carrot.dao;

import com.springboot2.carrot.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User getUser(String userName){
        User user = new User();
        user.setName(userName);
        return user;
    }
}
