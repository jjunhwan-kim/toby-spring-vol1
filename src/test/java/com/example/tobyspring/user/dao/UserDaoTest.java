package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        User user = new User("gyumee", "박성철", "springno1");

        userDao.add(user);

        assertThat(userDao.getCount()).isEqualTo(1);

        User user2 = userDao.get(user.getId());

        assertThat(user.getId()).isEqualTo(user2.getId());
        assertThat(user.getName()).isEqualTo(user2.getName());
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }
}