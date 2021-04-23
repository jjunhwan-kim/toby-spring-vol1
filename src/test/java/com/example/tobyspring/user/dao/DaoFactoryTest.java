package com.example.tobyspring.user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DaoFactoryTest {

    @Test
    public void daoFactoryTest() {
        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);
        assertThat(dao1).isNotSameAs(dao2);

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao3 = context.getBean("userDao", UserDao.class);
        UserDao dao4 = context.getBean("userDao", UserDao.class);
        
        System.out.println("dao3 = " + dao3);
        System.out.println("dao4 = " + dao4);
        assertThat(dao3).isSameAs(dao4);
    }
}