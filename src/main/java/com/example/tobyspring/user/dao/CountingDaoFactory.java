package com.example.tobyspring.user.dao;

import org.springframework.context.annotation.Bean;

//@Configuration
public class CountingDaoFactory {
    //@Bean
    public UserDao userDao() {
        UserDao userDao = new UserDaoJdbc();
//        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    //@Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    //@Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMaker();
    }
}
